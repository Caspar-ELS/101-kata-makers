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

public class Ec2Service {

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;
  private static final Filter RUNNING_INSTANCE_FILTER = new Filter(
      "instance-state-name").withValues("running");
  private final TagService tagService = new TagService();
  private final FilterService filterService = new FilterService();

  private static final HashMap<String, List<String>> SERVICE_RUNNING_MAP = new HashMap<>(Map.of(
      BILLING, Collections.emptyList(),
      ORDER_MANAGEMENT, Collections.emptyList(),
      FULFILLMENT, Collections.emptyList(),
      REVENUE_RECOGNITION, Collections.emptyList(),
      CORE_BOM, Collections.emptyList(),
      TEST_UTILITIES, Collections.emptyList()
  ));


  public void listRunningInstance() {
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

          List<String> servicesInComponent = new ArrayList(SERVICE_RUNNING_MAP.get(component));
          servicesInComponent.add(serviceShortName);
          SERVICE_RUNNING_MAP.put(component, servicesInComponent);
        }
      }
    }

    BOM_COMPONENTS.forEach((component, services) -> {
      System.out.printf("Component: %s, Services: %s%n", component, services);
      for (String service : services) {
        System.out.printf("Service: %s, State: %s%n", service,
            isRunning(service) ? "Running" : "Not Running");
      }
    });
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

  private void printRow(Map<String, String> tagMap, Instance instance) {
    System.out.printf("Name: %s, Component: %s, State: %s%n", getServiceShortNameFrom(tagMap),
        getServiceComponent(getServiceShortNameFrom(tagMap)), instance.getState().getName());
  }

  private String getServiceShortNameFrom(Map<String, String> tagMap) {
    return tagMap.get("Role");
  }

  private String getServiceEnvironment(Map<String, String> tagMap) {
    return tagMap.get("Environment");
  }

  private boolean isRunning(String serviceShortName) {
    return SERVICE_RUNNING_MAP.entrySet().stream()
        .anyMatch(entry -> entry.getValue().contains(serviceShortName));
  }

}
