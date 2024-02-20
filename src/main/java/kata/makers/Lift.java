package kata.makers;

import kata.makers.model.SkiPass;

public class Lift {

  public boolean openGate(SkiPass skiPass) {
    if (skiPass.getRemainingRides() >= 1) {
      skiPass.reduceRemainingRides();
      return true;
    } else {
      return false;
    }
  }
}
