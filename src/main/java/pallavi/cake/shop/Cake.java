package pallavi.cake.shop;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Cake {

  private String color;
  private String size;
  private String type;

  // get cake
  public Cake(String color, String size, String type) {
    this.color = color;
    this.size = size;
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getSize() {
    return size;
  }

  public String getType() {
    return type;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void getPrice() {

    switch (this.size) {
      case "Small":
        System.out.println("Price of the small Cake is: " + "£20");
        break;
      case "Medium":
        System.out.println("Price of the medium Cake is: " + "£30");
        break;
      case "Large":
        System.out.println("Price of the large Cake is: " + "£50");
        break;
      default:
        System.out.println("Please select color ,size and type of cake to get its price");
    }

  }


  public void displayCakeInfo() {
    System.out.println("Name of the selected cake : " + this.color + " " + this.size + " " + this.type);
  }

  public void getCakeFreshnessChecker() {
    LocalDate currentDate = LocalDate.now();
    LocalDate expiryDate = LocalDate.of(2023, 9, 30);
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
