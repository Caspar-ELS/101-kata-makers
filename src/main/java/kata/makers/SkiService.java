package kata.makers;

import kata.makers.exception.SkiPassInvalidException;
import org.springframework.stereotype.Service;

@Service
public class SkiService {

  public void buySkiPass(User user) {

    user.setPassBought(true);
    user.setSkiPass(new SkiPass());
  }

  public void scanSkiPass(User user) throws SkiPassInvalidException {
    if (isSkiPassValid(user)) {
      user.getSkiPass().setRides(user.getSkiPass().getRides()-1);
    } else {
      throw new SkiPassInvalidException("Pass not valid anymore");
    }
  }

  private boolean isSkiPassValid(User user) {
    return user.isPassBought() && user.getSkiPass().getRides() >= 1;
  }
}
