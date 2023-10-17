package my.cake.shop;

import static my.cake.shop.model.CakeColor.BLUE;
import static my.cake.shop.model.CakeColor.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  void testWhiteColorSmallCake(){
    Cake myCake = new Cake(WHITE, "Cheese", "Small");
    assertEquals(WHITE, myCake.getColor());
  }

  @Test
  void testCakeNameWithColorSizeAndTypeOfCake(){
    Cake myCake = new Cake(BLUE, "Cheese", "Large");
    assertEquals("Large BLUE Cheese cake", myCake.getName());
  }

  @Test
  void testCustomCakeName(){
    Cake cake = new Cake();
    String cakeName = "Chocolate Fudge cake";
    cake.setName(cakeName);
    assertEquals(cakeName, cake.getName());
  }

  @Test
  void testCakePriceBasedOnCakeSize(){
    Cake cake = new Cake();
    cake.setSize("Small");
    assertEquals("£20", cake.getPrice());
  }

  @Test
  void testCakeExpiryBasedOnAfterFiveSecondsCakeMade() throws InterruptedException {
    Cake cake = new Cake();
    Thread.sleep(5000);
    assertEquals("Expired", cake.getExpiresIn());
  }

  @Test
  void testCakeExpiryBasedOnAfterTw0SecondsCakeMade() throws InterruptedException {
    Cake cake = new Cake();
    Thread.sleep(2000);
    assertEquals("Cake will expire in another 2 seconds", cake.getExpiresIn());
  }
}