package my.cake.shop;

import lombok.Getter;

@Getter
public class Customer {

  private String colourPreference;
  private String typePreference;
  private String sizePreference;

  public Customer(String colourPreference, String typePreference, String sizePreference) {
    this.colourPreference = colourPreference;
    this.typePreference = typePreference;
    this.sizePreference = sizePreference;
  }
}
