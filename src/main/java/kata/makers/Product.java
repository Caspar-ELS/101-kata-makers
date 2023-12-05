package kata.makers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kata.makers.exception.CustomException;

public class Product {

  public static final List<String> PRODUCTS = Arrays.asList("Apple cake", "Banana cake",
      "Chocolate cake", "Vanilla cake", "Madeleine");
  public List<String> searchProduct(String keyword) throws CustomException {
    switch (keyword.trim()) {
      case "" -> {
        return List.of();
      }
      case "*" -> {
        return PRODUCTS;
      }
      default -> {
        if (keyword.trim().length() <= 2) {
          throw new CustomException("keyword is too short");
        }
        List<String> searchResult = new ArrayList<>();
        for (String cakeName : PRODUCTS) {
          if (cakeName.toLowerCase().contains(keyword.toLowerCase()))
            searchResult.add(cakeName);
        }
        return searchResult;
      }
    }
  }
}
