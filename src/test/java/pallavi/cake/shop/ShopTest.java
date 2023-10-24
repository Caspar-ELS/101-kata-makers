package pallavi.cake.shop;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class ShopTest {
  Shop myCakeShop = new Shop();
  @Test
  void shouldReturnNumberOfCakes(){
    myCakeShop.addCakes(3,"");
    myCakeShop.getCakeCount();

    myCakeShop.addCakes(7,"");
    myCakeShop.getCakeCount();

    myCakeShop.addCakes(0,"");
    myCakeShop.getCakeCount();
  }

  @Test
  void shouldReturnRemainingCakesInTheShop(){
    myCakeShop.addCakes(1,"Chocolate");
    myCakeShop.addCakes(1,"White");
    myCakeShop.addCakes(1,"Pink");
    myCakeShop.addCakes(1,"Red");

    myCakeShop.sellCake(1, "Small","Chocolate");
    myCakeShop.sellCake(1, "Medium","Red");

    myCakeShop.getRemainingCakesInTheShop();
  }

}
