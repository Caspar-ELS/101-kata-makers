package service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

import filter.BomServiceFilter;
import java.util.List;

public class Ec2Service {

  private static BomServiceFilter bomServiceFilter;

  public Ec2Service() {
    bomServiceFilter = new BomServiceFilter();
  }

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;

  public void printRunningInstances() {
    DescribeInstancesResult describeInstancesResult = getRunningInstances();

    List<Reservation> reservations = describeInstancesResult.getReservations();

    reservations.forEach(reservation -> {
      for (Instance instance : reservation.getInstances()) {
        instance.getTags().stream()
            .filter(Ec2Service::isDevEnvironment)
            .forEach(tag -> printRunningInstances(instance, getInstanceName(instance)));
      }
    });
  }

  private static boolean isDevEnvironment(Tag tag) {
    return tag.getKey().equals("Environment") && tag.getValue().equals("dev");
  }

  private static String getInstanceName(Instance instance) {
    return instance.getTags().stream()
        .filter(tag -> tag.getKey().equals("Role"))
        .findFirst()
        .map(Tag::getValue)
        .orElse("N/A");
  }

  private static void printRunningInstances(Instance instance, String instanceName) {
    if (bomServiceFilter.isBomService(instanceName)) {
      System.out.printf(
          "Name: %s, State: %s%n",
          instanceName,
          instance.getState().getName()
      );
    }
  }

  private static DescribeInstancesResult getRunningInstances() {
    AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(DEFAULT_REGION).build();

    DescribeInstancesRequest request = new DescribeInstancesRequest()
        .withFilters(new Filter("instance-state-name").withValues("running"));

    return ec2.describeInstances(request);
  }

//  Show all microservices, categorized by components
//  (billing, order_management, fulfillment, revenue_recognition, core_bom, test_utilities)

}
