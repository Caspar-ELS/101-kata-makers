package my.cake.shop;

import static my.cake.shop.model.CakeColor.BLUE;
import static my.cake.shop.model.CakeColor.PINK;
import static my.cake.shop.model.CakeColor.RED;
import static my.cake.shop.model.CakeColor.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CakeShopTest {

  @Test
  void addThreeCakesToTheShopAndReturnTheAvailableCakes() {
    CakeShop shop = new CakeShop();
    addCakesBasedOnNumber(shop, 3);
    assertEquals(3, shop.getAvailableNumberOfCake());
  }

  @Test
  void addSevenCakesToTheShopAndReturnTheAvailableCakes() {
    CakeShop shop = new CakeShop();
    addCakesBasedOnNumber(shop, 7);
    assertEquals(7, shop.getAvailableNumberOfCake());
  }

  @Test
  void addNoCakeToTheShopAndReturnTheAvailableCakes() {
    CakeShop shop = new CakeShop();
    assertEquals(0, shop.getAvailableNumberOfCake());
  }

  @Test
  void sellTheCakeToTheCustomerWhenTheCakeIsAvailableInShop() {
    CakeShop shop = new CakeShop();
    addCakes(shop);
    assertEquals(4, shop.getAvailableNumberOfCake());
    Cake cake = new Cake(WHITE, "Cheese", "Small");
    Customer customer = new Customer(cake);
    assertEquals(cake, shop.sellCake(customer));
    assertEquals(3, shop.getAvailableNumberOfCake());
  }

  @Test
  void sellNoCakeToTheCustomerWhenTheCakeIsNotAvailableInShop() {
    CakeShop shop = new CakeShop();
    addCakes(shop);
    assertEquals(4, shop.getAvailableNumberOfCake());
    Cake cake = new Cake(PINK, "Cheese", "Small");
    Customer customer = new Customer(cake);
    assertNull(shop.sellCake(customer));
    assertEquals(4, shop.getAvailableNumberOfCake());
  }

  @Test
  void sellCakesToTheCustomerAndTrackTheAmountCollectedInShop() {
    CakeShop shop = new CakeShop();
    addCakes(shop);
    assertEquals(4, shop.getAvailableNumberOfCake());
    shop.sellCake(new Customer(new Cake(WHITE, "Cheese", "Small")));
    shop.sellCake(new Customer(new Cake(BLUE, "Cheese", "Large")));
    assertEquals(2, shop.getAvailableNumberOfCake());
    assertEquals(70, shop.getAmountCollected());
  }

  @Test
  void removeCakesFromTheShopWhenExpired() throws InterruptedException {
    CakeShop shop = new CakeShop();
    addCakes(shop);
    assertEquals(4, shop.getAvailableNumberOfCake());
    Thread.sleep(5000);
    assertEquals(0, shop.getAvailableNumberOfCake());
  }
  private void addCakes(CakeShop shop) {
    shop.addCakesToTheShop(new Cake(WHITE, "Cheese", "Small"));
    shop.addCakesToTheShop(new Cake(WHITE, "Cheese", "Small"));
    shop.addCakesToTheShop(new Cake(BLUE, "Cheese", "Large"));
    shop.addCakesToTheShop(new Cake(RED, "Strawberry", "Large"));
  }

  private void addCakesBasedOnNumber(CakeShop shop, int expectedNumber) {
    for (int i = 1; i <= expectedNumber; i++) {
      shop.addCakesToTheShop(new Cake());
    }
  }

}
