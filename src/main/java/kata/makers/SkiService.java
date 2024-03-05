package kata.makers;

import kata.makers.exception.NotEnoughCreditException;
import kata.makers.exception.SkiPassInvalidException;
import org.springframework.stereotype.Service;

@Service
public class SkiService {

  private static final int RIDE_FEE = 20;

  public void buySkiPass(User user) {

    user.setPassBought(true);
    user.setSkiPass(new SkiPass());
  }

  public void scanSkiPass(User user) throws SkiPassInvalidException {
    if (isSkiPassValid(user)) {
      user.getSkiPass().setRides(user.getSkiPass().getRides()-1);
    } else if (user.isPassBought() && user.getSkiPass().getCredit() > 0) {
      user.getSkiPass().charge(RIDE_FEE);
    } else {
      throw new SkiPassInvalidException("Pass not valid anymore");
    }
  }

  public void buyItem(User user, Item item) throws NotEnoughCreditException {
    if (user.getSkiPass().getCredit() >= item.price) {
      user.getSkiPass().charge(item.price);
      user.getItems().add(item);
    } else {
      throw new NotEnoughCreditException("You don't have enough credit to buy the item, please top up");
    }
  }

  private boolean isSkiPassValid(User user) {
    return user.isPassBought() && user.getSkiPass().getRides() >= 1;
  }
}
