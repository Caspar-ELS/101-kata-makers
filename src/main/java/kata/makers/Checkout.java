package kata.makers;

import java.util.Arrays;
import kata.makers.exception.InvalidPricesException;

public class Checkout {

  public int getTotal(String prices) throws InvalidPricesException {
    if (prices.length() == 1 || prices.length() == 2) {
      throw new InvalidPricesException("Cannot contain only one price");
    }
    if (prices.substring(prices.length() -1).contains("\n")) {
      throw new InvalidPricesException("Prices must end with a number");
    }
    String[] pricesArr = prices.split("\n");
    return Arrays.stream(pricesArr).mapToInt(Integer::parseInt).sum();
  }
}
