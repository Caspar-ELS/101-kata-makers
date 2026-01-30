package kata.makers;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {

  private static final List<String> allCities = List.of("Paris", "Budapest", "Skopje", "Rotterdam",
      "Valencia", "Vancouver", "Amsterdam", "Vienna", "Sydney", "New York City", "London",
      "Bangkok", "Hong Kong", "Dubai", "Rome", "Istanbul");

  public static List<String> findCity(String find) {
    if (find.equals("*")) return allCities;

    List<String> result = new ArrayList<>();

    if (find.length() < 2) return result;

    allCities.forEach(city -> {
      if (city.toLowerCase().contains(find.toLowerCase())) {
        result.add(city);
      }
    });

    return result;

  }
}
