package kata.makers;

import kata.makers.model.SkiPass;

public class Shop {

  long nextId = 0;

  public SkiPass getSkiPass() {
    SkiPass skiPass = new SkiPass(nextId);

    nextId++;

    return skiPass;
  }
}