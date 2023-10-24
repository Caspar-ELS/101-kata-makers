package pallavi.cake.shop;

import lombok.Getter;

public class Inventory {

  private int eggs = 12;
  private double sugarGrams = 1000;
  private double flourGrams = 2000;

  int eggsRequired;
  double sugarRequired;
  double flourRequired;

  int eggsRequiredForSmallCake = 2;
  double sugarRequiredForSmallCake = 250;
  double flourRequiredForSmallCake = 450;

  int eggsRequiredForMediumCake = 3;
  double sugarRequiredForMediumCake = 350;
  double flourRequiredForMediumCake = 600;

  int eggsRequiredForLargeCake = 4;
  double sugarRequiredForLargeCake = 500;
  double flourRequiredForLargeCake = 600;

  int totalRestockCost = 0;
  int totalRofit = 0;

  @Getter
  Shop shop = new Shop();

  public void makeRequestedCake(String sizeOfCake) {
    this.checkIngredientsAndMakeCake(sizeOfCake);
    this.getTotalProfit(sizeOfCake);
    System.out.println("Total profit : "+this.totalRofit);
  }

  private void getTotalProfit(String sizeOfCake) {
    shop.getMoneyEarned(sizeOfCake);
    this.totalRofit = shop.getMoneyearned() - this.totalRestockCost;
  }

  private void checkIngredientsAndMakeCake(String sizeOfCake) {
    this.getIngrediant(sizeOfCake);
    if (!(this.eggs >= this.eggsRequired && this.sugarGrams >= this.sugarRequired && this.flourGrams >= this.flourRequired)) {
      System.out.println("Not enough ingredients to make a " + sizeOfCake + " cake.");
      this.getInventoryAutoRestock(this.eggsRequired, this.sugarRequired, this.flourRequired);
    }
    this.makeCake();
    System.out.println(sizeOfCake + " cake made successfully.");
    this.checkInventory();
  }

  private void getIngrediant(String sizeOfCake){
    switch (sizeOfCake) {
      case "Small":
        this.eggsRequired = this.eggsRequiredForSmallCake;
        this.sugarRequired = this.sugarRequiredForSmallCake;
        this.flourRequired = this.flourRequiredForSmallCake;
        break;
      case "Medium":
        this.eggsRequired = this.eggsRequiredForMediumCake;
        this.sugarRequired = this.sugarRequiredForMediumCake;
        this.flourRequired = this.flourRequiredForMediumCake;
        break;
      case "Large":
        this.eggsRequired = this.eggsRequiredForLargeCake;
        this.sugarRequired = this.sugarRequiredForLargeCake;
        this.flourRequired = this.flourRequiredForLargeCake;
        break;
    }
    System.out.println("Ingredient required to make "+sizeOfCake+" size cake are----> "+ "Eggs: "+this.eggsRequired+ ", sugar (grams) : "+this.sugarRequired+ ", flour (grams) : "+this.flourRequired);
  }
  private void makeCake() {
    this.eggs -= eggsRequired;
    this.sugarGrams -= sugarRequired;
    this.flourGrams -= flourRequired;
  }

  private void getInventoryAutoRestock(int eggs, double sugar, double flour) {
    if (this.eggs < eggs) {
      autoRestockEggs();
    }
    if (this.sugarGrams < sugar){
      autoRestockFlour();
    }
    if (this.flourGrams < flour) {
      autoRestockSugar();
    }
    System.out.println("Total restock cost is :" + this.totalRestockCost);
  }


  private void autoRestockEggs() {
    this.eggs +=12;
    this.totalRestockCost += 10;
  }

  private void autoRestockFlour() {
    this.flourGrams += 2000;
    this.totalRestockCost += 30;
  }

  private void autoRestockSugar() {
    this.sugarGrams += 1000;
    this.totalRestockCost += 13;
  }

  public void checkInventory() {
    System.out.println("Inventory Status:");
    System.out.println("Eggs: " + this.eggs);
    System.out.println("Sugar (grams): " + this.sugarGrams);
    System.out.println("Flour (grams): " + this.flourGrams);
  }

}

