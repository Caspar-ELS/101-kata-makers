package service;

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
import java.util.List;
import java.util.stream.Collectors;

public class Ec2Service {

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;

  public void listInstance() {
    AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(DEFAULT_REGION).build();

    DescribeInstancesRequest requestRunningInstances = new DescribeInstancesRequest()
        .withFilters(new Filter("instance-state-name").withValues("running"));

    DescribeInstancesResult runningInstancesResult = ec2.describeInstances(requestRunningInstances);
    runningInstancesResult.getReservations().forEach(reservation -> {
      for (Instance instance : reservation.getInstances()) {
        String instanceName = getServiceAcronym(instance);
        if (isDev(getEnvironment(instance)) && isBomService(instanceName)) {
          printResults(instance, instanceName);
        }
      }
    });

  }


  private static void printResults(Instance instance, String instanceName) {
    System.out.printf(
        "Name: %s, State: %s%n",
        instanceName,
        instance.getState().getName()
    );
  }

}
