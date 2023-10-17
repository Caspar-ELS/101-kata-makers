package my.cake.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.MockitoAnnotations.openMocks;

import my.cake.shop.exception.InvalidCakeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class ShopTest {

  @InjectMocks
  private Shop shop;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void willReturnTheNumberOfCakesSpecified() throws InvalidCakeException {
    Cake chocolateCake = new Cake("orange", "chocolate", "large");
    Cake carrotCake = new Cake("purple", "carrot", "small");
    Cake cheeseCake = new Cake("pink", "cheese", "large");

    addThreeCakesForSale(chocolateCake, carrotCake, cheeseCake);

    int actualNumberOfCakes = shop.getNumberOfCakesInStock();

    assertEquals(3, actualNumberOfCakes);
  }

  @Test
  void willReturnZeroWhenNoCakesAreInTheShop() {
    int actualNumberOfCakes = shop.getNumberOfCakesInStock();

    assertEquals(0, actualNumberOfCakes);
  }

  @Test
  void willRetrieveABlueCakeWhenOneIsInStock() throws InvalidCakeException {
    Cake chocolateCake = new Cake("orange", "chocolate", "large");
    Cake carrotCake = new Cake("purple", "carrot", "small");
    Cake cheeseCake = new Cake("blue", "cheese", "large");

    addThreeCakesForSale(chocolateCake, carrotCake, cheeseCake);

    Cake actualCake = shop.findCakeWithSpecificColour("blue");

    assertEquals("blue", actualCake.getColour());
    assertEquals(cheeseCake, actualCake);
  }

  @Test
  void willNotRetrieveACakeIfNoneSpecifiedAreInTheShop() throws InvalidCakeException {
    Cake chocolateCake = new Cake("orange", "chocolate", "large");
    Cake carrotCake = new Cake("purple", "carrot", "small");

    shop.addCake(chocolateCake);
    shop.addCake(carrotCake);

    assertNull(shop.findCakeWithSpecificColour("blue"));
  }

  @Test
  void willRemoveACakeFromTheShopWhenItsSold() throws InvalidCakeException {
    Cake chocolateCake = new Cake("orange", "chocolate", "large");
    Cake carrotCake = new Cake("purple", "carrot", "small");
    Cake cheeseCake = new Cake("blue", "cheese", "large");

    addThreeCakesForSale(chocolateCake, carrotCake, cheeseCake);

    shop.findCakeWithSpecificColour("blue");

    assertEquals(2, shop.getCakes().size());
  }

  @Test
  void willAddCostOfCakesToCashRegisterWhenBoughtByCustomer() throws InvalidCakeException {
    Cake chocolateCake = new Cake("orange", "chocolate", "large");
    Cake carrotCake = new Cake("purple", "carrot", "small");
    Cake cheeseCake = new Cake("blue", "cheese", "large");

    addThreeCakesForSale(chocolateCake, carrotCake, cheeseCake);

    shop.findCakeWithSpecificColour("blue");
    shop.findCakeWithSpecificColour("purple");

    assertEquals(70, shop.getAmountInCashRegister());
  }

  private void addThreeCakesForSale(Cake chocolateCake, Cake carrotCake, Cake cheeseCake) {
    shop.addCake(chocolateCake);
    shop.addCake(carrotCake);
    shop.addCake(cheeseCake);
  }

}
