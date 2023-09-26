package my.cake.shop;

import java.util.Objects;
import lombok.Getter;
import my.cake.shop.exception.CakeException;

public class Cake {

  private static final int EXPIRY_TIME_IN_SECONDS = 5;

  private static final int PRICE_POUNDS_LARGE = 50;
  private static final int PRICE_POUNDS_MEDIUM = 35;
  private static final int PRICE_POUNDS_SMALL = 20;

  @Getter
  private final Size size;
  @Getter
  private final Colors color;
  @Getter
  private final Type type;

  private String name;

  @Getter
  private final long momentOfExpiryInUnixTime;

  public Cake(Size size, Colors color, Type type) {
    this.size = size;
    this.color = color;
    this.type = type;

    this.momentOfExpiryInUnixTime = getCurrentUnixTime() + EXPIRY_TIME_IN_SECONDS;
  }

  public String getName() {
    return Objects.requireNonNullElseGet(this.name, () -> (
        size.name() + " "
            + color.name() + " "
            + type.name()
            + " cake")
        .toLowerCase()
        .replace("_", " "));
  }

  public Integer getPricePounds() throws CakeException {
    if (size == Size.LARGE) {
      return PRICE_POUNDS_LARGE;
    }
    if (size == Size.MEDIUM) {
      return PRICE_POUNDS_MEDIUM;
    }
    if (size == Size.SMALL) {
      return PRICE_POUNDS_SMALL;
    }
    throw new CakeException("Can't determine size and therefore price of cake");
  }

  public int expiresInSeconds() {
    long result = momentOfExpiryInUnixTime - getCurrentUnixTime();
    return Math.toIntExact(result < 0 ? 0 : result);
  }

  public void rename(String newName) {
    this.name = newName;
  }

  private static long getCurrentUnixTime() {
    return System.currentTimeMillis() / 1000L;
  }
}
