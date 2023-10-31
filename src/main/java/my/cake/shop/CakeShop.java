package my.cake.shop;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CakeShop {

  private List<Cake> cakes = new ArrayList<>();
  private double cash;
  private Inventory inventory;

  public CakeShop() {
    this.inventory = new Inventory();
  }

  public CakeShop(Inventory inventory) {
    this.inventory = inventory;
  }

  public void addCake(Cake cake) {
    cakes.add(cake);
    inventory.update(cake);
  }

  public int checkNumberOfCakes() {
    return cakes.size();
  }

  public Cake sellCakeTo(Customer customer) {
    for (int i = 0; i < cakes.size(); i++) {
      Cake cake = cakes.get(i);

      if ((customer.getPreferredColor() != null) && cake.getColor() != customer.getPreferredColor()) {
        continue;
      }
      if ((customer.getPreferredType() != null) && cake.getType() != customer.getPreferredType()) {
        continue;
      }

      cakes.remove(cake);
      cash += cake.getPrice();
      cash -= inventory.getCost();
      return cake;
    }
    Cake newCake = new Cake(customer.getPreferredColor(), customer.getPreferredSize(), customer.getPreferredType());
    addCake(newCake);
    return sellCakeTo(customer);
  }

  public double checkCashRegister() {
    return cash;
  }
}
