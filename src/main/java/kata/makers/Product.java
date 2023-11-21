package kata.makers;

import java.util.Arrays;
import java.util.List;

public class Product {

  public static final List<String> PRODUCTS = Arrays.asList("Apple cake", "Banana cake",
      "Chocolate cake", "Vanilla cake", "Madeleine");

  public List<String> searchByKeyword(String keyword) throws KeywordTooShortException {

    if (keyword.equals("*")) {
      return PRODUCTS;
    } else if (keyword.equals("")) {
      return List.of();
    } else if (keyword.length() <= 2) {
      throw new KeywordTooShortException("Keyword has to have at least 3 characters");
    } else {
      return PRODUCTS.stream()
          .map(String::toLowerCase)
          .filter(e -> e.contains(keyword.toLowerCase()))
          .map(e -> e.substring(0, 1).toUpperCase() + e.substring(1))
          .toList();
    }
  }
}
