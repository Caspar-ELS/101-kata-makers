package kata.makers;

import service.Ec2Service;

public class Application {
  public static void main(String[] args){
    Ec2Service ec2Service = new Ec2Service();

    ec2Service.listBomFeatures();
  }

}
