package my.cake.shop;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  public void shouldReturnName() {
    Assertions.assertEquals(
        new Cake(Size.LARGE, Colors.ORANGE, Type.CHEESE)
            .getName(),
        "large orange cheese cake");
  }

  @Test
  public void shouldReturnPricePounds() {
    Assertions.assertEquals(
        new Cake(Size.LARGE, Colors.ORANGE, Type.CHEESE)
            .getPricePounds(),
        50);
  }

  @Test
  public void shouldBeAbleToRename() {
    Cake cake = new Cake(Size.LARGE, Colors.ORANGE, Type.CHEESE);
    cake.setName("Caspar's amazing cake");

    Assertions.assertEquals(
        cake.getName(),
        "Caspar's amazing cake");
  }

  @SneakyThrows
  @Test
  public void shouldBeAbleToGetMinutesToExpiry() {
    Cake cake = new Cake(Size.LARGE, Colors.ORANGE, Type.CHEESE);

    Thread.sleep(1000);

    Assertions.assertEquals(
        7199,
        cake.expiresInMinutes());
  }
}