package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class MainTest {

  @Test
  public void testMain() {
    List<Map<String, String>> result = InventorySystem.readProductCsv(
        "src/test/java/kata/makers/valid_products.csv");
    assertValidRow("3", "Pixel 5", "50", result, 0);
    assertValidRow("6", "iPhone 3GS", "30", result, 1);
    assertValidRow("9", "Macbook", "20", result, 2);
  }

  @Test
  public void testEmptyFile() {
    List<Map<String, String>> result = InventorySystem.readProductCsv("non_existing_file.csv");
    assertValidRow("1", "Dummy Product 1", "10", result, 0);
    assertValidRow("2", "Dummy Product 2", "20", result, 1);
  }

  private void assertValidRow(String expectedId, String expectedProductName,
      String expectedQuantity, List<Map<String, String>> rows, int rowNumber) {
    checkId(expectedId, rows.get(rowNumber));
    checkProductName(expectedProductName, rows.get(rowNumber));
    checkQuantity(expectedQuantity, rows.get(rowNumber));
  }

  private void checkId(String expectedId, Map<String, String> result) {
    assertEquals(expectedId, result.get("ID"));
  }

  private void checkProductName(String expectedProductName, Map<String, String> result) {
    assertEquals(expectedProductName, result.get("product name"));
  }

  private void checkQuantity(String expectedQuantity, Map<String, String> result) {
    assertEquals(expectedQuantity, result.get("quantity"));
  }
}