package my.cake.shop;

import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  @Disabled
  void createCake() {
    Assertions.assertEquals(new Cake().createCake("Red", "Cheese", "Large"),
        "Cake created with color Red, type Cheese, and size Large");
  }

  @Test
  void largeOrangeCheeseCake() {
    Assertions.assertEquals(new Cake().createCake("orange", "cheese", "large"),
        "large orange cheese cake");
  }

  @Test
  void mediumBlueCreamCake() {
    Assertions.assertEquals(new Cake().createCake("blue", "cream", "medium"),
        "medium blue cream cake");
  }

  @Test
  void smallRedFruitCake() {
    Assertions.assertEquals(new Cake().createCake("red", "fruit", "small"),
        "small red fruit cake");
  }

  @Test
  void withSpecialName() {
    final String SOME_RANDOM_NAME = "This will be a super cake made by BOM";
    Assertions.assertEquals(
        new Cake().createCake("someColor", "someType", "someSize", SOME_RANDOM_NAME),
        SOME_RANDOM_NAME);
  }

  // withinMinMax
  @Test
  void withinMinMax(){
    Assertions.assertEquals(new Cake().getThePrice(20, 30, 40, "small"), "The price is 20");
  }

  // too large
  @Test
  void tooLarge(){
    Assertions.assertEquals(new Cake().getThePrice(1000, 30, 40, "small"), "Error, price out of bounds");
  }

  // too small
  @Test
  void tooSmall(){
    Assertions.assertEquals(new Cake().getThePrice(19, 30, 40, "small"), "Error, price out of bounds");
  }

  // onMax
  @Test
  void onMax(){
    Assertions.assertEquals(new Cake().getThePrice(50, 50, 50, "small"), "The price is 50");
  }

  // onMin
  @Test
  void onMin(){
    Assertions.assertEquals(new Cake().getThePrice(20, 20, 20, "small"), "The price is 20");
  }

  // fresh
  @Test
  void fresh() throws Exception {
    Date cakeCreatedAt = new Date(123, 8, 26, 14, 00);
    Date currentTime = new Date(123, 8, 26, 14, 07);
    Assertions.assertEquals(new Cake().getFreshMinutesLeft(cakeCreatedAt, currentTime), 7);
  }

  // not fresh
  @Test
  void notFresh() throws Exception {
    Date cakeCreatedAt = new Date(123, 8, 26, 14, 00);
    Date currentTime = new Date(123, 8, 26, 16, 00);
    Assertions.assertThrows(Exception.class, () -> {
      new Cake().getFreshMinutesLeft(cakeCreatedAt, currentTime);
    });
  }
}