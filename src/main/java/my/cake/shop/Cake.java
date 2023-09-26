package my.cake.shop;

import lombok.Getter;
import lombok.Setter;

public class Cake {

  public static final int FIVE_DAYS_IN_SECONDS = 432000;
  private final Size size;
  private final Colors color;
  private final Type type;

  @Getter
  private final long expiryInSeconds;

  @Getter
  @Setter
  private String name;

  public Cake(Size size, Colors color, Type type) {
    this.size = size;
    this.color = color;
    this.type = type;

    this.name = (size.name() + " " + color.name() + " " + type.name() + " cake").toLowerCase();

    this.expiryInSeconds = (System.currentTimeMillis() / 1000L) + FIVE_DAYS_IN_SECONDS;
  }

  public int getPricePounds() {
    switch (size) {
      case LARGE -> {
        return 50;
      }
      case MEDIUM -> {
        return 35;
      }
      case SMALL -> {
        return 20;
      }
      default -> {
        return 0;
      }
    }
  }

  public long expiresInMinutes() {
    long result = (expiryInSeconds - System.currentTimeMillis() / 1000L) / 60;
    return result < 0 ? 0 : result;
  }
}
