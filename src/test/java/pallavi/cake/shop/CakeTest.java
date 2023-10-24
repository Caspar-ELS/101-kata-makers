package pallavi.cake.shop;

import org.junit.jupiter.api.Test;

class CakeTest {

  Cakes myCake = new Cakes();

  @Test
  void shouldReturnRenameMyCake(){
    myCake.Cake("Red", "Medium", "Chocolate");
    myCake.displayRenameCakeInfo();
  }

  @Test
  void shouldReturnCakePrice(){
    myCake.getPrice("Small");
    myCake.getPrice("Medium");
    myCake.getPrice("Large");
  }

  @Test
  void shouldReturnCakeExpiry(){
    myCake.getCakeFreshnessChecker();
  }
}


