package service;

import static filter.BomServiceFilter.BILLING;
import static filter.BomServiceFilter.CORE_BOM;
import static filter.BomServiceFilter.FULFILLMENT;
import static filter.BomServiceFilter.ORDER_MANAGEMENT;
import static filter.BomServiceFilter.REVENUE_RECOGNITION;
import static filter.BomServiceFilter.TEST_UTILITIES;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

import filter.BomServiceFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ec2Service {

  public static final Map<String, List<String>> bomComponents = Map.of(
      BILLING, List.of("trds", "trsb", "trdr", "inas", "cats", "atss", "trsr", "insr",
          "insp", "cnsp", "tltt"),
      ORDER_MANAGEMENT, List.of("orcx", "orrx", "oisu"),
      FULFILLMENT, List.of("fucx", "aofc", "arfx", "asft", "asfc", "pofc", "fusp"),
      REVENUE_RECOGNITION, List.of("rrspv3", "rrsrv3", "rersv3", "reacv3", "rertv3", "arfs"),
      CORE_BOM, List.of("bocs", "nesx"),
      TEST_UTILITIES, List.of("eier", "tekp")
  );

  private final List<String> runningServices = new ArrayList<>();

  private final BomServiceFilter bomServiceFilter;

  public Ec2Service(BomServiceFilter bomServiceFilter) {
    this.bomServiceFilter = bomServiceFilter;
  }

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;

  public void printRunningInstances() {
    List<Instance> instances = getRunningInstances();

    findAllRunningBomServices(instances);

    for (String service : runningServices) {
      print(service);
    }
  }

  public void printInstancesByComponent() {
    List<Instance> instances = getRunningInstances();

    findAllRunningBomServices(instances);

    for (Map.Entry<String, List<String>> entry : bomComponents.entrySet()) {
      List<String> values = entry.getValue();
      System.out.println("Component: " + entry.getKey());
      for (String service : values) {
        print(service);
      }
    }
  }

  private List<Instance> getRunningInstances() {
    DescribeInstancesResult describeInstancesResult = getDescribeInstancesResult();

    return describeInstancesResult.getReservations().stream()
        .flatMap(reservation -> reservation.getInstances().stream())
        .toList();
  }

  private DescribeInstancesResult getDescribeInstancesResult() {
    AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(DEFAULT_REGION).build();

    DescribeInstancesRequest request = new DescribeInstancesRequest();
//        .withFilters(new Filter("instance-state-name").withValues("running", "terminated"));
//    .withValues("running", "stopped")

    return ec2.describeInstances(request);
  }

  private void findAllRunningBomServices(List<Instance> instances) {
    instances.forEach(instance -> instance.getTags().stream()
        .filter(this::isDevEnvironment)
        .filter(instanceName -> bomServiceFilter.isBomService(getInstanceName(instance)))
        .forEach(tag -> runningServices.add(getInstanceName(instance))));
  }


  private boolean isDevEnvironment(Tag tag) {
    return tag.getKey().equals("Environment") && tag.getValue().equals("dev");
  }

  private String getInstanceName(Instance instance) {
    return instance.getTags().stream()
        .filter(tag -> tag.getKey().equals("Role"))
        .findFirst()
        .map(Tag::getValue)
        .orElse("N/A");
  }

  private void print(String service) {
    System.out.printf(
        "Name: %s, State: %s%n",
        service,
        runningServices.contains(service) ? "running" : "not running"
    );
  }
}
