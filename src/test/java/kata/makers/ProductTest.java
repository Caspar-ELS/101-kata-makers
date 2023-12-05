package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import kata.makers.exception.InvalidCakeTypeException;
import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  void willReturnCustomersChosenCake() throws InvalidCakeTypeException {

    Product product = new Product();

    List<String> expectedCakes = List.of("Chocolate cake");

    assertEquals(expectedCakes, product.findCakes("Choc"));
  }

  @Test
  void willNotAcceptACakeTypeWithTwoCharacters() {
    Product product = new Product();

    assertThrows(InvalidCakeTypeException.class, () -> product.findCakes("Ch"));

  }

  @Test
  void willReturnAllAvailableCakesWhenSearchingWithAnAsterisk() throws InvalidCakeTypeException {
    Product product = new Product();

    List<String> expectedCakes = List.of("Apple cake", "Banana cake",
        "Chocolate cake", "Vanilla cake", "Madeleine");

    assertEquals(expectedCakes, product.findCakes("*"));

  }

  @Test
  void willReturnEmptyListIfEmptyStringProvided() throws InvalidCakeTypeException {
    Product product = new Product();

    assertEquals(new ArrayList<>(), product.findCakes(""));
  }

  @Test
  void willReturnEmptyListIfNoCakeIsFound() throws InvalidCakeTypeException {
    Product product = new Product();

    assertEquals(new ArrayList<>(), product.findCakes("Cheese"));
  }

}
