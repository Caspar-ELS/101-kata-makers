package pallavi.cake.shop;


import org.junit.jupiter.api.Test;

public class inventoryTest {
  Inventory inventory = new Inventory();

  @Test
  public void shouldReturnInventoryRestockCostAndProfit() {
    inventory.checkInventory();
    inventory.makeRequestedCake("Small");
    inventory.makeRequestedCake("Large");
    inventory.makeRequestedCake("Medium");
  }

}
