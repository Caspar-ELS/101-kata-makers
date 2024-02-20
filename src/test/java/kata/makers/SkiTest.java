package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import kata.makers.exception.SkiPassExpiredException;
import kata.makers.model.SkiPass;
import kata.makers.model.SkiRider;
import kata.makers.model.SkiRiding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkiTest {

  private SkiRiding skiRiding;
  @BeforeEach
  void setUp(){
    skiRiding = new SkiRiding();
  }
  @Test
  void skiRiderBuysANormalSkiPass(){
      SkiRider skiRider = skiRiding.buyASkiPass(1, "Person1", SkiPass.NORMAL);
      assertEquals(1, skiRider.getNoOfRidesLeft());
  }

  @Test
  void skiRiderRideOnlyOnceWithNormalSkiPass() throws SkiPassExpiredException {
    SkiRider skiRider = skiRiding.buyASkiPass(1, "Person1", SkiPass.NORMAL);
    assertEquals(0, skiRiding.rideTheSlopeWithDifferentSkiPasses(skiRider));
  }

  @Test
  void throwExceptionWhenSkiRiderRidesMoreThanOnceWithNormalSkiPass()
      throws SkiPassExpiredException {
    SkiRider skiRider = skiRiding.buyASkiPass(1, "Person1", SkiPass.NORMAL);
    skiRiding.rideTheSlopeWithDifferentSkiPasses(skiRider);
    assertThrows(SkiPassExpiredException.class,
        () -> skiRiding.rideTheSlopeWithDifferentSkiPasses(skiRider));
  }



}
