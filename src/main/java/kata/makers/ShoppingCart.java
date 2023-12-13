package kata.makers;

import java.util.Collection;
import java.util.HashMap;
import kata.makers.exception.DuplicateItemException;

public class ShoppingCart {

  private HashMap<String, Double> shoppingList = new HashMap<>();

  public ShoppingCart() {
  }

  public void addItemPriceToCart(String itemName, double price) throws DuplicateItemException {

    if (!shoppingList.containsKey(itemName)) {
      shoppingList.put(itemName, price);
    } else {
      throw new DuplicateItemException("Item is already on the list");
    }
  }

  public Collection<Double> getShoppingList() {
    return shoppingList.values();
  }

  public double calculateTotal(double discountAmount) {
    return shoppingList.values()
        .stream()
        .mapToDouble(price -> calculateDiscount(price, discountAmount))
        .reduce(0.0, Double::sum);
  }

  private double calculateDiscount(double price, double discountAmount) {
    return price - price * discountAmount;
  }
}
