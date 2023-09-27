package pallavi.cake.shop;

import org.junit.jupiter.api.Test;

class CakeTest {

  @Test
  void testNameMyCake(){
    Cake myCake = new Cake("Red", "Large", "Chocolate");
    myCake.displayCakeInfo();
  }

  @Test
  void testReNameMyCake(){
    Cake myCake = new Cake("Red", "Medium", "Chocolate");
    myCake.displayRenameCakeInfo();
  }

  @Test
  void testCakePrice(){
    Cake myCake1 = new Cake("Yellow", "Small", "Pineapple");
    myCake1.getPrice();

    Cake myCake2 = new Cake("Brown", "Medium", "Chocolate");
    myCake2.getPrice();

    Cake myCake3 = new Cake("Orange", "Large", "Carrot");
    myCake3.getPrice();
  }

  @Test
  void testCakeExpiry(){
    Cake myCake = new Cake("Red", "Large", "Chocolate");
    myCake.getCakeFreshnessChecker();
  }

}


