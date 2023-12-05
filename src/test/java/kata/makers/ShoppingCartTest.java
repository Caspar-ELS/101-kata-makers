package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ShoppingCartTest {

  @Test
  void shouldAbleToAddItems() throws Exception {
    ShoppingCart cart = new ShoppingCart();

    cart.addItem(new ShoppingCartItem("Item 1", 15.0));
    cart.addItem(new ShoppingCartItem("Item 2", 16.0));
    cart.addItem(new ShoppingCartItem("Item 3", 17.0));

    assertEquals(cart.shoppingCartItems.get(0).getName(), "Item 1");
    assertEquals(cart.shoppingCartItems.get(0).getPrice(), 15.0);

    assertEquals(cart.shoppingCartItems.get(1).getName(), "Item 2");
    assertEquals(cart.shoppingCartItems.get(1).getPrice(), 16.0);

    assertEquals(cart.shoppingCartItems.get(2).getName(), "Item 3");
    assertEquals(cart.shoppingCartItems.get(2).getPrice(), 17.0);
  }

  @Test
  void shouldThrowException() throws Exception {
    ShoppingCart cart = new ShoppingCart();
    String itemName = "Some product name";
    cart.addItem(new ShoppingCartItem(itemName, 15.0));

    assertThrows(Exception.class, () -> cart.addItem(new ShoppingCartItem(itemName, 15.0)));
  }

  @Test
  void shouldCalculateOriginalPriceIfThereIsNoDiscount() throws Exception {
    ShoppingCart cart = new ShoppingCart();

    cart.addItem(new ShoppingCartItem("Item 1", 10.0));
    cart.addItem(new ShoppingCartItem("Item 2", 20.0));
    cart.addItem(new ShoppingCartItem("Item 3", 30.0));

    cart.setDiscountPercentOff(null);

    assertEquals(cart.calculateTotalPrice(), 60.0);
  }

  @Test
  void shouldCalculateDiscountPriceIfThereIsSimpleDiscount() throws Exception {
    ShoppingCart cart = new ShoppingCart();

    cart.addItem(new ShoppingCartItem("Item 1", 10.0));
    cart.addItem(new ShoppingCartItem("Item 2", 20.0));
    cart.addItem(new ShoppingCartItem("Item 3", 30.0));

    cart.setDiscountPercentOff(20.0);

    assertEquals(48.0, cart.calculateTotalPrice());

  }

  @Test
  void shouldCalculateDiscountPriceIfThereIsComplicateDiscount() throws Exception {
    ShoppingCart cart = new ShoppingCart();

    cart.addItem(new ShoppingCartItem("Item 1", 10.0));
    cart.addItem(new ShoppingCartItem("Item 2", 20.0));
    cart.addItem(new ShoppingCartItem("Item 3", 30.0));

    cart.setDiscountPercentOff(23.1);

    assertEquals(46.14, cart.calculateTotalPrice());

  }

}
