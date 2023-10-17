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

  public void addCake(Cake cake) {
    cakes.add(cake);
  }

  public int checkNumberOfCakes() {
    return cakes.size();
  }

  public Cake sellCakeOfColor(Color color) {
    for (Cake cake : cakes) {
      if (cake.getColor().equals(color)) {
        cakes.remove(cake);
        cash += cake.getPrice();
        return cake;
      }
    }
    return null;
  }

  public Cake sellCakeTo(Customer customer) throws CakeException {
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
      return cake;
    }
    throw new CakeException("Can't find required cake in shop");
  }

  public double checkCashRegister() {
    return cash;
  }
}
