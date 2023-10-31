package my.cake.shop;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cake {

  private static final int TIME_TO_LIVE_MS = 5000;
  private static final double SMALL_CAKE_PRICE = 20.00;
  private static final double MEDIUM_CAKE_PRICE = 35.00;
  private static final double LARGE_CAKE_PRICE = 50.00;
  private Color color;
  private Size size;
  private Type type;
  private String name;
  private Double price;
  private long timeCakeMade;

  public Cake(Color color, Size size, Type type) {
    this.color = color;
    this.size = size;
    this.type = type;
    setName(color, size, type);
    setPriceBasedOnSize(size);
    setTimeCakeMade(System.currentTimeMillis());
  }

  public Cake(Color color, Size size, Type type, String name) {
    this.color = color;
    this.size = size;
    this.type = type;
    this.name = name;
  }

  public void setName(Color color, Size size, Type type) {
    name = size.name() + " " + color.name() + " " + type.name();
  }

  public void rename(String name) {
    setName(name);
  }

  public void setPriceBasedOnSize(Size size) {
    if (size.equals(Size.SMALL)) {
      setPrice(SMALL_CAKE_PRICE);
    } else if (size.equals(Size.MEDIUM)) {
      setPrice(MEDIUM_CAKE_PRICE);
    } else if (size.equals(Size.LARGE)) {
      setPrice(LARGE_CAKE_PRICE);
    }
  }

  public String checkHowMuchTimeLeft() {
    long stop = System.currentTimeMillis();
    long timePassed = stop - getTimeCakeMade();
    long timeLeft;

    if (timePassed > TIME_TO_LIVE_MS) {
      return "Expired";
    } else {
      timeLeft = TIME_TO_LIVE_MS - timePassed;
      return "Time left: " + timeLeft + " milliseconds";
    }
  }
}
