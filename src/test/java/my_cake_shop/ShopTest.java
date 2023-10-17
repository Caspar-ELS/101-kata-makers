package my_cake_shop;

import lombok.SneakyThrows;
import my_cake_shop.cake_properties.Colors;
import my_cake_shop.cake_properties.Sizes;
import my_cake_shop.cake_properties.Types;
import my_cake_shop.exception.CakeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShopTest {

  @Test
  public void shouldAddCakeAndVerifyCount() {
    Shop shop = new Shop();
    Cake cake = new Cake(Sizes.LARGE, Colors.YELLOW, Types.ICE_CREAM);

    Assertions.assertEquals(0, shop.howMuchCake());

    int three = 3;
    for (int i = 0; i < three; i++) shop.add(cake);
    Assertions.assertEquals(three, shop.howMuchCake());

    int four = 4;
    for (int i = 0; i < four; i++) shop.add(cake);
    Assertions.assertEquals(three + four, shop.howMuchCake());
  }

  @SneakyThrows
  @Test
  public void customerCanBuyCakeByColor() {
    Shop shop = new Shop();
    Cake cake = new Cake(Sizes.SMALL, Colors.WHITE, Types.CHOCOLATE);
    shop.add(cake);

    Assertions.assertEquals(1, shop.howMuchCake());

    Customer customer = Customer.builder()
        .cakeColor(Colors.WHITE)
        .build();
    Assertions.assertEquals(cake, shop.sellCakeTo(customer));
    Assertions.assertEquals(0, shop.howMuchCake());
  }

  @SneakyThrows
  @Test
  public void customerCanBuyCakeByType() {
    Shop shop = new Shop();
    Cake cake = new Cake(Sizes.SMALL, Colors.WHITE, Types.CHOCOLATE);
    shop.add(cake);

    Assertions.assertEquals(1, shop.howMuchCake());

    Customer customer = Customer.builder()
        .cakeType(Types.CHOCOLATE)
        .build();
    Assertions.assertEquals(cake, shop.sellCakeTo(customer));
    Assertions.assertEquals(0, shop.howMuchCake());
  }

  @SneakyThrows
  @Test
  public void customerCanBuyCakeByColorAndTypeAndSize() {
    Shop shop = new Shop();
    Cake cake = new Cake(Sizes.SMALL, Colors.WHITE, Types.CHOCOLATE);
    shop.add(cake);

    Assertions.assertEquals(1, shop.howMuchCake());

    Customer customer = Customer.builder()
        .cakeType(Types.CHOCOLATE)
        .cakeColor(Colors.WHITE)
        .cakeSize(Sizes.SMALL)
        .build();

    Assertions.assertEquals(cake, shop.sellCakeTo(customer));
    Assertions.assertEquals(0, shop.howMuchCake());
  }

  @SneakyThrows
  @Test
  public void shouldKeepTrackOfProfit() {
    Shop shop = new Shop();

    Cake cake1 = new Cake(Sizes.SMALL, Colors.WHITE, Types.CHOCOLATE);
    shop.add(cake1);

    Cake cake2 = new Cake(Sizes.SMALL, Colors.WHITE, Types.CHOCOLATE);
    shop.add(cake2);

    Customer customer = Customer.builder()
        .cakeColor(Colors.WHITE)
        .build();

    for (int i = 0; i < 2; i++) shop.sellCakeTo(customer);

    int priceOfSmallCake = 20;

    int totalExpectedProfit = 2 * priceOfSmallCake;

    Assertions.assertEquals(
        totalExpectedProfit,
        shop.howMuchProfit());
  }

  @SneakyThrows
  @Test
  public void shouldRemoveExpiringCake() {
    Shop shop = new Shop();
    Cake cake = new Cake(Sizes.SMALL, Colors.WHITE, Types.CHOCOLATE);
    shop.add(cake);

    Assertions.assertEquals(1, shop.howMuchCake());

    Thread.sleep(5000);

    Assertions.assertEquals(0, shop.howMuchCake());
  }

  @SneakyThrows
  @Test
  public void shouldCreateCakeForCustomerIfNotInShopAndBuyIngredients() {
    Shop shop = new Shop();
    Customer customer = Customer.builder()
        .cakeColor(Colors.WHITE)
        .cakeSize(Sizes.MEDIUM)
        .cakeType(Types.CHOCOLATE)
        .build();

    shop.sellCakeTo(customer);
    shop.sellCakeTo(customer);
    shop.sellCakeTo(customer);
    shop.sellCakeTo(customer);

    Assertions.assertEquals(97, shop.howMuchProfit());
  }
}