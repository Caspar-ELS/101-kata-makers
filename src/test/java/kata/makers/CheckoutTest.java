package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kata.makers.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckoutTest {

  private Checkout checkout;

  @BeforeEach
  void setUp(){
    checkout = new Checkout();
  }

  @Test
  void testForValidInputStringAndGetSum() throws CustomException {
    assertFalse(checkout.isValidInput("1,", ","));
    assertFalse(checkout.isValidInput("1", ","));
    assertFalse(checkout.isValidInput("1,2,4,", ","));
    assertFalse(checkout.isValidInput("1,2,,4", ","));
    assertTrue(checkout.isValidInput("1,2,3,4", ","));
    assertEquals(10,checkout.sum("1,2,3,4", ","));
    assertEquals(0,checkout.sum("1,", ","));
  }

  @Test
  void testForValidInputStringWithDifferentDelimiterAndGetSum() throws CustomException {
    assertFalse(checkout.isValidInput("1\n", "\n"));
    assertFalse(checkout.isValidInput("1", "\n"));
    assertFalse(checkout.isValidInput("1\n2\n4\n", "\n"));
    assertFalse(checkout.isValidInput("1\n2\n\n4", "\n"));
    assertTrue(checkout.isValidInput("1\n2\n3\n4", "\n"));
    assertEquals(10,checkout.sum("1\n2\n3\n4", "\n"));
    assertEquals(0,checkout.sum("1\n", "\n"));
  }

  @Test
  void testForValidInputStringWithSquareBracketAsDelimiterAndGetSum() throws CustomException {
    assertThrows(CustomException.class,
        () -> checkout.isValidInput("1]2", "]["));
    assertFalse(checkout.isValidInput("1]", "]"));
    assertFalse(checkout.isValidInput("1", "]"));
    assertFalse(checkout.isValidInput("1]2]4]", "]"));
    assertFalse(checkout.isValidInput("1]2]]4", "]"));
    assertTrue(checkout.isValidInput("1]2]3]4", "]"));
    assertEquals(10,checkout.sum("1]2]3]4", "]"));
    assertEquals(0,checkout.sum("1]", "]"));
  }

  @Test
  void testForValidInputStringWithReservedCharacterAsDelimiterAndGetSum() throws CustomException {
    assertThrows(CustomException.class,
        () -> checkout.isValidInput("1$2", "$["));
    assertFalse(checkout.isValidInput("1$", "$"));
    assertFalse(checkout.isValidInput("1", "$"));
    assertFalse(checkout.isValidInput("1$2$4$", "$"));
    assertFalse(checkout.isValidInput("1$2$$4", "$"));
    assertTrue(checkout.isValidInput("1$2$3$4", "$"));
    assertThrows(CustomException.class,
        () -> checkout.sum("1$2$3$a", "$"));
    assertEquals(10,checkout.sum("1$2$3$4", "$"));
    assertEquals(0,checkout.sum("1$", "$"));
  }
}
