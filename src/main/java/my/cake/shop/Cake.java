package my.cake.shop;

import lombok.Getter;
import lombok.Setter;

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

  public String getPrice(String cakeSize) {
    return cakeSize.equals("large") ? "£50" : "£20";
  }

  public String getCakeExpiry(Long timeCreated) {
    long timeDifference = (System.currentTimeMillis() - timeCreated) / 1000L;
    if (timeDifference <= 5) {
      return "Your cake will expire in " + timeDifference + " seconds";
    } else {
      return "Your cake expired :(";
    }
  }
}
