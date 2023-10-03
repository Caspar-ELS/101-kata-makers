package my.cake.shop;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cake {

  private static final int TIME_TO_LIVE_MS = 5000;
  private Color color;
  private Size size;
  private Type type;
  private String name;
  private Double price;
  private long timeCakeMade;

  public static Cake makeCake(Color color, Size size, Type type) {
    Cake cake = new Cake();
    cake.setColor(color);
    cake.setSize(size);
    cake.setType(type);
    cake.setName(color, size, type);
    cake.setTimeCakeMade(System.currentTimeMillis());
    return cake;
  }

  public static Cake makeCake(Color color, Size size, Type type, String name) {
    Cake cake = new Cake();
    cake.setColor(color);
    cake.setSize(size);
    cake.setType(type);
    cake.setName(name);
    cake.setTimeCakeMade(System.currentTimeMillis());
    return cake;
  }

  public void setName(Color color, Size size, Type type) {
    name = size.name() + " " + color.name() + " " + type.name();
  }

  public Double setPriceBasedOnSize() {
    Cake cake = makeCake(color, size, type);
    if (cake.getSize().equals(Size.SMALL)) {
      cake.setPrice(20.00);
    } else if (cake.getSize().equals(Size.MEDIUM)) {
      cake.setPrice(35.00);
    } else if (cake.getSize().equals(Size.LARGE)) {
      cake.setPrice(50.00);
    }
    return cake.getPrice();
  }

  public static String checkHowMuchTimeLeft(Cake cake) {
    long stop = System.currentTimeMillis();
    long timePassed = stop - cake.getTimeCakeMade();
    long timeLeft;

    if (timePassed > TIME_TO_LIVE_MS) {
      return "Expired";
    } else {
      timeLeft = TIME_TO_LIVE_MS - timePassed;
      return "Time left: " + timeLeft + " milliseconds";
    }
  }
}
