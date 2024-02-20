package kata.makers;

import static kata.makers.UserActions.*;
import static kata.makers.UserIdentifiers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccessControlTest {

  @Test
  public void testAdminControls() {
    AccessControl accessControl = new AccessControl();

    Assertions.assertTrue(accessControl.canUserDoThing(A, CHANGE_PASSWORDS));
    Assertions.assertTrue(accessControl.canUserDoThing(A, ADD_PRODUCTS));
    Assertions.assertTrue(accessControl.canUserDoThing(A, VIEW_PRODUCTS));
    Assertions.assertFalse(accessControl.canUserDoThing(A, CHECK_OUT));
    Assertions.assertFalse(accessControl.canUserDoThing(A, CHECK_FUTURE_PRICE));
  }

  @Test
  public void testUserControls() {
    AccessControl accessControl = new AccessControl();

    Assertions.assertFalse(accessControl.canUserDoThing(U, CHANGE_PASSWORDS));
    Assertions.assertFalse(accessControl.canUserDoThing(U, ADD_PRODUCTS));
    Assertions.assertTrue(accessControl.canUserDoThing(U, VIEW_PRODUCTS));
    Assertions.assertTrue(accessControl.canUserDoThing(U, CHECK_OUT));
    Assertions.assertFalse(accessControl.canUserDoThing(U, CHECK_FUTURE_PRICE));
  }
}