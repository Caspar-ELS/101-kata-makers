package kata.makers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShopTest {

  @Test
  public void userCanGetAUniqueSkiPass() {
    Shop shop = new Shop();

    assertEquals(0, shop.getSkiPass().getId());
    assertEquals(1, shop.getSkiPass().getId());
    assertEquals(2, shop.getSkiPass().getId());
    assertEquals(3, shop.getSkiPass().getId());
    assertEquals(4, shop.getSkiPass().getId());
  }

}