package my.cake.shop;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {

  private String name;
  private Cake cake;

  public Customer(Cake cake){
    this.cake =cake;
  }

}
