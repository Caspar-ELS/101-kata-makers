package service;

import static service.FilterService.BILLING;
import static service.FilterService.BOM_COMPONENTS;
import static service.FilterService.CORE_BOM;
import static service.FilterService.FULFILLMENT;
import static service.FilterService.ORDER_MANAGEMENT;
import static service.FilterService.REVENUE_RECOGNITION;
import static service.FilterService.TEST_UTILITIES;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import model.ServiceStatus;

public class Ec2Service {

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;
  private static final Filter RUNNING_INSTANCE_FILTER = new Filter(
      "instance-state-name").withValues("running");
  private final TagService tagService = new TagService();
  private final FilterService filterService = new FilterService();

  private static final HashMap<String, List<ServiceStatus>> COMPONENT_SERVICES_STATUS_MAP = new HashMap<>(Map.of(
      BILLING, Collections.emptyList(),
      ORDER_MANAGEMENT, Collections.emptyList(),
      FULFILLMENT, Collections.emptyList(),
      REVENUE_RECOGNITION, Collections.emptyList(),
      CORE_BOM, Collections.emptyList(),
      TEST_UTILITIES, Collections.emptyList()
  ));


  public void listRunningInstance() {
    initialiseServiceRunningMap();

    DescribeInstancesRequest request = new DescribeInstancesRequest().withFilters(
        RUNNING_INSTANCE_FILTER);

    DescribeInstancesResult result = AmazonEC2ClientBuilder.standard().withRegion(DEFAULT_REGION)
        .build().describeInstances(request);

    List<Reservation> reservations = result.getReservations();
    for (Reservation reservation : reservations) {
      for (Instance instance : reservation.getInstances()) {
        Map<String, String> tagMap = tagService.getTagsFrom(instance);
        if (isDevBomService(tagMap)) {
          String serviceShortName = getServiceShortNameFrom(tagMap);
          String component = getServiceComponent(serviceShortName);

          List<ServiceStatus> servicesInComponent = new ArrayList<>(
              COMPONENT_SERVICES_STATUS_MAP.get(component));
          Optional<ServiceStatus> optionalServiceStatus = servicesInComponent.stream()
              .filter(service -> serviceShortName.equals(service.getName())).findFirst();

          if(optionalServiceStatus.isPresent()){
            updateServiceStatus(servicesInComponent, optionalServiceStatus);

            COMPONENT_SERVICES_STATUS_MAP.put(component, servicesInComponent);
          }
        }
      }
    }

    System.out.println("To run regression test you have to start component:");
    System.out.println(String.format("Orders: %s", printComponentFor(List.of(ORDER_MANAGEMENT, BILLING))));
    System.out.println(String.format("Invoices: %s", printComponentFor(List.of(BILLING))));
    System.out.println(String.format("CreditNotes: %s", printComponentFor(List.of(BILLING))));
    System.out.println(String.format("TransactionStatuses: %s", printComponentFor(List.of(BILLING))));
    System.out.println(String.format("AccountsReceivablesRevenueRecognition: %s", printComponentFor(List.of(ORDER_MANAGEMENT, BILLING, REVENUE_RECOGNITION, TEST_UTILITIES, FULFILLMENT))));
    System.out.println(String.format("GeneralLedgerRevenueRecognitionV3: %s", printComponentFor(List.of(ORDER_MANAGEMENT, REVENUE_RECOGNITION, FULFILLMENT))));

  }

  private String getServiceComponent(String serviceShortName) {
    return BOM_COMPONENTS.entrySet().stream()
        .filter(entry -> entry.getValue().contains(serviceShortName))
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse(null);
  }

  private boolean isDevBomService(Map<String, String> tagMap) {
    return filterService.isBomServices(getServiceShortNameFrom(tagMap))
        && filterService.isDev(getServiceEnvironment(tagMap));
  }

  private String getServiceShortNameFrom(Map<String, String> tagMap) {
    return tagMap.get("Role");
  }

  private String getServiceEnvironment(Map<String, String> tagMap) {
    return tagMap.get("Environment");
  }

  private List<ServiceStatus> initialServicesStatus(List<String> serviceNames){
    return serviceNames.stream().map(serviceName -> new ServiceStatus(serviceName, false)).toList();
  }

  private void initialiseServiceRunningMap(){
    COMPONENT_SERVICES_STATUS_MAP.put(BILLING, initialServicesStatus(BOM_COMPONENTS.get(BILLING)));
    COMPONENT_SERVICES_STATUS_MAP.put(ORDER_MANAGEMENT, initialServicesStatus(BOM_COMPONENTS.get(ORDER_MANAGEMENT)));
    COMPONENT_SERVICES_STATUS_MAP.put(FULFILLMENT, initialServicesStatus(BOM_COMPONENTS.get(FULFILLMENT)));
    COMPONENT_SERVICES_STATUS_MAP.put(REVENUE_RECOGNITION, initialServicesStatus(BOM_COMPONENTS.get(REVENUE_RECOGNITION)));
    COMPONENT_SERVICES_STATUS_MAP.put(CORE_BOM, initialServicesStatus(BOM_COMPONENTS.get(CORE_BOM)));
    COMPONENT_SERVICES_STATUS_MAP.put(TEST_UTILITIES, initialServicesStatus(BOM_COMPONENTS.get(TEST_UTILITIES)));
  }

  private List<String> printComponentFor(List<String> components) {
    return components.stream().filter(component -> !filterService.isAllServicesRunningIn(component,
        COMPONENT_SERVICES_STATUS_MAP)).toList();
  }

  private void updateServiceStatus(List<ServiceStatus> servicesInComponent, Optional<ServiceStatus> optionalServiceStatusToBeUpdated){
    ServiceStatus serviceStatus = optionalServiceStatusToBeUpdated.get();
    serviceStatus.setRunning(true);
    servicesInComponent.set(servicesInComponent.indexOf(serviceStatus), serviceStatus);
  }
}
