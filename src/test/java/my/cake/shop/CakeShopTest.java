package my.cake.shop;

import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class CakeShopTest {

  private final Inventory inventory = new Inventory();

  @InjectMocks
  private CakeShop cakeShop = new CakeShop(inventory);

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
  void customerCanBuyCakeByColor() {
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
  void customerCanBuyCakeByType() {
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
 void customerCanBuyCakeByColorAndType() {
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
  void shouldMakeNewCakeIfNoCakeToBuyAndUpdateCashRegister() {
    Inventory inventory = new Inventory();
    CakeShop shop = new CakeShop(inventory);
    Customer customer = Customer.builder()
        .preferredColor(Color.WHITE)
        .preferredType(Type.CHOCOLATE_CAKE)
        .preferredSize(Size.SMALL)
        .build();

    Cake customersCake = shop.sellCakeTo(customer);
    Assertions.assertEquals(customer.getPreferredColor(), customersCake.getColor());
    Assertions.assertEquals(customer.getPreferredType(), customersCake.getType());
    Assertions.assertEquals(customer.getPreferredSize(), customersCake.getSize());
    Assertions.assertEquals(20, shop.checkCashRegister());
  }

  @Test
  void shouldMakeNewCakeIfNoCakeToBuyAndRestockAndUpdateCashRegisterWithCakePriceAndRestockCost() {
    inventory.setEggs(0);
    CakeShop shop = new CakeShop(inventory);
    Customer customer = Customer.builder()
        .preferredColor(Color.WHITE)
        .preferredType(Type.CHOCOLATE_CAKE)
        .preferredSize(Size.SMALL)
        .build();

    Cake customersCake = shop.sellCakeTo(customer);
    Assertions.assertEquals(customer.getPreferredColor(), customersCake.getColor());
    Assertions.assertEquals(customer.getPreferredType(), customersCake.getType());
    Assertions.assertEquals(customer.getPreferredSize(), customersCake.getSize());
    Assertions.assertEquals(10, shop.checkCashRegister());
  }

  @Test
  void shouldUpdateCashRegisterWithCakePriceIfNoRestockingRequired() {
    Customer customerSmallCake = Customer.builder()
        .preferredSize(Size.SMALL)
        .preferredColor(Color.RED)
        .preferredType(Type.CHOCOLATE_CAKE)
        .build();
    Customer customerMediumCake = Customer.builder()
        .preferredSize(Size.MEDIUM)
        .preferredColor(Color.RED)
        .preferredType(Type.CHOCOLATE_CAKE)
        .build();
    cakeShop.sellCakeTo(customerSmallCake);
    cakeShop.sellCakeTo(customerMediumCake);
    Assertions.assertEquals(55.00, cakeShop.checkCashRegister());
  }
}