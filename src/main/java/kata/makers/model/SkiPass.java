package kata.makers.model;

import lombok.Getter;
import lombok.Setter;

public class SkiPass {
  private static final int START_AMOUNT_OF_RIDES = 5;

  @Getter
  private Long id;

  @Getter
  private int remainingRides;

  public SkiPass(long nextId) {
    this.id = nextId;

    this.remainingRides = START_AMOUNT_OF_RIDES;
  }

  public void reduceRemainingRides() {
    this.remainingRides--;
  }
}
