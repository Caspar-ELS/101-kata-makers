package kata.makers;

import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @BeforeEach
  public void setUp() {
    openMocks(this);
  }

  @Test
  void userCanGetPass() {
    User user = new User("Erwin Smith");

    userService.buySkiPass(user);
    Assertions.assertEquals(true, user.isValidPass());

  }

}