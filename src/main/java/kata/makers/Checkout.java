package kata.makers;

import java.util.regex.Pattern;
import kata.makers.exception.CustomException;

public class Checkout {

  public boolean isValidInput(String input, String delimiter) throws CustomException {
    if(delimiter == null || delimiter.length() != 1){
      throw new CustomException("Invalid Delimiter");
    }
    int lastIndex = input.lastIndexOf(delimiter);
    String[] split = input.split(Pattern.quote(delimiter));
    return split.length>1 && split.length == lastIndex-1;
  }

  public int sum(String input, String delimiter) throws CustomException {
    int sum =0;
   try {
     if (isValidInput(input, delimiter)) {
       String[] split = input.split(Pattern.quote(delimiter));
       for (String splitChar : split) {
         sum += Integer.parseInt(splitChar);
       }
     }
   }catch (NumberFormatException exception){
     throw new CustomException("Input contains non-numerical characters");
   }
    return sum;
  }
}
