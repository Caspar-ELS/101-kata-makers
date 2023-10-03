package my_cake_shop;

import java.util.Objects;
import lombok.Getter;
import my_cake_shop.cake_properties.Sizes;
import my_cake_shop.cake_properties.Types;
import my_cake_shop.exception.CakeException;
import my_cake_shop.cake_properties.Colors;

public class Cake {

  private static final int EXPIRY_TIME_IN_SECONDS = 5;

  private static final int PRICE_POUNDS_LARGE = 50;
  private static final int PRICE_POUNDS_MEDIUM = 35;
  private static final int PRICE_POUNDS_SMALL = 20;

  @Getter
  private final Sizes sizes;
  @Getter
  private final Colors color;
  @Getter
  private final Types types;

  private String name;

  @Getter
  private final long momentOfExpiryInUnixTime;
  @Getter
  private final String id;

  public Cake(Sizes sizes, Colors color, Types types) {
    this.sizes = sizes;
    this.color = color;
    this.types = types;

    this.momentOfExpiryInUnixTime = getCurrentUnixTime() + EXPIRY_TIME_IN_SECONDS;

    this.id = java.util.UUID.randomUUID().toString();
  }

  public String getName() {
    return Objects.requireNonNullElseGet(this.name, () -> (
        sizes.name() + " "
            + color.name() + " "
            + types.name()
            + " cake")
        .toLowerCase()
        .replace("_", " "));
  }

  public Integer getPrice() throws CakeException {
    if (sizes == Sizes.LARGE) {
      return PRICE_POUNDS_LARGE;
    }
    if (sizes == Sizes.MEDIUM) {
      return PRICE_POUNDS_MEDIUM;
    }
    if (sizes == Sizes.SMALL) {
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
