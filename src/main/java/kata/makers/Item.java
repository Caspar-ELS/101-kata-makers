package kata.makers;

public enum Item {
  COFFEE(10),
  TEA(5),
  GLOVES(30);

  public final int price;

  Item(int price) {
    this.price = price;
  }
}
