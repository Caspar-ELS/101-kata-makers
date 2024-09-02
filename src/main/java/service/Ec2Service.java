package service;

import static service.Filter.belongsToEnumComponent;
import static service.Filter.checkForComponentServices;
import static service.Filter.componentsForTests;
import static service.Filter.getEnvironment;
import static service.Filter.getServiceAcronym;
import static service.Filter.isBomService;
import static service.Filter.isDev;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import enums.Component;
import enums.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.ServiceInstance;

public class Ec2Service {

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;
  private static final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(DEFAULT_REGION).build();

  public ArrayList<ServiceInstance> getAllDevRunningInstances() {

    DescribeInstancesResult runningInstancesResult = getRunningInstancesResult();
    ArrayList<ServiceInstance> runningInstances = new ArrayList<>();
    runningInstancesResult.getReservations().forEach(reservation -> {
      for (Instance instance : reservation.getInstances()) {
        String instanceName = getServiceAcronym(instance);
        if (isDev(getEnvironment(instance)) && isBomService(instanceName)) {
          runningInstances.add(ServiceInstance.builder().name(instanceName).isRunning(true).build());
        }
      }
    });
    return runningInstances;
  }

  public void listAllDevRunningInstances() {
    ArrayList<ServiceInstance> devRunningInstances = getAllDevRunningInstances();
    System.out.println("Instances running in DEV\n");
    devRunningInstances.forEach(Ec2Service::printInstanceState);
  }

  private static void printInstanceState(ServiceInstance serviceInstance) {
    System.out.printf(
        "Name: %s, isRunning: %s%n",
        serviceInstance.getName(),
        serviceInstance.isRunning()
    );
  }

  public List<ServiceInstance> getInstancesByComponent(Component component) {
    DescribeInstancesResult instancesResult = getDescribeInstancesResult();
    ArrayList<ServiceInstance> componentRunningInstances = new ArrayList<>();
    instancesResult.getReservations().forEach(reservation -> {
      for (Instance instance : reservation.getInstances()) {
        String instanceName = getServiceAcronym(instance);
        if (isDev(getEnvironment(instance)) && belongsToEnumComponent(component, instanceName)) {
          componentRunningInstances.add(ServiceInstance.builder().name(instanceName).isRunning(true).build());
        }
      }
    });
    List<ServiceInstance> inactiveServices = checkForComponentServices(componentRunningInstances,
        component);
     componentRunningInstances.addAll(inactiveServices);
     return componentRunningInstances;
  }

  public void listInstancesByComponent(Component component) {
    List<ServiceInstance> instancesByComponent = getInstancesByComponent(component);
    System.out.println("Component's instances in DEV\n");
    instancesByComponent.forEach(Ec2Service::printInstanceState);
  }

  public void listComponentsToStartForRegressionTests(Test testName) {
    System.out.printf("To run %s you need to start:%n", testName);
    printInstancesToStart(getComponentsForTest(testName));
  }

  public List<ServiceInstance> getComponentsForTest(Test testName) {
    List<Component> components = componentsForTests.get(testName);
    List<ServiceInstance> toStart = new ArrayList<>();
    for (Component component : components) {
      toStart.addAll(getInstancesByComponent(component));
    }
    return toStart.stream().filter(instance -> !instance.isRunning()).collect(Collectors.toList());
  }

  private static DescribeInstancesResult getRunningInstancesResult() {
    DescribeInstancesRequest requestRunningInstances = new DescribeInstancesRequest()
        .withFilters(new Filter("instance-state-name").withValues("running"));
    return ec2.describeInstances(requestRunningInstances);
  }

  private static DescribeInstancesResult getDescribeInstancesResult() {
    DescribeInstancesRequest requestInstances = new DescribeInstancesRequest();
    return ec2.describeInstances(requestInstances);
  }

  private static void printInstancesToStart(List<ServiceInstance> services) {
    for (ServiceInstance service : services) {
      System.out.printf(
          "Name: %s, running: %s%n",
          service.getName(),
          service.isRunning()
      );
    }
    if (services.isEmpty()) {
      System.out.println("All required services are already running");
    }
  }
}
