package kata.makers;

import java.util.Scanner;
import service.Ec2Service;

public class Application {
  public static void main(String[] args){
    Ec2Service ec2Service = new Ec2Service();

    Scanner scanner = new Scanner(System.in);

    System.out.println("Please select an option:");
    System.out.println("1. Show all running instances in DEV");
    System.out.println("2. Show all instances by component");

    int option = scanner.nextInt();

    switch (option) {
      case 1:
        System.out.println("Printing running instances...");
        ec2Service.printRunningInstances();
        break;
      case 2:
        System.out.println("Printing instances by component...");
        ec2Service.printInstancesByComponent();
        break;
      default:
        System.out.println("Invalid option. Please enter 1 or 2.");
        break;
    }

    scanner.close();
  }

}
