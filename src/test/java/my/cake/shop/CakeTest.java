package my.cake.shop;

import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  void shouldOverwriteDefaultNameIfNameChangedAfterMakingCake() {
    Cake cake = new Cake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    cake.setName("Amazing cheesecake");
    Assertions.assertEquals("Amazing cheesecake", cake.getName());
  }

  @Test
  void shouldMakeCakeWithCustomName() {
    Cake cake = new Cake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE, "Amazing cheesecake");
    Assertions.assertEquals("Amazing cheesecake", cake.getName());
  }

  @Test
  void smallCakeShouldCost20() {
    Cake cake = new Cake(Color.ORANGE, Size.SMALL, Type.CHEESECAKE);
    Assertions.assertEquals(20.00, cake.getPrice());
  }

  @Test
  void mediumCakeShouldCost35() {
    Cake cake = new Cake(Color.ORANGE, Size.MEDIUM, Type.CHEESECAKE);
    Assertions.assertEquals(35.00, cake.getPrice());
  }

  @Test
  void largeCakeShouldCost50() {
    Cake cake = new Cake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    Assertions.assertEquals(50.00, cake.getPrice());
  }

  @Test
  void shouldReturnFullTimeLeftRightAfterMakingCake() {
    Cake cake = new Cake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    Assertions.assertEquals("Time left: 5000 milliseconds", cake.checkHowMuchTimeLeft());
  }

//  @Test
//  void shouldNotReturnFullTimeLeftSomeTimeAfterMakingCake() {
//    Cake anotherCake = Cake.makeCake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
//    Awaitility.waitAtMost(20, TimeUnit.SECONDS);
//    Assertions.assertNotEquals("Time left: 5000 milliseconds", Cake.checkHowMuchTimeLeft(anotherCake));
//  }

  @Test
  void shouldReturnExpired() {
    Cake cake = new Cake(Color.ORANGE, Size.LARGE, Type.CHEESECAKE);
    Awaitility.await().pollDelay(6, TimeUnit.SECONDS)
        .untilAsserted(() -> Assertions.assertEquals("Expired", cake.checkHowMuchTimeLeft()));
  }
}