package my.cake.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerTest {

  @Test
  void willCreateACustomerWithCakeRequirements() {
    Customer customer = new Customer("white", "sponge", "large");

    assertEquals("white", customer.getColourPreference());
    assertEquals("sponge", customer.getTypePreference());
    assertEquals("large", customer.getSizePreference());
  }


}
