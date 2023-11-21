package kata.makers;

import java.util.Arrays;

public class Checkout {

  public int calculateSum(String numbersString) throws Exception {

    if (numbersString.contains(",")) {
      return calculateSumForCommaSeparatedNumbers(numbersString);
    } else if (numbersString.contains("\n")) {
      return calculateSumForNewLineSeparatedNumbers(numbersString);
    } else {
      throw new InvalidCharacterException("Cannot accept one character");
    }
  }

  private static int calculateSumForCommaSeparatedNumbers(String numbersString) throws Exception {
    if (numbersString.endsWith(",")) {
      throw new InvalidCharacterException("Cannot ends with comma");
    }
    return Arrays.stream(numbersString.split(",")).mapToInt(Integer::valueOf).sum();
  }

  private static int calculateSumForNewLineSeparatedNumbers(String numbersString) throws Exception {
    if (numbersString.endsWith("\n")) {
      throw new InvalidCharacterException("Cannot ends with new line");
    }
    return Arrays.stream(numbersString.split("\n")).mapToInt(Integer::valueOf).sum();
  }
}
