package kata.makers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private UserIdentifiers UserIdentifier;

  private static final String AT_CHARACTER = "@";

  public String getFullName() {
    return firstName + ", " + lastName.toUpperCase();
  }

  public boolean validateEmail() {
    if (!containsDotComAtTheEnd()) {
      return false;
    }
    if (!containsSingleAtCharacter()) {
      return false;
    }
    if (containsComma()) {
      return false;
    }
    return true;
  }

  private boolean containsDotComAtTheEnd() {
    return emailAddress.substring(emailAddress.length() - 4).equals(".com");
  }

  private boolean containsSingleAtCharacter() {
    return emailAddress.split(AT_CHARACTER).length == 2;
  }

  private boolean containsComma() {
    return emailAddress.contains(",");
  }
}
