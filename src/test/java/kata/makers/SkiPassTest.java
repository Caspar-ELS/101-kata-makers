package kata.makers;

import static org.junit.jupiter.api.Assertions.*;

import kata.makers.model.SkiPass;
import org.junit.jupiter.api.Test;

class SkiPassTest {

  @Test
  public void userCanGetAUniqueSkiPass() {
    SkiPassControl skiPassControl = new SkiPassControl();
    Shop shop = new Shop(skiPassControl);

    assertEquals(0, shop.getSkiPass().getId());
    assertEquals(1, shop.getSkiPass().getId());
    assertEquals(2, shop.getSkiPass().getId());
    assertEquals(3, shop.getSkiPass().getId());
    assertEquals(4, shop.getSkiPass().getId());
  }

  @Test
  public void userCanUseSkiPassToRideLift() {
    SkiPassControl skiPassControl = new SkiPassControl();
    Lift lift = new Lift(skiPassControl);
    Shop shop = new Shop(skiPassControl);

    SkiPass skiPass = shop.getSkiPass();

    assertTrue(lift.openGate(skiPass));
  }

  @Test
  public void userCanOnlyUseSkiPassToRideLiftFiveTimes() {
    SkiPassControl skiPassControl = new SkiPassControl();

    Shop shop = new Shop(skiPassControl);
    SkiPass skiPass = shop.getSkiPass();

    Lift lift = new Lift(skiPassControl);

    assertTrue(lift.openGate(skiPass));
    assertTrue(lift.openGate(skiPass));
    assertTrue(lift.openGate(skiPass));
    assertTrue(lift.openGate(skiPass));
    assertTrue(lift.openGate(skiPass));
    assertFalse(lift.openGate(skiPass));
  }

  // TODO: add edge cases (ski pass already exists error, ski pass not found error)

}