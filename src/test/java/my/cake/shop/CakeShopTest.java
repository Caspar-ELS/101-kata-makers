package my.cake.shop;

import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class CakeShopTest {

  @InjectMocks
  private CakeShop cakeShop;

  @BeforeEach
  public void setup() {
    openMocks(this);
  }

  @Test
  void shouldHaveZeroCakesIfNoCakesAdded() {
    int numberOfCakes = cakeShop.checkNumberOfCakes();
    Assertions.assertEquals(0, numberOfCakes);
  }

  @Test
  void shouldHaveOneCakeIfOneCakeAdded() {
    Cake cake = new Cake(Color.BLUE, Size.MEDIUM, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    int numberOfCakes = cakeShop.checkNumberOfCakes();
    Assertions.assertEquals(1, numberOfCakes);
  }

  @Test
  void shouldReturnListOfBlueCakes() {
    Cake cake = new Cake(Color.BLUE, Size.MEDIUM, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    Assertions.assertEquals(cake, cakeShop.sellCakeOfColor(Color.BLUE));
  }

  @Test
  void shouldReturnNullIfNoBlueCake() {
    Cake cake = new Cake(Color.BLUE, Size.MEDIUM, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    Assertions.assertNull(cakeShop.sellCakeOfColor(Color.ORANGE));
  }

  @Test
  void shouldRemoveBlueCakeFromTheShopAfterSelling() {
    Cake cake = new Cake(Color.BLUE, Size.MEDIUM, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    cakeShop.sellCakeOfColor(Color.BLUE);
    Assertions.assertEquals(0, cakeShop.checkNumberOfCakes());
  }

  @Test
  void shouldHaveCorrectAmountAmountInTheCashRegister() {
    Cake cake = new Cake(Color.BLUE, Size.MEDIUM, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    Cake cakeTwo = new Cake(Color.BLUE, Size.LARGE, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cakeTwo);
    Cake cakeThree = new Cake(Color.BLUE, Size.SMALL, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cakeThree);
    cakeShop.sellCakeOfColor(Color.BLUE);
    cakeShop.sellCakeOfColor(Color.BLUE);
    cakeShop.sellCakeOfColor(Color.BLUE);
    Assertions.assertEquals(105.00, cakeShop.checkCashRegister());
  }

  @Test
  void customerCanBuyCakeByColor() throws CakeException {
    CakeShop shop = new CakeShop();
    Cake cake = new Cake(Color.WHITE, Size.SMALL, Type.CHOCOLATE_CAKE);
    shop.addCake(cake);

    Assertions.assertEquals(1, shop.checkNumberOfCakes());

    Customer customer = Customer.builder()
        .preferredColor(Color.WHITE)
        .build();
    Assertions.assertEquals(cake, shop.sellCakeTo(customer));
    Assertions.assertEquals(0, shop.checkNumberOfCakes());
  }

  @Test
  void customerCanBuyCakeByType() throws CakeException {
    CakeShop shop = new CakeShop();
    Cake cake = new Cake(Color.WHITE, Size.SMALL, Type.CHOCOLATE_CAKE);
    shop.addCake(cake);

    Assertions.assertEquals(1, shop.checkNumberOfCakes());

    Customer customer = Customer.builder()
        .preferredType(Type.CHOCOLATE_CAKE)
        .build();
    Assertions.assertEquals(cake, shop.sellCakeTo(customer));
    Assertions.assertEquals(0, shop.checkNumberOfCakes());
  }

  @Test
 void customerCanBuyCakeByColorAndType() throws CakeException {
    CakeShop shop = new CakeShop();
    Cake cake = new Cake(Color.WHITE, Size.SMALL, Type.CHOCOLATE_CAKE);
    shop.addCake(cake);

    Assertions.assertEquals(1, shop.checkNumberOfCakes());

    Customer customer = Customer.builder()
        .preferredColor(Color.WHITE)
        .preferredType(Type.CHOCOLATE_CAKE)
        .build();
    Assertions.assertEquals(cake, shop.sellCakeTo(customer));
    Assertions.assertEquals(0, shop.checkNumberOfCakes());
  }

  @Test
  void throwsExceptionWhenNoCakeToBuy() {
    CakeShop shop = new CakeShop();
    Customer customer = Customer.builder()
        .preferredColor(Color.WHITE)
        .build();
    Assertions.assertThrows(CakeException.class, () -> shop.sellCakeTo(customer));
  }
}