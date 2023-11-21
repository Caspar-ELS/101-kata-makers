package kata.makers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CheckoutTest {

  @Test
  void shouldReturnSumOfStringNumbers() throws Exception {
    Checkout checkout = new Checkout();
    Assertions.assertEquals(6, checkout.calculateSum("1,2,3"));
  }

//  @Test
  @ParameterizedTest
  @ValueSource(strings ={"1,", "1\n"})
  void shouldNotAcceptInvalidCharacterAtTheEnd(String keyword) {
    Checkout checkout = new Checkout();
    Assertions.assertThrows(InvalidCharacterException.class, () -> checkout.calculateSum(keyword));
  }

  @Test
  void shouldNotAcceptSingleNumber() {
    Checkout checkout = new Checkout();
    Assertions.assertThrows(InvalidCharacterException.class, () -> checkout.calculateSum("1"));
  }
}
