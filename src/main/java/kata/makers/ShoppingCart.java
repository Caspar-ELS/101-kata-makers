package kata.makers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {

  public List<ShoppingCartItem> shoppingCartItems;

  private Double discountPercentOff = null;

  public ShoppingCart() {
    this.shoppingCartItems = new ArrayList<>();
  }

  public void addItem(ShoppingCartItem newItem) throws Exception {
    if (shoppingCartItems.stream()
        .anyMatch(shoppingCartItem -> shoppingCartItem.getName().equals(newItem.getName()))) {
      throw new Exception("duplicate name");
    }
    shoppingCartItems.add(newItem);
  }

  public Double calculateTotalPrice() {
    Double total = shoppingCartItems.stream().map(ShoppingCartItem::getPrice).reduce(0.0, Double::sum);

    return Objects.isNull(discountPercentOff) ? total : total * getDiscountRate(discountPercentOff);
  }

  public void setDiscountPercentOff(Double discountPercentOff){
    this.discountPercentOff = discountPercentOff;
  }

  private Double getDiscountRate(Double discountPercentOff){
    return ((100 - discountPercentOff) / 100);
  }
}
