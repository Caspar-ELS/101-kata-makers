package pallavi.cake.shop;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Cakes {

  private String color;
  private String size;
  private String type;

  private int smallCakePrice = 20;
  private int mediumCakePrice = 30;
  private int largeCakePrice = 50;
  private int price;

  public void Cake(String color, String size, String type) {
    this.color = color;
    this.size = size;
    this.type = type;
    System.out.println("Name of the selected cake : " + this.color + " " + this.size + " " + this.type);
  }

  public int getPrice(String size) {

    switch (size) {
      case "Small":
        this.price = this.smallCakePrice;
        break;
      case "Medium":
        this.price = this.mediumCakePrice;
        break;
      case "Large":
        this.price = this.largeCakePrice;
        break;
      default:
        System.out.println("Please select size of cake to get its price");
    }
    System.out.println("Price of the "+size+" cake is: " + this.price);
    return this.price;
  }


  public void getCakeFreshnessChecker() {
    LocalDate currentDate = LocalDate.now();
    LocalDate expiryDate = LocalDate.of(2023, 11, 30);
    long daysLeft = ChronoUnit.DAYS.between(currentDate, expiryDate);
    if (daysLeft > 0) {
      System.out.println("Days left until expiry: " + daysLeft);
    }else{
      System.out.println("Please choose other cake!");
    }
  }

  public void displayRenameCakeInfo() {
    System.out.println("Rename the selected cake : " +"Caspar's amazing cake");
  }
}
