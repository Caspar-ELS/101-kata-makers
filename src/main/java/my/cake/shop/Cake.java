package my.cake.shop;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import my.cake.shop.exception.InvalidCakeException;

@Getter
@Setter
public class Cake {

  private String colour;
  private String type;
  private String size;
  private String name;
  private Long timeCreated;


  public Cake(String colour, String type, String size) {
    this.colour = colour;
    this.type = type;
    this.size = size;
    this.timeCreated = System.currentTimeMillis();
  }

  public Cake(String colour, String type, String size, String name) {
    this.colour = colour;
    this.type = type;
    this.size = size;
    this.name = name;
    this.timeCreated = System.currentTimeMillis();
  }

  public String printCakeName(Cake cake) {
    if (cake.getName() != null) {
      return cake.getName();
    }
    return cake.getSize() + " " + cake.getColour() + " " + cake.getType();
  }

  public int getCakePriceBasedOnSize(String cakeSize) throws InvalidCakeException {
    try {
      return cakePrices.get(cakeSize);
    } catch (NullPointerException exception) {
      throw new InvalidCakeException(exception);
    }
  }

  public String getCakeExpiry(Long timeCreated) {
    long timeDifference = (System.currentTimeMillis() - timeCreated) / 1000L;
    if (timeDifference <= 5) {
      return "Your cake will expire in " + timeDifference + " seconds";
    } else {
      return "Your cake expired :(";
    }
  }

  private static Map<String, Integer> cakePrices;
  static {
    cakePrices = new HashMap<>();
    cakePrices.put("small", 20);
    cakePrices.put("medium", 35);
    cakePrices.put("large", 50);
  }
}
