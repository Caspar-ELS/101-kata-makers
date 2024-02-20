package kata.makers;

import org.springframework.stereotype.Service;

@Service
public class UserService {


  public void buySkiPass(User user) {

    user.setValidPass(true);
    user.setSkiPass(new SkiPass());
  }

  public void scanSkiPass(User user) {

    user.getSkiPass().setScanned(true);
  }
}
