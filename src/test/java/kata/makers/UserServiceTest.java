package kata.makers;

import static org.mockito.MockitoAnnotations.openMocks;

import kata.makers.exception.SkiPassInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class UserServiceTest {

  private static final String TEST_USER_NAME_ONE = "Erwin Smith";
  private static final String TEST_USER_NAME_TWO = "Levi Ackermann";
  @InjectMocks
  private UserService userService;

  @BeforeEach
  public void setUp() {
    openMocks(this);
  }

  @Test
  void userCanGetPass() {
    User user = new User(TEST_USER_NAME_ONE);

    userService.buySkiPass(user);
    Assertions.assertTrue(user.isValidPass());
  }

  @Test
  void userCanGetUniquePass() {
    User user = new User(TEST_USER_NAME_ONE);
    User userTwo = new User(TEST_USER_NAME_TWO);

    userService.buySkiPass(user);
    userService.buySkiPass(userTwo);
    Assertions.assertNotEquals(user.getSkiPass().getId(), userTwo.getSkiPass().getId());
  }

  @Test
  void userCanScanSkiPassAndRideOnce() throws Exception {
    User user = new User(TEST_USER_NAME_ONE);

    userService.buySkiPass(user);
    Assertions.assertEquals(5, user.getSkiPass().getRides());

    userService.scanSkiPass(user);

    Assertions.assertEquals(4, user.getSkiPass().getRides());
  }

  @Test
  void userCannotUseTheSamePassMoreThanFiveTimes() throws Exception {
    User user = new User(TEST_USER_NAME_ONE);

    userService.buySkiPass(user);
    userService.scanSkiPass(user);
    userService.scanSkiPass(user);
    userService.scanSkiPass(user);
    userService.scanSkiPass(user);
    userService.scanSkiPass(user);
    Assertions.assertEquals(0, user.getSkiPass().getRides());

    Assertions.assertThrows(SkiPassInvalidException.class, () -> {
      userService.scanSkiPass(user);
    });

  }

}