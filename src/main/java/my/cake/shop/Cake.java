package my.cake.shop;

import java.util.Date;

public class Cake {
  public String createCake(String color, String type, String size) {
    return createCake(color, type, size, "");
  }

  public String createCake(String color, String type, String size, String specialName) {
    if(specialName.isEmpty()){
      return String.format("%s %s %s cake", size, color, type);
    }
    return specialName;
  }

  public String getThePrice(Integer sPrice, Integer mPrice, Integer lPrice, String desiredSize){

    if(outOfBounds(sPrice) || outOfBounds(mPrice) || outOfBounds(lPrice)){
      return "Error, price out of bounds";
    }

    if(desiredSize.equals("small")){
      return String.format("The price is %d", sPrice);
    }
    if(desiredSize.equals("medium")){
      return String.format("The price is %d", mPrice);
    }
    if(desiredSize.equals("large")){
      return String.format("The price is %d", lPrice);
    }
    return "Error";
  }

  public Integer getFreshMinutesLeft(Date cakeCreatedTime, Date currentTime) throws Exception {
    if(cakeCreatedTime.after(currentTime)){
      throw new Exception("Cake created time is after current time");
    }

    long timeDiff = currentTime.getTime() - cakeCreatedTime.getTime();
    System.out.println("cakeCreatedDate: " + cakeCreatedTime);
    System.out.println("current time: " + new Date());
    System.out.println("timeDiff: " + timeDiff);

    if ( 3600000 >= timeDiff){
      return (int) (timeDiff / 1000 / 60);
    }else{
      throw new Exception("Cake is not fresh");
    }
  }


  private boolean outOfBounds(Integer price){
    return price < 20 || price > 50;
  }
}
