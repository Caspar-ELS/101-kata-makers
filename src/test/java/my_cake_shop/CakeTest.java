package my_cake_shop;

import lombok.SneakyThrows;
import my_cake_shop.cake_properties.Sizes;
import my_cake_shop.cake_properties.Types;
import my_cake_shop.exception.CakeException;
import my_cake_shop.cake_properties.Colors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  public void shouldHaveProperties() {
    Cake cake = new Cake(Sizes.SMALL, Colors.WHITE, Types.CHOCOLATE);

    Assertions.assertEquals(Sizes.SMALL, cake.getSizes());
    Assertions.assertEquals(Colors.WHITE, cake.getColor());
    Assertions.assertEquals(Types.CHOCOLATE, cake.getTypes());
  }

  @Test
  public void shouldReturnName() {
    Assertions.assertEquals(
        new Cake(Sizes.LARGE, Colors.YELLOW, Types.ICE_CREAM)
            .getName(),
        "large yellow ice cream cake");
  }

  @SneakyThrows
  @Test
  public void shouldReturnPrice() {
    Assertions.assertEquals(
        new Cake(Sizes.LARGE, Colors.WHITE, Types.CHEESE)
            .getPrice(),
        50);

    Assertions.assertEquals(
        new Cake(Sizes.MEDIUM, Colors.WHITE, Types.CHOCOLATE)
            .getPrice(),
        35);

    Assertions.assertEquals(
        new Cake(Sizes.SMALL, Colors.WHITE, Types.ICE_CREAM)
            .getPrice(),
        20);

    Assertions.assertThrows(
        CakeException.class,
        () -> new Cake(null, Colors.WHITE, Types.ICE_CREAM).getPrice());
  }

  @Test
  public void shouldBeAbleToRename() {
    Cake cake = new Cake(Sizes.MEDIUM, Colors.YELLOW, Types.ICE_CREAM);

    Assertions.assertEquals(
        cake.getName(),
        "medium yellow ice cream cake");

    cake.rename("Caspar's amazing cake");

    Assertions.assertEquals(
        cake.getName(),
        "Caspar's amazing cake");
  }

  @SneakyThrows
  @Test
  public void shouldReturnSecondsLeftToExpiryFromFiveSecondsRoundingDown() {
    Cake freshCake = new Cake(Sizes.MEDIUM, Colors.PURPLE, Types.CHOCOLATE);
    Thread.sleep(1000);
    Assertions.assertEquals(
        4,
        freshCake.expiresInSeconds());

    Cake expiredCake = new Cake(Sizes.MEDIUM, Colors.PURPLE, Types.CHOCOLATE);
    Thread.sleep(6000);
    Assertions.assertEquals(
        0,
        expiredCake.expiresInSeconds());
  }
}