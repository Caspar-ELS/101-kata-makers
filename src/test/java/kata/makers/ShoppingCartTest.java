package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import kata.makers.exception.DuplicateItemException;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

  @Test
  void willAddItemsToTheShoppingCart() throws DuplicateItemException {
    ShoppingCart shoppingCart = new ShoppingCart();

    shoppingCart.addItemPriceToCart("chocolate cake", 60.00);
    shoppingCart.addItemPriceToCart("carrot cake", 30.00);
    shoppingCart.addItemPriceToCart("cheese cake", 20.00);

    assertEquals(3, shoppingCart.getShoppingList().size());

  }

  @Test
  void willCalculateTotalPriceInShoppingCart() throws DuplicateItemException {
    ShoppingCart shoppingCart = new ShoppingCart();

    shoppingCart.addItemPriceToCart("chocolate cake", 60.00);
    shoppingCart.addItemPriceToCart("carrot cake", 30.00);
    shoppingCart.addItemPriceToCart("cheese cake", 20.00);

    assertEquals(110.00, shoppingCart.calculateTotal(0));
  }

  @Test
  void willNotAllowDuplicateItems() throws DuplicateItemException {
    ShoppingCart shoppingCart = new ShoppingCart();

    shoppingCart.addItemPriceToCart("chocolate cake", 60.00);
    assertThrows(DuplicateItemException.class, () -> shoppingCart.addItemPriceToCart("chocolate cake", 30.00));
    assertEquals(60.00, shoppingCart.calculateTotal(0));

  }

  @Test
  void willApplyDiscountWhenProvided() throws DuplicateItemException {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItemPriceToCart("chocolate cake", 60.00);

    assertEquals(54.00, shoppingCart.calculateTotal(0.10));
    assertEquals(45.00, shoppingCart.calculateTotal(0.25));
  }

}
