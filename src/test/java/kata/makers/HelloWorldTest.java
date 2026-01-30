package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

  private static final List<String> cities = List.of("Paris", "Budapest", "Skopje", "Rotterdam",
      "Valencia", "Vancouver", "Amsterdam", "Vienna", "Sydney", "New York City", "London",
      "Bangkok", "Hong Kong", "Dubai", "Rome", "Istanbul");

  @Test
  public void shouldReturnNullWhenQueryTooShort() {
    assertNull(HelloWorld.findCity(""));
    assertNull(HelloWorld.findCity("a"));
    assertNotNull(HelloWorld.findCity("ab"));
  }

  @Test
  public void shouldFindCityStartsWith() {
    cities.forEach(city -> {

      String shortCity = city.substring(0, 2);
      System.out.println("Now testing \"" + city + "\", short \"" + shortCity + "\"");

      List<String> result = HelloWorld.findCity(shortCity);
      assertTrue(result.contains(city));

    });
  }

  @Test
  public void shouldFindCitySubstringAnywhere() {
    cities.forEach(city -> {

      String shortCity = city.substring(2);
      System.out.println("Now testing \"" + city + "\", short \"" + shortCity + "\"");

      List<String> result = HelloWorld.findCity(shortCity);
      assertTrue(result.contains(city));

    });
  }

  @Test
  public void shouldBeCaseInsensitive() {
    List<String> result = HelloWorld.findCity("pa");
    assertTrue(result.contains("Paris"));
  }

  @Test
  public void shouldReturnAllOnAsterisk() {
    List<String> result = HelloWorld.findCity("*");
    assertEquals(result, cities);
  }

  @Test
  public void shouldThrowOnNull() {
    assertThrows(NullPointerException.class, () -> HelloWorld.findCity(null));
  }
}
