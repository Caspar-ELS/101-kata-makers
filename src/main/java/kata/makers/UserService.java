package kata.makers;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  public void buySkiPass(User user) {

    user.setValidPass(true);
    user.setSkiPass(new SkiPass());
  }

  public void scanSkiPass(User user) {
    if (isSkiPassValid(user)) {
      user.getSkiPass().setRides(user.getSkiPass().getRides()-1);
    }
  }

  private boolean isSkiPassValid(User user) {
    return user.getSkiPass().getRides() > 1;
  }
}
