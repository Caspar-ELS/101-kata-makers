package kata.makers;

import java.util.HashMap;
import java.util.Map;
import kata.makers.model.SkiPass;

public class SkiPassControl {
  private static final int DEFAULT_START_AMOUNT_OF_RIDES = 5;

  private long nextId = 0;

  private final Map<Long, Integer> remainingRidesPerSkiPassId = new HashMap<>();

  public SkiPass registerNewSkiPass() {
    this.remainingRidesPerSkiPassId.put(nextId, DEFAULT_START_AMOUNT_OF_RIDES);

    SkiPass skiPass = new SkiPass(nextId);

    nextId++;

    return skiPass;
  }

  public int getRemainingRidesFor(SkiPass skiPass) {
    return remainingRidesPerSkiPassId.get(skiPass.getId());
  }

  public void reduceRemainingRidesFor(SkiPass skiPass) {
    int remainingRides = this.remainingRidesPerSkiPassId.get(skiPass.getId());

    this.remainingRidesPerSkiPassId.put(skiPass.getId(), remainingRides - 1);
  }
}
