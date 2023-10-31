package my.cake.shop;

import static my.cake.shop.model.CakeColor.BLUE;
import static my.cake.shop.model.CakeColor.PINK;
import static my.cake.shop.model.CakeColor.RED;
import static my.cake.shop.model.CakeColor.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import my.cake.shop.model.CakeSize;
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
    Cake cake = new Cake(WHITE, "Cheese", CakeSize.SMALL);
    Customer customer = new Customer(cake);
    assertEquals(cake, shop.sellCake(customer));
    assertEquals(3, shop.getAvailableNumberOfCake());
  }

  @Test
  void sellNoCakeToTheCustomerWhenTheCakeIsNotAvailableInShop() {
    CakeShop shop = new CakeShop();
    addCakes(shop);
    assertEquals(4, shop.getAvailableNumberOfCake());
    Cake cake = new Cake(PINK, "Cheese", CakeSize.SMALL);
    Customer customer = new Customer(cake);
    assertEquals(4, shop.getAvailableNumberOfCake());
  }

  @Test
  void sellCakesToTheCustomerAndTrackTheAmountCollectedInShop() {
    CakeShop shop = new CakeShop();
    addCakes(shop);
    assertEquals(4, shop.getAvailableNumberOfCake());
    shop.sellCake(new Customer(new Cake(WHITE, "Cheese", CakeSize.SMALL)));
    shop.sellCake(new Customer(new Cake(BLUE, "Cheese", CakeSize.LARGE)));
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

  @Test
  void shouldSellCakeToTheCustomerWhenTheCakeIsNotAvailableInShop() {
    CakeShop shop = new CakeShop();
    addCakes(shop);
    assertEquals(4, shop.getAvailableNumberOfCake());
    Cake cake = new Cake(PINK, "Cheese", CakeSize.LARGE);
    Customer customer = new Customer(cake);
    shop.sellCake(customer);
    shop.sellCake(customer);
    shop.sellCake(customer);
    assertEquals(4, shop.getAvailableNumberOfCake());
    assertEquals(107, shop.getAmountCollected());
  }

  private void addCakes(CakeShop shop) {
    shop.addCakesToTheShop(new Cake(WHITE, "Cheese", CakeSize.SMALL));
    shop.addCakesToTheShop(new Cake(WHITE, "Cheese", CakeSize.SMALL));
    shop.addCakesToTheShop(new Cake(BLUE, "Cheese", CakeSize.LARGE));
    shop.addCakesToTheShop(new Cake(RED, "Strawberry", CakeSize.LARGE));
  }

  private void addCakesBasedOnNumber(CakeShop shop, int expectedNumber) {
    for (int i = 1; i <= expectedNumber; i++) {
      shop.addCakesToTheShop(new Cake());
    }
  }

}
