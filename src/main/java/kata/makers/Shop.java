package kata.makers;

import kata.makers.model.SkiPass;

public class Shop {

  SkiPassControl skiPassControl;

  public Shop(SkiPassControl skiPassControl) {
    this.skiPassControl = skiPassControl;
  }

  public SkiPass getSkiPass() {
    return skiPassControl.registerNewSkiPass();
  }
}