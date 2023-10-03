package my.cake.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  void willCreateAnyCake() {
    Cake cake = new Cake("Blue", "Chocolate", "Medium");

    assertEquals("Medium", cake.getSize());
    assertEquals("Chocolate", cake.getType());
    assertEquals("Blue", cake.getColour());
  }

  @Test
  void willOutputTheNameOfACake() {
    Cake cake = new Cake("orange", "cheese cake", "large");

    String expectedOutput = "large orange cheese cake";
    String actualOutput = cake.printCakeName(cake);

    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  void willBeAbleToOverrideTheDefaultCakeName() {
    Cake cake = new Cake("orange", "cheese cake", "large", "Caspar's amazing cake");

    String expectedCakeName = "Caspar's amazing cake";
    String actualCakeName = cake.printCakeName(cake);

    assertEquals(expectedCakeName, actualCakeName);
  }

  @Test
  void willGetTheCorrectPriceOfALargeCake() {
    Cake cake = new Cake("orange", "cheese cake", "large");

    String expectedPrice = "£50";
    String actualPrice = cake.getPrice(cake.getSize());

    assertEquals(expectedPrice, actualPrice);

  }

  @Test
  void willGetTheCorrectPriceOfASmallCake() {
    Cake cake = new Cake("orange", "cheese cake", "small");

    String expectedPrice = "£20";
    String actualPrice = cake.getPrice(cake.getSize());

    assertEquals(expectedPrice, actualPrice);

  }

  @Test
  void willOutputHowManySecondsLeftBeforeTheCakeExpiresWhenCalledAfterLessThan5Seconds() throws InterruptedException {
    Cake cake = new Cake("orange", "cheese cake", "small");

    Thread.sleep(3000);
    String expectedSecondsLeft = "Your cake will expire in 3 seconds";
    String actualSecondsLeft = cake.getCakeExpiry(cake.getTimeCreated());

    assertEquals(expectedSecondsLeft, actualSecondsLeft);

  }

  @Test
  void willOutputThatTheCakeExpiredWhenLongerThan5SecondsHasPassed() throws InterruptedException {
    Cake cake = new Cake("orange", "cheese cake", "small");

    Thread.sleep(6000);
    String expectedOutput = "Your cake expired :(";
    String actualOutput = cake.getCakeExpiry(cake.getTimeCreated());

    assertEquals(expectedOutput, actualOutput);

  }

}