package kata.makers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LongestWord {

  public static String LongestWord(String sen) {
    // code goes here
    List<String> words = Arrays.asList(sen.replaceAll("[^A-Za-z\\s]", "").split(" "));
    return words.stream().max(Comparator.comparingInt(String::length)).get();
  }

}
