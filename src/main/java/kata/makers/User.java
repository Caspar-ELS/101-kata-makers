package kata.makers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private boolean validPass;
  private String name;

  public User(String name) {
    this.name = name;
    this.validPass = false;
  }
}