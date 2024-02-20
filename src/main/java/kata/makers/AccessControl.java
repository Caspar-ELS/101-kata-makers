package kata.makers;

public class AccessControl {

  public boolean canUserDoThing(UserIdentifiers userIdentifiers, UserActions userActions) {

    if (userIdentifiers.equals(UserIdentifiers.U)) {
      if (userActions.equals(UserActions.CHECK_OUT)
          || userActions.equals(UserActions.VIEW_PRODUCTS)) {
        return true;
      }
    }

    if (userIdentifiers.equals(UserIdentifiers.A)) {
      if (userActions.equals(UserActions.CHANGE_PASSWORDS)
          || userActions.equals(UserActions.ADD_PRODUCTS)
          || userActions.equals(UserActions.VIEW_PRODUCTS)) {
        return true;
      }
    }

    return false;
  }
}
