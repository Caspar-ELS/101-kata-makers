package my.cake.shop;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Inventory {

  private static final int EGGS_SMALL_CAKE = 2;
  private static final int EGGS_MEDIUM_CAKE = 3;
  private static final int EGGS_LARGE_CAKE = 4;
  private static final int SUGAR_SMALL_CAKE = 250;
  private static final int FLOUR_SMALL_CAKE = 450;
  private static final int SUGAR_MEDIUM_CAKE = 350;
  private static final int FLOUR_MEDIUM_CAKE = 600;
  private static final int SUGAR_LARGE_CAKE = 500;
  private static final int FLOUR_LARGE_CAKE = 800;
  private static final int EGGS_RESTOCK_COST = 10;
  private static final int SUGAR_RESTOCK_COST = 13;
  private static final int FLOUR_RESTOCK_COST = 30;
  private static final int EGGS_DEFAULT_AMOUNT = 12;
  private static final int SUGAR_DEFAULT_AMOUNT = 1000;
  private static final int FLOUR_DEFAULT_AMOUNT = 2000;
  private int eggs;
  private int sugar;
  private int flour;
  private int cost;

  public Inventory() {
    this.eggs = EGGS_DEFAULT_AMOUNT;
    this.sugar = SUGAR_DEFAULT_AMOUNT;
    this.flour = FLOUR_DEFAULT_AMOUNT;
  }

  public void update(Cake cake) {
    if (cake.getSize().equals(Size.SMALL)) {
      eggs -= EGGS_SMALL_CAKE;
      sugar -= SUGAR_SMALL_CAKE;
      flour -= FLOUR_SMALL_CAKE;
    } else if (cake.getSize().equals(Size.MEDIUM)) {
      eggs -= EGGS_MEDIUM_CAKE;
      sugar -= SUGAR_MEDIUM_CAKE;
      flour -= FLOUR_MEDIUM_CAKE;
    } else {
      eggs -= EGGS_LARGE_CAKE;
      sugar -= SUGAR_LARGE_CAKE;
      flour -= FLOUR_LARGE_CAKE;
    }
    restock();
  }

  private void restock() {
    if (eggs < 0) {
      eggs += EGGS_DEFAULT_AMOUNT;
      cost += EGGS_RESTOCK_COST;
    }
    if (sugar < 0) {
      sugar += SUGAR_DEFAULT_AMOUNT;
      cost += SUGAR_RESTOCK_COST;
    }
    if (flour < 0) {
      flour += FLOUR_DEFAULT_AMOUNT;
      cost += FLOUR_RESTOCK_COST;
    }
    System.out.println("Restock cost: " + cost);
  }
}
