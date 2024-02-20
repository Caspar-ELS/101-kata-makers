package kata.makers;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  public void buySkiPass(User user) {

    user.setValidPass(true);
    user.setSkiPass(new SkiPass());
  }

  public void scanSkiPass(User user) throws Exception {
    if (isSkiPassValid(user)) {
      user.getSkiPass().setRides(user.getSkiPass().getRides()-1);
    } else {
      throw new Exception("Pass not valid anymore");
    }
  }

  private boolean isSkiPassValid(User user) {
    return user.getSkiPass().getRides() >= 1;
  }
}
