package kata.makers;

import enums.Component;
import enums.Test;
import service.Ec2Service;

public class Application {
  public static void main(String[] args){
    Ec2Service ec2Service = new Ec2Service();

//    ec2Service.listInstancesByComponent(Component.FULFILLMENT);
//    ec2Service.listAllDevRunningInstances();
    ec2Service.listComponentsToStartForRegressionTests(
        Test.ACCOUNTS_RECEIVABLES_REVENUE_RECOGNITION);
  }

}
