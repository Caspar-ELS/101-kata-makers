package my_cake_shop;

import my_cake_shop.cake_properties.Sizes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InventoryTest {

  @Test
  public void shouldReturnInventorySize() {
    Inventory inventory = new Inventory();
    Assertions.assertEquals(12, inventory.getEggsAmount());
    Assertions.assertEquals(1000, inventory.getSugarGr());
    Assertions.assertEquals(2000, inventory.getFlourGr());
  }

  @Test
  public void shouldBeAbleToGetIngredientsForSmallCake() {
    Inventory inventory = new Inventory();

    inventory.takeIngredientsForCakeAndReturnPrice(Sizes.SMALL);

    Assertions.assertEquals(10, inventory.getEggsAmount());
    Assertions.assertEquals(750, inventory.getSugarGr());
    Assertions.assertEquals(1550, inventory.getFlourGr());
  }

  @Test
  public void shouldBeAbleToGetIngredientsForMediumCake() {
    Inventory inventory = new Inventory();

    inventory.takeIngredientsForCakeAndReturnPrice(Sizes.MEDIUM);

    Assertions.assertEquals(9, inventory.getEggsAmount());
    Assertions.assertEquals(650, inventory.getSugarGr());
    Assertions.assertEquals(1400, inventory.getFlourGr());
  }

  @Test
  public void shouldBeAbleToGetIngredientsForLargeCake() {
    Inventory inventory = new Inventory();

    inventory.takeIngredientsForCakeAndReturnPrice(Sizes.LARGE);

    Assertions.assertEquals(8, inventory.getEggsAmount());
    Assertions.assertEquals(500, inventory.getSugarGr());
    Assertions.assertEquals(1200, inventory.getFlourGr());
  }

  @Test
  public void shouldRestockWhenNotEnough() {
    Inventory inventory = new Inventory();

    inventory.takeIngredientsForCakeAndReturnPrice(Sizes.MEDIUM);
    inventory.takeIngredientsForCakeAndReturnPrice(Sizes.SMALL);
    inventory.takeIngredientsForCakeAndReturnPrice(Sizes.MEDIUM);
    inventory.takeIngredientsForCakeAndReturnPrice(Sizes.MEDIUM);
    inventory.takeIngredientsForCakeAndReturnPrice(Sizes.LARGE);

    Assertions.assertEquals(9, inventory.getEggsAmount());
    Assertions.assertEquals(200, inventory.getSugarGr());
    Assertions.assertEquals(950, inventory.getFlourGr());
  }

  @Test
  public void shouldReturnTotalPriceWhenRestocking() {
    Inventory inventory = new Inventory();

    Assertions.assertEquals(12, inventory.getEggsAmount());
    Assertions.assertEquals(1000, inventory.getSugarGr());
    Assertions.assertEquals(2000, inventory.getFlourGr());

    Assertions.assertEquals(0, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.MEDIUM));
    Assertions.assertEquals(0, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.SMALL));
    Assertions.assertEquals(13, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.LARGE));
    Assertions.assertEquals(30, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.MEDIUM));
    Assertions.assertEquals(10, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.SMALL));
    Assertions.assertEquals(13, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.LARGE));
    Assertions.assertEquals(30, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.MEDIUM));
    Assertions.assertEquals(0, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.SMALL));
    Assertions.assertEquals(23, inventory.takeIngredientsForCakeAndReturnPrice(Sizes.SMALL));
  }
}