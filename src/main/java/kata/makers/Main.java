package kata.makers;

import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    String path = "products.csv";

    List<Map<String, String>> products = InventorySystem.readProductCsv(path);

    for (Map<String, String> product : products) {
      System.out.println(product);
    }
  }

}
