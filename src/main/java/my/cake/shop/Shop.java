package my.cake.shop;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import my.cake.shop.exception.InvalidCakeException;

@Getter
@Setter
public class Shop {

  private List<Cake> cakes = new ArrayList<>();
  private int cashRegister;
  private Inventory inventory;

  public void addCake(Cake cake) {
    cakes.add(cake);
  }

  public int getNumberOfCakesInStock() {
    return cakes.size();
  }

  public Cake sellCakeWithSpecificColour(Customer customer) throws InvalidCakeException {
    if (!cakes.isEmpty()) {
      for (Cake cake : cakes) {
        if (cake.getColour().equals(customer.getColourPreference())) {
          cakes.remove(cake);
          cashRegister += cake.getCakePrice(cake.getSize());
          return cake;
        }
      }
    }
      Cake cake = bakeNewCakeAndDeductCosts(customer);
      cashRegister += cake.getCakePrice(cake.getSize());
      return cake;
  }

  private Cake bakeNewCakeAndDeductCosts(Customer customer) {
    Cake cake = new Cake(customer.getColourPreference(), customer.getTypePreference(),
        customer.getSizePreference());
    cashRegister -= inventory.getIngredientsForCake(cake.getSize());
    return cake;
  }

  public int getAmountInCashRegister() {
    return cashRegister;
  }
}
