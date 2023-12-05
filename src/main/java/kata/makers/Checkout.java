package kata.makers;

import java.util.Arrays;
import java.util.regex.Pattern;
import kata.makers.exception.InvalidInputException;

public class Checkout {

  public int getTotal(String prices, String delimiter) throws InvalidInputException {
    validateDelimiter(delimiter);
    if (prices.length() <= 2) {
      throw new InvalidInputException("Cannot contain only one price");
    }
    if (prices.substring(prices.length() - 1).contains(Pattern.quote(delimiter))) {
      throw new InvalidInputException("Prices must end with a number");
    }
    String[] pricesArr = prices.split(Pattern.quote(delimiter));
    return Arrays.stream(pricesArr).mapToInt(Integer::parseInt).sum();
  }

  private void validateDelimiter(String delimiter) throws InvalidInputException {
    if (delimiter == null || delimiter.isEmpty()) {
      throw new InvalidInputException("Delimiter can't be empty or null");
    } else if (delimiter.length() >= 2) {
      throw new InvalidInputException("Delimiter is too long");
    }
  }
}
