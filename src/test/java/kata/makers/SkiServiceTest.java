package kata.makers;

import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;
import kata.makers.exception.NotEnoughCreditException;
import kata.makers.exception.SkiPassInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class SkiServiceTest {

  private static final String TEST_USER_NAME_ONE = "Erwin Smith";
  private static final String TEST_USER_NAME_TWO = "Levi Ackermann";
  @InjectMocks
  private SkiService userService;

  private User user;

  @BeforeEach
  public void setUp() {
    openMocks(this);
    user = new User(TEST_USER_NAME_ONE);
  }

  @Test
  void userCanGetPass() {

    userService.buySkiPass(user);
    Assertions.assertTrue(user.isPassBought());
  }

  @Test
  void userCanGetUniquePass() {

    User userTwo = new User(TEST_USER_NAME_TWO);

    userService.buySkiPass(user);
    userService.buySkiPass(userTwo);
    Assertions.assertNotEquals(user.getSkiPass().getId(), userTwo.getSkiPass().getId());
  }

  @Test
  void userCanScanSkiPassAndRideOnce() throws Exception {

    userService.buySkiPass(user);
    Assertions.assertEquals(5, user.getSkiPass().getRides());

    userService.scanSkiPass(user);

    Assertions.assertEquals(4, user.getSkiPass().getRides());
  }

  @Test
  void userCannotUseTheSamePassMoreThanFiveTimes() throws Exception {

    userService.buySkiPass(user);
    scanSkiPassNTimes(user, 5);
    Assertions.assertEquals(0, user.getSkiPass().getRides());

    Assertions.assertThrows(SkiPassInvalidException.class, () -> {
      userService.scanSkiPass(user);
    });

  }

  @Test
  void userCannotScanPassWithoutBuying() {
    Assertions.assertThrows(SkiPassInvalidException.class, () -> {
      userService.scanSkiPass(user);
    });

  }

  @Test
  void userCanUseCreditIfPassHasNoRidesLeft() throws SkiPassInvalidException {

    userService.buySkiPass(user);
    scanSkiPassNTimes(user, 5);
    Assertions.assertEquals(0, user.getSkiPass().getRides());
    user.getSkiPass().topUp(100);
    userService.scanSkiPass(user);
    Assertions.assertEquals(0, user.getSkiPass().getRides());
    Assertions.assertEquals(80, user.getSkiPass().getCredit());
  }

  @Test
  void userWillNotUseCreditIfThereAreRidesLeft() throws SkiPassInvalidException {

    userService.buySkiPass(user);
    scanSkiPassNTimes(user, 4);
    Assertions.assertEquals(1, user.getSkiPass().getRides());
    user.getSkiPass().topUp(100);
    userService.scanSkiPass(user);
    Assertions.assertEquals(0, user.getSkiPass().getRides());
    Assertions.assertEquals(100, user.getSkiPass().getCredit());
  }

  @Test
  void userCannotUseSkiPassIfThereAreNoRidesOrCredit() throws Exception {

    userService.buySkiPass(user);
    scanSkiPassNTimes(user, 5);
    Assertions.assertEquals(0, user.getSkiPass().getRides());
    Assertions.assertEquals(0, user.getSkiPass().getCredit());

    Assertions.assertThrows(SkiPassInvalidException.class, () -> {
      userService.scanSkiPass(user);
    });
  }

  @Test
  void userCanUseCreditEvenIfItGoesNegative() throws SkiPassInvalidException {

    userService.buySkiPass(user);
    scanSkiPassNTimes(user, 5);
    Assertions.assertEquals(0, user.getSkiPass().getRides());
    user.getSkiPass().topUp(15);
    userService.scanSkiPass(user);
    Assertions.assertEquals(0, user.getSkiPass().getRides());
    Assertions.assertEquals(-5, user.getSkiPass().getCredit());
  }

  @Test
  void userCanBuyItemWithSkiPass() throws NotEnoughCreditException {

    userService.buySkiPass(user);
    user.getSkiPass().topUp(100);
    userService.buyItem(user, Item.COFFEE);
    Assertions.assertEquals(List.of(Item.COFFEE), user.getItems());
    Assertions.assertEquals(90, user.getSkiPass().getCredit());
  }

  @Test
  void userCannotBuyItemWithSkiPassIfNotEnoughCredit() {

    userService.buySkiPass(user);
    user.getSkiPass().topUp(5);

    Assertions.assertThrows(NotEnoughCreditException.class, () -> {
      userService.buyItem(user, Item.COFFEE);
    });
    Assertions.assertEquals(List.of(), user.getItems());
    Assertions.assertEquals(5, user.getSkiPass().getCredit());
  }

  private void scanSkiPassNTimes(User user, int numberOfTimes) throws SkiPassInvalidException {
    for (int i = 0; i < numberOfTimes; i++) {
      userService.scanSkiPass(user);
    }
  }

}