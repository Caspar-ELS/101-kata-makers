package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.regex.Pattern;
import kata.makers.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

class CheckoutTest {

  @Test
  void willNotAcceptAStringWithASingleNumber() {
    Checkout checkout = new Checkout();
    String prices = "1";

    assertThrows(InvalidInputException.class, () -> checkout.getTotal(prices, "\n"));
  }

  @Test
  void willNotAcceptAStringWithASingleNumberFollowedByANewLine() {
    Checkout checkout = new Checkout();
    String prices = "1\n";

    assertThrows(InvalidInputException.class, () -> checkout.getTotal(prices, "\n"));
  }

  @Test
  void willNotAcceptAStringWithANewLineAtTheEnd() {
    Checkout checkout = new Checkout();
    String prices = "1\n2\n3\n";

    assertThrows(InvalidInputException.class, () -> checkout.getTotal(prices, Pattern.quote("\n")));
  }

  @Test
  void willSumStringWithValidDelimiter() throws InvalidInputException {
    Checkout checkout = new Checkout();
    String pricesNewLine = "1\n2\n3\n";
    String pricesComma = "1,2,3";
    String pricesSquareBrackets = "1]2]3";

    assertEquals(6, checkout.getTotal(pricesSquareBrackets, "]"));
    assertEquals(6, checkout.getTotal(pricesNewLine, "\n"));
    assertEquals(6, checkout.getTotal(pricesComma, ","));
  }

  @Test
  void willThrowExceptionWithInvalidDelimiter() {
    Checkout checkout = new Checkout();
    String pricesWithNoDelimiter = "123";
    String pricesWithTooLongDelimiter = "1,,2,,3";

    assertThrows(InvalidInputException.class, () -> checkout.getTotal(pricesWithNoDelimiter, ""));
    assertThrows(InvalidInputException.class, () -> checkout.getTotal(pricesWithTooLongDelimiter, ",,"));

  }

}
