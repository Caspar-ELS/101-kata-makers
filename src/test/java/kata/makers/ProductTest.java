package kata.makers;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

  @Test
  void shouldThrowExceptionIfKeywordIsNotAtLeastThreeCharactersLong() {
    Product product = new Product();
    Assertions.assertThrows(KeywordTooShortException.class, () -> product.searchByKeyword("ab"));
  }

  @Test
  void shouldReturnAllProductsForAsterisk() throws KeywordTooShortException {
    Product product = new Product();
    Assertions.assertEquals(Product.PRODUCTS, product.searchByKeyword("*"));
  }

  @Test
  void shouldReturnEmptyListForEmptyKeyword() throws KeywordTooShortException {
    Product product = new Product();
    Assertions.assertEquals(List.of(), product.searchByKeyword(""));
  }

  @Test
  void shouldReturnEmptyListForNoResult() throws KeywordTooShortException {
    Product product = new Product();
    Assertions.assertEquals(List.of(), product.searchByKeyword("rvfcd"));
  }

  @Test
  void shouldReturnSearchResult() throws KeywordTooShortException {
    Product product = new Product();
    Assertions.assertEquals(List.of("Banana cake"), product.searchByKeyword("ban"));
    Assertions.assertEquals(
        List.of("Apple cake", "Banana cake", "Chocolate cake", "Vanilla cake"),
        product.searchByKeyword("cak"));
  }
}
