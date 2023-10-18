package my.cake.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import my.cake.shop.model.CakeSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InventoryTest {

  public static Stream<Arguments> getRemainingIngredientsQuantity() {
    return Stream.of(Arguments.of(
        10, 750, 1550, CakeSize.SMALL,
        9, 650, 1400, CakeSize.MEDIUM,
        8, 500, 1200, CakeSize.LARGE
    ));
  }

  @Test
  void shouldCheckTheInventoryForHowMuchIngredientsLeft() {
    Inventory inventory = new Inventory();
    assertEquals(12, inventory.getNoOfEggs());
    assertEquals(1000, inventory.getSugarInGrams());
    assertEquals(2000, inventory.getFlourInGrams());
  }

  @ParameterizedTest
  @MethodSource("getRemainingIngredientsQuantity")
  void shouldReturnTheReducedInventoryAfterMakingACakeWithIngredientsFromInventory(int eggs,
      int sugar, int flour, CakeSize cakeSize) {
    Inventory inventory = new Inventory();
    inventory.makeCakeFromIngredients(cakeSize);
    assertEquals(eggs, inventory.getNoOfEggs());
    assertEquals(sugar, inventory.getSugarInGrams());
    assertEquals(flour, inventory.getFlourInGrams());
  }

  @Test
  void shouldReturnTheReducedInventoryAfterMakingACakeWithIngredientsFromInventory() {
    Inventory inventory = new Inventory();
    inventory.makeCakeFromIngredients(CakeSize.LARGE);
    inventory.makeCakeFromIngredients(CakeSize.LARGE);
    inventory.makeCakeFromIngredients(CakeSize.LARGE);

    assertEquals(0, inventory.getNoOfEggs());
    assertEquals(500, inventory.getSugarInGrams());
    assertEquals(1600, inventory.getFlourInGrams());
    assertEquals(43, inventory.getRestockingCost());
  }
}

