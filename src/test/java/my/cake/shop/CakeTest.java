package my.cake.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import my.cake.shop.exception.InvalidCakeException;
import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  void willCreateAnyCake() {
    Cake cake = new Cake("Blue", "Chocolate", "medium");

    assertEquals("medium", cake.getSize());
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
  void willGetTheCorrectPriceOfALargeCake() throws InvalidCakeException {
    Cake cake = new Cake("orange", "cheese cake", "large");

    int expectedPrice = 50;
    int actualPrice = cake.getCakePrice(cake.getSize());

    assertEquals(expectedPrice, actualPrice);

  }

  @Test
  void willGetTheCorrectPriceOfASmallCake() throws InvalidCakeException {
    Cake cake = new Cake("orange", "cheese cake", "small");

    int expectedPrice = 20;
    int actualPrice = cake.getCakePrice(cake.getSize());

    assertEquals(expectedPrice, actualPrice);

  }

  @Test
  void willThrowInvalidCakeExceptionWhenGettingPriceOfCakeSizeThatDoesNotExist() {
    Cake cake = new Cake("orange", "cheese cake", "extra large");

    assertThrows(InvalidCakeException.class, () -> cake.getCakePrice(cake.getSize()));
  }

  @Test
  void willOutputHowManySecondsLeftBeforeTheCakeExpiresWhenCalledAfterLessThan5Seconds()
      throws InterruptedException {
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