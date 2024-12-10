package kata.makers;

import java.io.IOException;
import java.util.Scanner;
import kata.makers.converter.XmlToJsonConverter;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        XmlToJsonConverter converter = new XmlToJsonConverter();

        System.out.println("Enter XML string:");
        String xmlInput = scanner.nextLine();

        System.out.println("Choose option 1 or 2:");
        System.out.println("1. Convert XML to JSON string");
        System.out.println("2. Convert XML to JSON file");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            String jsonOutput = converter.convertXmlToJson(xmlInput);

            switch (choice) {
                case 1:
                    System.out.println("JSON Output:");
                    System.out.println(jsonOutput);
                    break;
                case 2:
                    converter.convertXmlToJsonFile(jsonOutput);
                    System.out.println("JSON file created, file named: output.json");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        scanner.close();
    }

}
