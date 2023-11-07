package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NovSevenTest {

  @Test
  public void shouldGreet() {
    Customer customer = new Customer("John", "Doe", "helloWorld@elsevier.com");
    assertEquals(customer.getFirstName(), "John");
  }
}