package my.cake.shop;

import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CakeShopTest {

  @Mock
  Cake cake;

  @InjectMocks
  private CakeShop cakeShop;

  @BeforeEach
  public void setup() {
    openMocks(this);
  }

  @Test
  void shouldHaveZeroCakesIfNoCakesAdded() {
    Cake cake =new Cake(Color.BLUE, Size.MEDIUM, Type.CHOCOLATE_CAKE);
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
}