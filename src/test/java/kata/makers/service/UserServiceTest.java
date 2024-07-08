package kata.makers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import kata.makers.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

  UserService userService;

  @BeforeEach
  void beforeEach() {
    userService = new UserService();
  }

  @Test
  void shouldReturnNewlyCreatedCustomer() {
    String customerName = "customer";
    Double initialBalance = 1000.0;

    User newlyCreatedUser = userService.createCustomer(customerName, initialBalance);

    assertEquals(customerName, newlyCreatedUser.getName());
    assertEquals(initialBalance, newlyCreatedUser.getBalance());
  }

}
