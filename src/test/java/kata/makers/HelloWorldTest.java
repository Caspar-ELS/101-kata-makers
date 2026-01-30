package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    int minimumSize = 2;

    String tooSmall = cities.get(0).substring(0, minimumSize - 1);
    assertEquals(HelloWorld.findCity(tooSmall).size(), 0);

    String bigEnough = cities.get(0).substring(0, minimumSize);
    assertNotEquals(HelloWorld.findCity(bigEnough), 0);
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
