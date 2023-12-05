package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kata.makers.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

  private Product product;

  @BeforeEach
  void setUp() {
    product = new Product();
  }

  @Test
  void searchProductsWithKeyWord() throws CustomException {
    assertTrue(product.searchProduct("").isEmpty());
    assertEquals(5, product.searchProduct("*").size());
    assertThrows(CustomException.class,
        () -> product.searchProduct("ca"));
    assertTrue(product.searchProduct("erte").isEmpty());
    assertEquals(4, product.searchProduct("CAKE").size());
    assertEquals("Chocolate cake", product.searchProduct("CHOCO").get(0));

  }
}
