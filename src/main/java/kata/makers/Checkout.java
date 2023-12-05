package kata.makers;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

public class Checkout {

  public int calculateSum(String numbersString) throws Exception {

    validateStringLength(numbersString);
    String delimiter = getDelimiter(numbersString);

    if (numbersString.endsWith(delimiter)) {
      throw new InvalidCharacterException("Cannot end with comma");
    }
    return Arrays.stream(numbersString.split(delimiter)).filter(e -> !e.equals(""))
        .mapToInt(Integer::valueOf).sum();
  }

  private static String getDelimiter(String numbersString) {

    String[] list = numbersString.split("");
    String delimiter = null;
    for (String element : list) {
      if (!StringUtils.isNumeric(element)) {
        delimiter = element;
      }
    }
    return delimiter;
  }

  private static void validateStringLength(String numbersString) throws InvalidCharacterException {
    if (numbersString.length() < 2) {
      throw new InvalidCharacterException("Cannot accept single number");
    }
  }
}
