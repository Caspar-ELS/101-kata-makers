package kata.makers;

import kata.makers.model.SkiPass;

public class Lift {

  private final SkiPassControl skiPassControl;

  public Lift(SkiPassControl skiPassControl) {
    this.skiPassControl = skiPassControl;
  }

  public boolean openGate(SkiPass skiPass) {
    Integer remainingRides = skiPassControl.getRemainingRidesFor(skiPass);

    if (remainingRides >= 1) {
      skiPassControl.reduceRemainingRidesFor(skiPass);

      return true;
    } else {
      return false;
    }
  }

}
