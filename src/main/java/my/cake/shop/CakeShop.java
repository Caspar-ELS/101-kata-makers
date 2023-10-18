package my.cake.shop;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CakeShop {

  private final List<Cake> cakes;
  private long amountCollected;
  private Inventory inventory;

  public CakeShop(){
    this.cakes = new ArrayList<>();
    this.inventory = new Inventory();
  }

  public void addCakesToTheShop(Cake cake){
    this.cakes.add(cake);
  }

  public int getAvailableNumberOfCake(){
    this.cakes.removeAll(this.cakes.stream()
        .filter(Cake::isCakeExpired)
        .toList());
    return this.cakes.size();
  }

  public Cake sellCake(Customer customer){
    Cake cake = customer.getCake();
    if(this.cakes.contains(cake)){
      amountCollected += cake.getCakePrice();
      this.cakes.remove(cake);
      return cake;
    }else{
      return makeCakeFromIngredients(cake);
    }

  }

  private Cake makeCakeFromIngredients(Cake cake) {
    amountCollected += cake.getCakePrice();
    inventory.makeCakeFromIngredients(cake.getSize());
   amountCollected -= inventory.getRestockingCost();
    return cake;
  }

}
