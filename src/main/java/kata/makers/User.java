package kata.makers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private boolean passBought;
  private SkiPass skiPass;
  private String name;

  public User(String name) {
    this.name = name;
    this.passBought = false;
  }
}