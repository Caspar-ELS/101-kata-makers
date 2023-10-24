package pallavi.cake.shop;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shop {
  private int numberOfCakes =0;
  private int moneyearned = 0;
  Map<String, Integer> cakesByColor = new HashMap<>();
  @Getter
  Cakes cake = new Cakes();


  public void addCakes(int cakesToAdd, String colourOfCake) {
    this.numberOfCakes += cakesToAdd;
    this.cakesByColor.put(colourOfCake, cakesByColor.getOrDefault(colourOfCake, 1) );
  }

  public int getCakeCount() {
    System.out.println("Number of cakes in the shop: " + this.numberOfCakes);
    return this.numberOfCakes;
  }

  public void sellCake(int cakesToSell, String sizeOfCake, String typeOfCake) {
    if (cakesByColor.containsKey(typeOfCake) && cakesByColor.get(typeOfCake) > 0) {
      cakesByColor.put(typeOfCake, cakesByColor.get(typeOfCake) - 1);
      System.out.println("Sold " + cakesToSell + " cake(s) to the customer.");
    } else {
      System.out.println("Sorry, " + cakesToSell + " cake(s) is(are) not available.");
    }
    getMoneyEarned(sizeOfCake);
  }

  public void getMoneyEarned(String sizeOfCake){
    this.moneyearned += cake.getPrice(sizeOfCake);
    System.out.println("Money earned so far is : "+ this.moneyearned);
  }

public void getRemainingCakesInTheShop(){
  System.out.println("Remaining cakes in the shop:");
  for (Map.Entry<String, Integer> entry : cakesByColor.entrySet()) {
    System.out.println(entry.getKey() + " cakes: " + entry.getValue());
  }
}
}
