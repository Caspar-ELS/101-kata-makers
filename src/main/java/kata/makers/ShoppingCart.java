package kata.makers;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

  private List<Integer> prices = new ArrayList<>();

  public ShoppingCart() {
  }

  public ShoppingCart(List<Integer> prices) {
    this.prices = prices;
  }

  public List<Integer> getPrices() {
    return this.prices;
  }

  public void addPrice(Integer price) {
    this.prices.add(price);
  }

  public Integer calculateTotal() {
    return this.prices.stream().reduce(0, Integer::sum);
  }
}
