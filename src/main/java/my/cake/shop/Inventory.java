package my.cake.shop;
import lombok.Getter;

@Getter
public class Inventory {

  private static final int CARTON_OF_EGGS = 12;
  private static final int BAG_OF_FLOUR = 2000;
  private static final int BAG_OF_SUGAR = 1000;
  private static final int SUGAR_PRICE = 13;
  private static final int EGGS_PRICE = 10;
  private static final int FLOUR_PRICE = 30;
  private int eggs;
  private int flour;
  private int sugar;
  private int restockCost;

  public Inventory() {
    this.eggs = 12;
    this.flour = 2000;
    this.sugar = 1000;
  }

  public int getIngredientsForCake(String cakeSize) {
    if (cakeSize.equals("small")) {
      eggs -= 2;
      flour -= 450;
      sugar -= 250;
    } else if (cakeSize.equals("medium")) {
      eggs -= 3;
      flour -= 600;
      sugar -= 350;
    } else if (cakeSize.equals("large")) {
      eggs -= 4;
      flour -= 800;
      sugar -= 500;
    }
    return restockInventoryIfOutOfStock();
  }

  private int restockInventoryIfOutOfStock() {
    if (eggs <= 0) {
      eggs += CARTON_OF_EGGS;
      restockCost += EGGS_PRICE;
    }
    if (flour <= 0) {
      flour += BAG_OF_FLOUR;
      restockCost += FLOUR_PRICE;
    }
    if (sugar <= 0) {
      sugar += BAG_OF_SUGAR;
      restockCost += SUGAR_PRICE;
    }
    return restockCost;
  }
}
