package kata.makers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

  @Test
  void shouldAddPriceToTheListOfPrices() {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addPrice(10);
    assertEquals(List.of(10), shoppingCart.getPrices());
  }

  @Test
  void shouldCalculateTotalPriceBasedOnTheListOfPrices() {
    ShoppingCart shoppingCart = new ShoppingCart(List.of(10, 20, 30));
    assertEquals(60, shoppingCart.calculateTotal());
  }
}