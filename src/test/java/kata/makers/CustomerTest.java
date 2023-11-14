package kata.makers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerTest {

  @Test
  public void shouldReturnFullName() {
    Customer customer = new Customer("Jason", "Kwong", "j.kwong@kwongmail.ja");

    Assertions.assertEquals("Jason, KWONG", customer.getFullName());
  }

  @Test
  public void emailMustHaveASingleAmpersand() {
    Customer customer = new Customer("Jason", "Kwong", "j@kwong@kwongmail.com");
    Assertions.assertTrue(customer.validateEmail());
  }

  @Test
  public void emailMustHaveADotComAtTheEnd() {
    Customer customer = new Customer("Jason", "Kwong", "j.kwong@kwongmail.ja");

    Assertions.assertFalse(customer.validateEmail());
  }

  @Test
  public void emailCannotHaveComma() {
    Customer customer = new Customer("Jason", "Kwong", "j,kwong@kwongmail.com");
    Assertions.assertFalse(customer.validateEmail());
  }

  @Test
  public void emailIsValid() {
    Customer customer = new Customer("Jason", "Kwong", "j.kwong@kwongmail.com");
    Assertions.assertTrue(customer.validateEmail());
  }
}