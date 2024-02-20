package kata.makers;

import static org.junit.jupiter.api.Assertions.*;

import kata.makers.model.SkiPass;
import org.junit.jupiter.api.Test;

class SkiPassTest {

  @Test
  public void userCanGetAUniqueSkiPass() {
    Shop shop = new Shop();

    assertEquals(0, shop.getSkiPass().getId());
    assertEquals(1, shop.getSkiPass().getId());
    assertEquals(2, shop.getSkiPass().getId());
    assertEquals(3, shop.getSkiPass().getId());
    assertEquals(4, shop.getSkiPass().getId());
  }

  @Test
  public void userCanUseSkiPassToRideLift() {
    Lift lift = new Lift();

    SkiPass skiPass = new SkiPass(0);

    assertEquals(true, lift.openGate(skiPass));
  }

}