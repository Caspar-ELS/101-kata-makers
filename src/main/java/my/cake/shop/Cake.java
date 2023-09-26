package my.cake.shop;

import lombok.Getter;

public class Cake {

  public static final long FIVE_SECONDS = 5L;
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

    this.expiryInSeconds = (System.currentTimeMillis() / 1000L) + FIVE_SECONDS;
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

  public long expiresInSeconds() {
    long result = (expiryInSeconds - System.currentTimeMillis() / 1000L);
    return result < 0 ? 0 : result;
  }

  public void rename(String newName) {
    this.name = newName;
  }
}
