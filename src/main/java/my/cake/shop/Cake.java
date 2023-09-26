package my.cake.shop;

import lombok.Getter;

public class Cake {

  public static final long ONE_HOUR_IN_SECONDS = 3600L;
  @Getter
  private final Size size;
  @Getter
  private final Colors color;
  @Getter
  private final Type type;

  @Getter
  private final long expiryInSeconds;

  @Getter
  private String name;

  public Cake(Size size, Colors color, Type type) {
    this.size = size;
    this.color = color;
    this.type = type;

    this.name = (size.name() + " " + color.name() + " " + type.name() + " cake").toLowerCase().replace("_", " ");

    this.expiryInSeconds = (System.currentTimeMillis() / 1000L) + ONE_HOUR_IN_SECONDS;
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

  public void rename(String newName) {
    this.name = newName;
  }
}
