package kata.makers;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SkiPassTest {

  private SkiPass skiPass;

  @BeforeEach
  public void setUp() {
    skiPass = new SkiPass();
  }

  @Test
  void skiPassHasFiveRidesByDefault() {
    Assertions.assertEquals(5, skiPass.getRides());
  }

  @Test
  void skiPassHasZeroCreditByDefault() {
    Assertions.assertEquals(0, skiPass.getCredit());
  }

  @Test
  void canTopUpSkiPass() {
    Assertions.assertEquals(0, skiPass.getCredit());
    skiPass.topUp(100);
    Assertions.assertEquals(100, skiPass.getCredit());
  }

  @Test
  void canChargeSkiPass() {
    skiPass.topUp(100);
    Assertions.assertEquals(100, skiPass.getCredit());
    skiPass.charge(20);
    Assertions.assertEquals(80, skiPass.getCredit());
  }

}