package service;

import static util.BomConstants.bomComponents;
import static util.BomConstants.bomServices;
import static util.BomConstants.regressionComponents;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ec2Service {

  private static final String DIVIDER = "------------------------";
  private static final String RUNNING = "running";
  private static final String NOT_RUNNING = "not running";
  private final List<String> runningServices = new ArrayList<>();
  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;

  public Ec2Service() {
  }

  public void printRunningInstances() {
    List<Instance> instances = getRunningInstances();
    findAllRunningBomServices(instances);

    runningServices.forEach(this::printNameAndState);
  }

  public void printInstancesByComponent() {
    List<Instance> instances = getRunningInstances();
    findAllRunningBomServices(instances);

    for (Map.Entry<String, List<String>> entry : bomComponents.entrySet()) {
      System.out.println("Component: " + entry.getKey());

      List<String> services = entry.getValue();
      services.forEach(this::printNameAndState);

      System.out.println(DIVIDER);
    }
  }

  public void printComponentsForRegressionTests() {
    List<Instance> instances = getRunningInstances();
    findAllRunningBomServices(instances);

    for (Map.Entry<String,List<String>> entry : regressionComponents.entrySet()) {
      List<String> components = entry.getValue();

      System.out.println("Collection: " + entry.getKey());

      components.forEach(component -> {
        System.out.println("Component: " + component);

        List<String> services = bomComponents.get(component);
        services.forEach(this::printNameAndState);

        System.out.println(DIVIDER);
      });
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

    DescribeInstancesRequest request = new DescribeInstancesRequest()
        .withFilters(new Filter("instance-state-name").withValues(RUNNING));

    return ec2.describeInstances(request);
  }

  private void findAllRunningBomServices(List<Instance> instances) {
    instances.forEach(instance -> instance.getTags().stream()
        .filter(this::isDevEnvironment)
        .filter(tag -> isBomService(getServiceAcronymFrom(instance)))
        .forEach(tag -> runningServices.add(getServiceAcronymFrom(instance))));
  }

  private boolean isDevEnvironment(Tag tag) {
    return tag.getKey().equals("Environment") && tag.getValue().equals("dev");
  }

  private boolean isBomService(String serviceAcronym) {
    return bomServices.contains(serviceAcronym);
  }

  private String getServiceAcronymFrom(Instance instance) {
    return instance.getTags().stream()
        .filter(tag -> tag.getKey().equals("Role"))
        .findFirst()
        .map(Tag::getValue)
        .orElse("N/A");
  }

  private void printNameAndState(String service) {
    System.out.printf(
        "Name: %s, State: %s%n",
        service,
        runningServices.contains(service) ? RUNNING : NOT_RUNNING
    );
  }
}
