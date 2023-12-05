package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

  private ShoppingCart cart;

  @BeforeEach
  void setUp(){
    cart = new ShoppingCart();
  }

  @Test
  void testByAddingItemsToPriceListAndGetTotal() {
      cart.addItemToThePriceList(10.0);
      cart.addItemToThePriceList(10.0);
      cart.addItemToThePriceList(10.0);
      cart.addItemToThePriceList(10.0);
      assertEquals(40.0, cart.getTotalPrice() );
  }

  @Test
  void testByAddingNegativeAmountToPriceListAndGetTotal() {
    cart.addItemToThePriceList(10.0);
    cart.addItemToThePriceList(-10.0);
    cart.addItemToThePriceList(-10.0);
    cart.addItemToThePriceList(10.0);
    assertEquals(0.0, cart.getTotalPrice() );
  }
}
