package service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Ec2Service {

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;
  private static final Filter RUNNING_INSTANCE_FILTER = new Filter(
      "instance-state-name").withValues("running");
  private final TagService tagService = new TagService();
  private final FilterService filterService = new FilterService();
  Map<String, String> tagMap = null;
  Map<String, String> devTagMap = null;

  public void listBomFeatures() {
    listAllRunningInstance();
    for (Map.Entry<String, List<String>> entry : filterService.BOM_FEATURE.entrySet()) {
      String bomFeature = entry.getKey();
      List<String> bomComponents = entry.getValue();
      System.out.println("-----------------------");
      System.out.println(bomFeature);
      for (String bomComponent : bomComponents) {
        listBomServices(bomComponent);
      }

    }
  }

  private void listBomServices(String bomComponent) {
    for (Map.Entry<String, List<String>> entry : filterService.BOM_COMPONENTS.entrySet()) {
      String component = entry.getKey();
      List<String> services = entry.getValue();
      if (Objects.equals(component, bomComponent)) {
        System.out.println(bomComponent);
        for (String service : services) {
          isServiceRunning(service);
        }
      }
    }
  }

  private void listAllRunningInstance() {
    DescribeInstancesRequest request = new DescribeInstancesRequest().withFilters(
        RUNNING_INSTANCE_FILTER);

    DescribeInstancesResult result = AmazonEC2ClientBuilder.standard().withRegion(DEFAULT_REGION)
        .build().describeInstances(request);

    List<Reservation> reservations = result.getReservations();
    for (Reservation reservation : reservations) {
      for (Instance instance : reservation.getInstances()) {
        tagMap = tagService.getTagsFrom(instance);
        if (isDevBomService(tagMap)) {
          devTagMap = tagService.getTagsFrom(instance);
        }
      }
    }
  }


  private void isServiceRunning(String serviceShortName) {
    if (devTagMap.containsValue(serviceShortName)) {
      printRow(serviceShortName, "Running");
    } else {
      printRow(serviceShortName, "Not running");
    }
  }

  private boolean isDevBomService(Map<String, String> tagMap) {
    return filterService.isBomServices(getServiceShortNameFrom(tagMap))
        && filterService.isDev(getServiceEnvironment(tagMap));
  }

  private void printRow(String serviceShortName, String state) {
    System.out.printf("%s, :  %s%n", serviceShortName, state);
  }

  private String getServiceShortNameFrom(Map<String, String> tagMap) {
    return tagMap.get("Role");
  }

  private String getServiceEnvironment(Map<String, String> tagMap) {
    return tagMap.get("Environment");
  }

}