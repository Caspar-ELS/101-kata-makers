package kata.makers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kata.makers.exception.InvalidCakeTypeException;

public class Product {

  public static final List<String> PRODUCTS = Arrays.asList("Apple cake", "Banana cake",
      "Chocolate cake", "Vanilla cake", "Madeleine");

  public List<String> findCakes(String cakeType) throws InvalidCakeTypeException {
    List<String> availableCakes = new ArrayList<>();
    if (cakeType.isEmpty()) {
      return availableCakes;
    }
    if (cakeType.equals("*")) {
      return PRODUCTS;
    }
    if (cakeType.length() <= 2) {
      throw new InvalidCakeTypeException("Cake must contain at least three characters");
    }
    for (String cake : PRODUCTS) {
      if (cake.contains(cakeType)) {
        availableCakes.add(cake);
      }
    }
    return availableCakes;
  }
}
