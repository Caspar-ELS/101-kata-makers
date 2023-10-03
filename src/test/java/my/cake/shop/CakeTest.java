package my.cake.shop;

import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  void shouldHaveColor() {
    Cake cake = new Cake();
    cake.setColor(Color.BLUE);
    Assertions.assertEquals(Color.BLUE, cake.getColor());
  }

  @Test
  void shouldHaveType() {
    Cake cake = new Cake();
    cake.setType(Type.CHEESECAKE);
    Assertions.assertEquals(Type.CHEESECAKE, cake.getType());
  }

  @Test
  void shouldHaveSize() {
    Cake cake = new Cake();
    cake.setSize(Size.SMALL);
    Assertions.assertEquals(Size.SMALL, cake.getSize());
  }

  @Test
  void shouldHaveName() {
    Cake cake = Cake.makeCake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    Assertions.assertEquals("LARGE ORANGE CHEESECAKE", cake.getName());
  }

  @Test
  void shouldOverwriteDefaultNameIfNameChangedAfterMakingCake() {
    Cake cake = Cake.makeCake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    cake.setName("Amazing cheesecake");
    Assertions.assertEquals("Amazing cheesecake", cake.getName());
  }

  @Test
  void shouldMakeCakeWithCustomName() {
    Cake cake = Cake.makeCake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE, "Amazing cheesecake");
    Assertions.assertEquals("Amazing cheesecake", cake.getName());
  }

  @Test
  void smallCakeShouldCost20() {
    Cake cake = Cake.makeCake(Color.ORANGE, Size.SMALL, Type.CHEESECAKE);
    Assertions.assertEquals(20.00, cake.setPriceBasedOnSize());
  }

  @Test
  void mediumCakeShouldCost35() {
    Cake cake = Cake.makeCake(Color.ORANGE, Size.MEDIUM, Type.CHEESECAKE);
    Assertions.assertEquals(35.00, cake.setPriceBasedOnSize());
  }

  @Test
  void largeCakeShouldCost50() {
    Cake cake = Cake.makeCake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    Assertions.assertEquals(50.00, cake.setPriceBasedOnSize());
  }

  @Test
  void shouldReturnFullTimeLeftRightAfterMakingCake() {
    Cake cake = Cake.makeCake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    Assertions.assertEquals("Time left: 5000 milliseconds", Cake.checkHowMuchTimeLeft(cake));
  }

//  @Test
//  void shouldNotReturnFullTimeLeftSomeTimeAfterMakingCake() {
//    Cake anotherCake = Cake.makeCake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
//    Awaitility.waitAtMost(20, TimeUnit.SECONDS);
//    Assertions.assertNotEquals("Time left: 5000 milliseconds", Cake.checkHowMuchTimeLeft(anotherCake));
//  }

  @Test
  void shouldReturnExpired() {
    Cake newCake = Cake.makeCake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    Awaitility.await().pollDelay(6, TimeUnit.SECONDS)
        .untilAsserted(() -> Assertions.assertEquals("Expired", Cake.checkHowMuchTimeLeft(newCake)));
  }
}