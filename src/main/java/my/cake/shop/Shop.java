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

  public void addCake(Cake cake) {
    cakes.add(cake);
  }

  public int getNumberOfCakesInStock() {
    return cakes.size();
  }

  public Cake findCakeWithSpecificColour(String colour) throws InvalidCakeException {
    for (Cake cake : cakes) {
      if (cake.getColour().equals(colour)) {
        cakes.remove(cake);
        cashRegister += cake.getCakePriceBasedOnSize(cake.getSize());
        return cake;
      }
    }
    return null;
  }

  public int getAmountInCashRegister() {
    return cashRegister;
  }
}
