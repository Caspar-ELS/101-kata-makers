package my.cake.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class InventoryTest {

  @Test
  void willStockInventoryWithDefaultAmount() {
    Inventory inventory = new Inventory();

    assertEquals(2000, inventory.getFlour());
    assertEquals(12, inventory.getEggs());
    assertEquals(1000, inventory.getSugar());
  }

  @Test
  void willRemoveIngredientsNeededForASmallCake() {
    Inventory inventory = new Inventory();

    inventory.getIngredientsForCake("small");

    assertEquals(10, inventory.getEggs());
    assertEquals(1550, inventory.getFlour());
    assertEquals(750, inventory.getSugar());
  }

  @Test
  void willRemoveIngredientsNeededForAMediumCake() {
    Inventory inventory = new Inventory();

    inventory.getIngredientsForCake("medium");

    assertEquals(9, inventory.getEggs());
    assertEquals(1400, inventory.getFlour());
    assertEquals(650, inventory.getSugar());
  }

  @Test
  void willRemoveIngredientsNeededForALargeCake() {
    Inventory inventory = new Inventory();

    inventory.getIngredientsForCake("large");

    assertEquals(8, inventory.getEggs());
    assertEquals(1200, inventory.getFlour());
    assertEquals(500, inventory.getSugar());
  }

  @Test
  void willRestockIngredientsWhenTheyHaveRunOut() {
    Inventory inventory = new Inventory();

    getIngredientsForThreeLargeCakes(inventory);

    assertEquals(12, inventory.getEggs());
    assertEquals(1600, inventory.getFlour());
    assertEquals(500, inventory.getSugar());
  }

  @Test
  void willChargeCustomerForRestockedIngredients() {
    Inventory inventory = new Inventory();

    getIngredientsForThreeLargeCakes(inventory);

    assertEquals(53, inventory.getRestockCost());
  }

  private void getIngredientsForThreeLargeCakes(Inventory inventory) {
    inventory.getIngredientsForCake("large");
    inventory.getIngredientsForCake("large");
    inventory.getIngredientsForCake("large");
  }

}
