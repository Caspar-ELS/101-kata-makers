package my.cake.shop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


class InventoryTest {

  @InjectMocks
  private Inventory inventory;

  @BeforeEach
  public void setup() {
    openMocks(this);
  }

  @Test
  void shouldRemoveIngredientsAfterMakingSmallCake() {
    CakeShop cakeShop = new CakeShop(inventory);
    Cake cake = new Cake(Color.BLUE, Size.SMALL, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    assertEquals(10, inventory.getEggs());
    assertEquals(750, inventory.getSugar());
    assertEquals(1550, inventory.getFlour());
  }

  @Test
  void shouldRemoveIngredientsAfterMakingMediumCake() {
    CakeShop cakeShop = new CakeShop(inventory);
    Cake cake = new Cake(Color.BLUE, Size.MEDIUM, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    assertEquals(9, inventory.getEggs());
    assertEquals(650, inventory.getSugar());
    assertEquals(1400, inventory.getFlour());
  }

  @Test
  void shouldRemoveIngredientsAfterMakingLargeCake() {
    CakeShop cakeShop = new CakeShop(inventory);
    Cake cake = new Cake(Color.BLUE, Size.LARGE, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    assertEquals(8, inventory.getEggs());
    assertEquals(500, inventory.getSugar());
    assertEquals(1200, inventory.getFlour());
  }

  @Test
  void shouldRestockEggsAndUpdateRestockingCost() {
    inventory.setEggs(2);
    CakeShop cakeShop = new CakeShop(inventory);
    Cake cake = new Cake(Color.BLUE, Size.MEDIUM, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    assertEquals(11, inventory.getEggs());
    assertEquals(10, inventory.getCost());
  }

  @Test
  void shouldRestockSugarAndUpdateRestockingCost() {
    inventory.setSugar(200);
    CakeShop cakeShop = new CakeShop(inventory);
    Cake cake = new Cake(Color.BLUE, Size.SMALL, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    assertEquals(950, inventory.getSugar());
    assertEquals(13, inventory.getCost());
  }

  @Test
  void shouldRestockFlourAndUpdateRestockingCost() {
    inventory.setFlour(200);
    CakeShop cakeShop = new CakeShop(inventory);
    Cake cake = new Cake(Color.BLUE, Size.SMALL, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    assertEquals(1750, inventory.getFlour());
    assertEquals(30, inventory.getCost());
  }

  @Test
  void shouldRestockFlourAndEggsAndUpdateRestockingCost() {
    inventory.setFlour(200);
    inventory.setEggs(1);
    CakeShop cakeShop = new CakeShop(inventory);
    Cake cake = new Cake(Color.BLUE, Size.SMALL, Type.CHOCOLATE_CAKE);
    cakeShop.addCake(cake);
    assertEquals(40, inventory.getCost());
  }
}