package my.cake.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import my.cake.shop.exception.InvalidCakeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ShopTest {

  @Mock
  private Inventory inventory;

  @InjectMocks
  private Shop shop;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void willReturnTheNumberOfCakesSpecified() {
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

    Customer customer = new Customer("blue", "sponge", "large");

    Cake actualCake = shop.sellCakeWithSpecificColour(customer);

    assertEquals(cheeseCake.getColour(), actualCake.getColour());
    assertEquals(cheeseCake.getType(), actualCake.getType());
    assertEquals(cheeseCake.getSize(), actualCake.getSize());
  }

  @Test
  void willRemoveACakeFromTheShopWhenItsSold() throws InvalidCakeException {
    Cake chocolateCake = new Cake("orange", "chocolate", "large");
    Cake carrotCake = new Cake("purple", "carrot", "small");
    Cake cheeseCake = new Cake("blue", "cheese", "large");

    Customer customer = new Customer("blue", "sponge", "large");

    addThreeCakesForSale(chocolateCake, carrotCake, cheeseCake);

    shop.sellCakeWithSpecificColour(customer);

    assertEquals(2, shop.getCakes().size());
  }

  @Test
  void willAddCostOfCakesToCashRegisterWhenBoughtByCustomer() throws InvalidCakeException {
    Cake chocolateCake = new Cake("orange", "chocolate", "large");
    Cake carrotCake = new Cake("purple", "carrot", "small");
    Cake cheeseCake = new Cake("blue", "cheese", "large");
    addThreeCakesForSale(chocolateCake, carrotCake, cheeseCake);

    Customer customerOne = new Customer("blue", "sponge", "large");
    Customer customerTwo = new Customer("purple", "sponge", "large");

    shop.sellCakeWithSpecificColour(customerOne);
    shop.sellCakeWithSpecificColour(customerTwo);

    assertEquals(70, shop.getAmountInCashRegister());
  }

  @Test
  void willDeductCostsOfRestockingInventoryFromCashRegister() throws InvalidCakeException {
    Customer customerOne = new Customer("blue", "sponge", "large");
    Customer customerTwo = new Customer("purple", "sponge", "large");

    when(inventory.getIngredientsForCake(customerOne.getSizePreference())).thenReturn(0);
    shop.sellCakeWithSpecificColour(customerOne);
    when(inventory.getIngredientsForCake(customerTwo.getSizePreference())).thenReturn(13);
    shop.sellCakeWithSpecificColour(customerTwo);

    assertEquals(87, shop.getAmountInCashRegister());
  }

  private void addThreeCakesForSale(Cake chocolateCake, Cake carrotCake, Cake cheeseCake) {
    shop.addCake(chocolateCake);
    shop.addCake(carrotCake);
    shop.addCake(cheeseCake);
  }

}
