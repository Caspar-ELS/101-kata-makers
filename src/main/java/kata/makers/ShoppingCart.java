package kata.makers;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

  private List<Double> productPrices;

  public ShoppingCart(){
    productPrices = new ArrayList<>();
  }

  public void addItemToThePriceList(Double amount){
    productPrices.add(amount);
  }

  public Double getTotalPrice(){
    return productPrices.stream().reduce(0.0, Double::sum);
  }
}
