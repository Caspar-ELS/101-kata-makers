package kata.makers;

public class Shop {

  long nextId = 0;

  public SkiPass getSkiPass() {
    SkiPass skiPass = new SkiPass(nextId);

    nextId++;

    return skiPass;
  }
}