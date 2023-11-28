package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import kata.makers.exception.InvalidPricesException;
import org.junit.jupiter.api.Test;

class CheckoutTest {

  @Test
  void willSumStringSeparatedByNewLines() throws InvalidPricesException {
    Checkout checkout = new Checkout();
    String prices = "1\n2\n3\n4\n5";

    assertEquals(15, checkout.getTotal(prices));
  }

  @Test
  void willNotAcceptAStringWithASingleNumber() throws InvalidPricesException {
    Checkout checkout = new Checkout();
    String prices = "1";

    assertThrows(InvalidPricesException.class, () -> checkout.getTotal(prices));
  }

  @Test
  void willNotAcceptAStringWithASingleNumberFollowedByANewLine() throws InvalidPricesException {
    Checkout checkout = new Checkout();
    String prices = "1\n";

    assertThrows(InvalidPricesException.class, () -> checkout.getTotal(prices));
  }

  @Test
  void willNotAcceptAStringWithANewLineAtTheEnd() throws InvalidPricesException {
    Checkout checkout = new Checkout();
    String prices = "1\n2\n3\n";

    assertThrows(InvalidPricesException.class, () -> checkout.getTotal(prices));
  }

}
