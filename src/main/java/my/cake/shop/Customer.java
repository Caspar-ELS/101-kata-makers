package my.cake.shop;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {

  private Color preferredColor;
  private Type preferredType;
  private Size preferredSize;
}
