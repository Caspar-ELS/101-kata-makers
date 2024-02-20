package SkiPass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import kata.makers.SkiPass;
import org.junit.jupiter.api.Test;

class SkiPassTest {

  @Test
  void testGetSkiPass() {
    SkiPass skiPass = new SkiPass();
    String result = skiPass.getSkiPass();
    assertNotNull(result);
    assertEquals(result, "1234");
  }
}


