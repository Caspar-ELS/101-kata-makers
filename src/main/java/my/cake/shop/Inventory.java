package my.cake.shop;

import static my.cake.shop.model.CakeSize.LARGE;
import static my.cake.shop.model.CakeSize.MEDIUM;
import static my.cake.shop.model.CakeSize.SMALL;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import my.cake.shop.model.CakeSize;

@Getter
@Setter
public class Inventory {

  private static final int INITIAL_EGG_STOCK = 12;
  private static final int INITIAL_SUGAR_STOCK = 1000;
  private static final int INITIAL_FLOUR_STOCK = 2000;
  private static final Map<CakeSize, Inventory> ingredientsByCakeType = Map.of(
      SMALL, new Inventory(2, 250, 450),
      MEDIUM, new Inventory(3, 350, 600),
      LARGE, new Inventory(4, 500, 800)
  );
  private int noOfEggs;
  private int sugarInGrams;
  private int flourInGrams;
  private int restockingCost;

  public Inventory() {
    this.noOfEggs = INITIAL_EGG_STOCK;
    this.sugarInGrams = INITIAL_SUGAR_STOCK;
    this.flourInGrams = INITIAL_FLOUR_STOCK;
    this.restockingCost = 0;
  }

  public Inventory(int eggs, int sugar, int flour) {
    this.noOfEggs = eggs;
    this.sugarInGrams = sugar;
    this.flourInGrams = flour;
  }

  public void makeCakeFromIngredients(CakeSize cakeSize) {
    this.noOfEggs = checkStock(ingredientsByCakeType.get(cakeSize).getNoOfEggs(), INITIAL_EGG_STOCK,
        this.getNoOfEggs(), 10);

    this.sugarInGrams = checkStock(ingredientsByCakeType.get(cakeSize).getSugarInGrams(),
        INITIAL_SUGAR_STOCK,
        this.getSugarInGrams(), 13);

    this.flourInGrams = checkStock(ingredientsByCakeType.get(cakeSize).getFlourInGrams(),
        INITIAL_FLOUR_STOCK,
        this.getFlourInGrams(), 30);
    switch (cakeSize) {
      case SMALL -> updateInventoryForSmallCake();
      case MEDIUM -> updateInventoryForMediumCake();
      case LARGE -> updateInventoryForLargeCake();
    }
  }

  private int checkStock(int requiredStock, int initialStock, int currentStock,
      int restockingCost) {
    if (currentStock < requiredStock) {
      this.restockingCost += restockingCost;
      return currentStock + initialStock;
    }
    return currentStock;
  }

  private void updateInventoryForSmallCake() {
    this.noOfEggs -= 2;
    this.sugarInGrams -= 250;
    this.flourInGrams -= 450;
  }

  private void updateInventoryForMediumCake() {
    this.noOfEggs -= 3;
    this.sugarInGrams -= 350;
    this.flourInGrams -= 600;
  }

  private void updateInventoryForLargeCake() {
    this.noOfEggs -= 4;
    this.sugarInGrams -= 500;
    this.flourInGrams -= 800;
  }
}
