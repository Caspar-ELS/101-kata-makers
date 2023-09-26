package my.cake.shop;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  public void shouldHaveProperties() {
    Cake cake = new Cake(Size.SMALL, Colors.WHITE, Type.CHOCOLATE);

    Assertions.assertEquals(Size.SMALL, cake.getSize());
    Assertions.assertEquals(Colors.WHITE, cake.getColor());
    Assertions.assertEquals(Type.CHOCOLATE, cake.getType());
  }

  @Test
  public void shouldReturnName() {
    Assertions.assertEquals(
        new Cake(Size.LARGE, Colors.YELLOW, Type.ICE_CREAM)
            .getName(),
        "large yellow ice cream cake");
  }

  @Test
  public void shouldReturnPricePounds() {
    Assertions.assertEquals(
        new Cake(Size.LARGE, Colors.WHITE, Type.CHEESE)
            .getPricePounds(),
        50);

    Assertions.assertEquals(
        new Cake(Size.MEDIUM, Colors.WHITE, Type.CHOCOLATE)
            .getPricePounds(),
        35);

    Assertions.assertEquals(
        new Cake(Size.SMALL, Colors.WHITE, Type.ICE_CREAM)
            .getPricePounds(),
        20);

    // edge case, technically possible, should ask PO what they want in this case
    Assertions.assertNull(new Cake(null, Colors.WHITE, Type.ICE_CREAM)
        .getPricePounds());
  }

  @Test
  public void shouldBeAbleToRename() {
    Cake cake = new Cake(Size.MEDIUM, Colors.YELLOW, Type.ICE_CREAM);

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
  public void shouldReturnMinutesToExpiryRoundingDown() {
    Cake freshCake = new Cake(Size.MEDIUM, Colors.PURPLE, Type.CHOCOLATE);
    Thread.sleep(500);
    Assertions.assertEquals(
        2,
        freshCake.expiresInSeconds());

    Cake expiredCake = new Cake(Size.MEDIUM, Colors.PURPLE, Type.CHOCOLATE);
    Thread.sleep(5500);
    Assertions.assertEquals(
        0,
        expiredCake.expiresInSeconds());
  }
}