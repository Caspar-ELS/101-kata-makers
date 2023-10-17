package my_cake_shop;


import lombok.Getter;
import my_cake_shop.cake_properties.Sizes;

public class Inventory {
  private static final int EGGS_BASE_AMOUNT = 12;
  private static final int SUGAR_BASE_AMOUNT = 1000;
  private static final int FLOUR_BASE_AMOUNT = 2000;

  private static final int EGGS_PRICE = 10;
  private static final int SUGAR_PRICE = 13;
  private static final int FLOUR_PRICE = 30;

  @Getter
  private int eggsAmount;
  @Getter
  private int sugarGr;
  @Getter
  private int flourGr;

  public Inventory() {
    this.eggsAmount = EGGS_BASE_AMOUNT;
    this.sugarGr = SUGAR_BASE_AMOUNT;
    this.flourGr = FLOUR_BASE_AMOUNT;
  }

  public int takeIngredientsForCakeAndReturnPrice(Sizes size) {
    if (size.equals(Sizes.SMALL)) {
      this.eggsAmount -= 2;
      this.sugarGr -= 250;
      this.flourGr -= 450;
    }
    if (size.equals(Sizes.MEDIUM)) {
      this.eggsAmount -= 3;
      this.sugarGr -= 350;
      this.flourGr -= 600;
    }
    if (size.equals(Sizes.LARGE)) {
      this.eggsAmount -= 4;
      this.sugarGr -= 500;
      this.flourGr -= 800;
    }

    int restockTotalAmount = 0;

    if (this.eggsAmount < 0) {
      restockTotalAmount += this.restockEggs();
    }
    if (this.sugarGr < 0) {
      restockTotalAmount += this.restockSugar();
    }
    if (this.flourGr < 0) {
      restockTotalAmount += this.restockFlour();
    }

    return restockTotalAmount;
  }

  private int restockEggs() {
    this.eggsAmount += EGGS_BASE_AMOUNT;
    return EGGS_PRICE;
  }

  private int restockSugar() {
    this.sugarGr += SUGAR_BASE_AMOUNT;
    return SUGAR_PRICE;
  }

  private int restockFlour() {
    this.flourGr += FLOUR_BASE_AMOUNT;
    return FLOUR_PRICE;
  }
}
