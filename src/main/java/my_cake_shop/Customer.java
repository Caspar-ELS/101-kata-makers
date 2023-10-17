package my_cake_shop;


import lombok.Builder;
import lombok.Setter;
import my_cake_shop.cake_properties.Colors;
import my_cake_shop.cake_properties.Sizes;
import my_cake_shop.cake_properties.Types;

@Builder
public class Customer {

  private Colors cakeColor;
  private Types cakeType;
  private Sizes cakeSize;

  public Colors getCakeColor() {
    return cakeColor;
  }

  public Types getCakeType() {
    return cakeType;
  }

  public Sizes getCakeSize() {
    return cakeSize;
  }
}
