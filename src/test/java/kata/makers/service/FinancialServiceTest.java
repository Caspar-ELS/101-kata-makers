package kata.makers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import kata.makers.enums.Role;
import kata.makers.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinancialServiceTest {

  private User customer;
  private User merchant;
  private FinancialService financialService;

  @BeforeEach
  void beforeEach(){
    this.customer = new User(Role.CUSTOMER, "customer", 100.0);
    this.merchant = new User(Role.MERCHANT, "merchant", 50.0);
    financialService = new FinancialService();
  }

  @Test
  void canTransferMoney(){
    financialService.transferMoney(customer, merchant, 10.0);

    assertEquals(60.0, merchant.getBalance());
    assertEquals(90.0, customer.getBalance());
  }

}
