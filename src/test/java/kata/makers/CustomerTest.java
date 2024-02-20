package kata.makers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerTest {

  @Test
  public void shouldReturnFullName() {
    Customer customer = new Customer("Jason", "Kwong", "j.kwong@kwongmail.ja", UserIdentifiers.U);
    Assertions.assertEquals("Jason, KWONG", customer.getFullName());
  }

  @Test
  public void emailMustHaveASingleAmpersand() {
    Customer customer = new Customer("Jason", "Kwong", "j@kwong@kwongmail.com", UserIdentifiers.U);
    Assertions.assertFalse(customer.validateEmail());
  }

  @Test
  public void emailMustHaveADotComAtTheEnd() {
    Customer customer = new Customer("Jason", "Kwong", "j.kwong@kwongmail.ja", UserIdentifiers.U);

    Assertions.assertFalse(customer.validateEmail());
  }

  @Test
  public void emailCannotHaveComma() {
    Customer customer = new Customer("Jason", "Kwong", "j,kwong@kwongmail.com", UserIdentifiers.U);
    Assertions.assertFalse(customer.validateEmail());
  }

  @Test
  public void emailIsValid() {
    Customer customer = new Customer("Jason", "Kwong", "j.kwong@kwongmail.com", UserIdentifiers.U);
    Assertions.assertTrue(customer.validateEmail());
  }
}