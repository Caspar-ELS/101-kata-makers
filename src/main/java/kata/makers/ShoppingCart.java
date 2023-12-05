package kata.makers;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

  private List<Double> productPrices = new ArrayList<>();

  public ShoppingCart() {
  }

  public void addItemPriceToCart(double price) {
    productPrices.add(price);
  }

  public List<Double> getProductPrices() {
    return productPrices;
  }

  public double getTotalPrice() {
    return productPrices.stream().reduce(0.0, Double::sum);
  }
}
