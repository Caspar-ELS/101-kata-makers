package service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

import java.util.List;
import java.util.Map;

public class Ec2Service {

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;
  private static final Filter RUNNING_INSTANCE_FILTER = new Filter("instance-state-name").withValues("running");
  private final TagService tagService = new TagService();
  private final FilterService filterService = new FilterService();


  public void listRunningInstance() {
    DescribeInstancesRequest request = new DescribeInstancesRequest().withFilters(RUNNING_INSTANCE_FILTER);

    DescribeInstancesResult result = AmazonEC2ClientBuilder.standard().withRegion(DEFAULT_REGION)
        .build().describeInstances(request);

    List<Reservation> reservations = result.getReservations();
    for (Reservation reservation : reservations) {
      for (Instance instance : reservation.getInstances()) {
        Map<String, String> tagMap = tagService.getTagsFrom(instance);
        if (isDevBomService(tagMap)) {
          printRow(tagMap, instance);
        }
      }
    }
  }

  private boolean isDevBomService(Map<String, String> tagMap) {
    return filterService.isBomServices(getServiceShortNameFrom(tagMap))
        && filterService.isDev(getServiceEnvironment(tagMap));
  }

  private void printRow(Map<String, String> tagMap, Instance instance) {
    System.out.printf("Name: %s, State: %s%n", getServiceShortNameFrom(tagMap), instance.getState().getName());
  }

  private String getServiceShortNameFrom(Map<String, String> tagMap) {
    return tagMap.get("Role");
  }

  private String getServiceEnvironment(Map<String, String> tagMap) {
    return tagMap.get("Environment");
  }

}
