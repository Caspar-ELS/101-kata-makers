package kata.makers.service;

import kata.makers.enums.Role;
import kata.makers.model.User;

public class UserService {
  public User createCustomer(String name, Double balance) {
    return new User(Role.CUSTOMER, name, balance);
  }
}
