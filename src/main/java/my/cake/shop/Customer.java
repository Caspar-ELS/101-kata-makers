package my.cake.shop;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Customer {

  private Color preferredColor;
  private Type preferredType;

}
