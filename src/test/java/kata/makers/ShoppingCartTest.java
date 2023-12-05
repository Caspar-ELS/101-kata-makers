package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ShoppingCartTest {

  @Test
  void willAddItemsToTheShoppingCart() {
    ShoppingCart shoppingCart = new ShoppingCart();

    shoppingCart.addItemPriceToCart(60.00);
    shoppingCart.addItemPriceToCart(30.00);
    shoppingCart.addItemPriceToCart(20.00);

    assertEquals(3, shoppingCart.getProductPrices().size());

  }

  @Test
  void willCalculateTotalPriceInShoppingCart() {
    ShoppingCart shoppingCart = new ShoppingCart();

    shoppingCart.addItemPriceToCart(60.00);
    shoppingCart.addItemPriceToCart(30.00);
    shoppingCart.addItemPriceToCart(20.00);

    assertEquals(110.00, shoppingCart.getTotalPrice());
  }

}
