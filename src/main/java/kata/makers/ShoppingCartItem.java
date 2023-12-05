package kata.makers;

public class ShoppingCartItem {

  public String name;
  public Double price;

  public ShoppingCartItem(String name, Double price){
    this.name = name;
    this.price = price;
  }

  public String getName(){
    return this.name;
  }

  public Double getPrice(){
    return this.price;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setPrice(Double price){
    this.price = price;
  }


}
