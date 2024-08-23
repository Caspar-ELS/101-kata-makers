package kata.makers;

import filter.BomServiceFilter;
import java.util.Scanner;
import service.Ec2Service;

public class Application {
  public static void main(String[] args){
    BomServiceFilter bomServiceFilter = new BomServiceFilter();
    Ec2Service ec2Service = new Ec2Service(bomServiceFilter);


    Scanner scanner = new Scanner(System.in);

    System.out.println("Please select an option:");
    System.out.println("1. Show all running instances in DEV");
    System.out.println("2. Show all instances by component");
    System.out.println("3. Option 3");
    System.out.print("Enter your choice (1, 2, or 3): ");

    int option = scanner.nextInt();

    switch (option) {
      case 1:
        System.out.println("You selected Option 1.");
        break;
      case 2:
        System.out.println("You selected Option 2.");
        break;
      case 3:
        System.out.println("You selected Option 3.");
        break;
      default:
        System.out.println("Invalid option. Please enter 1, 2, or 3.");
        break;
    }

    scanner.close();

    ec2Service.printInstancesByComponent();

    System.out.println("===================================");
    ec2Service.printRunningInstances();
  }

}
