package service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

import java.util.List;

public class Ec2Service {

  private static final Regions DEFAULT_REGION = Regions.EU_WEST_1;

  public void listInstance() {
    AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(DEFAULT_REGION).build();

    // filter out all running instances
    DescribeInstancesRequest request = new DescribeInstancesRequest()
        .withFilters(new Filter("instance-state-name").withValues("running"));

    DescribeInstancesResult result = ec2.describeInstances(request);

    List<Reservation> reservations = result.getReservations();
    for (Reservation reservation : reservations) {

      // loop through the instances
      for (Instance instance : reservation.getInstances()) {
        String instanceName = "N/A";

        // extract the information from tags
        for (Tag tag : instance.getTags()) {
          if (tag.getKey().equals("Role")) {
            instanceName = tag.getValue();
            break;
          }
        }

        // print details of the instance
        System.out.printf(
            "Name: %s, State: %s%n",
            instanceName,
            instance.getState().getName()
        );
      }
    }
  }

}
