package kata.makers;

import org.springframework.stereotype.Service;

@Service
public class UserService {


  public void buySkiPass(User user) {

    user.setValidPass(true);
  }
}
