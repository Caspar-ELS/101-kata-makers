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

  public String getFullName() {
      return firstName + ", " + lastName.toUpperCase();
  }

  public boolean validateEmail() {
    return true;
  }
}
