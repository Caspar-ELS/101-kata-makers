package kata.makers;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private boolean passBought;
  private SkiPass skiPass;
  private String name;
  private List<Item> items;

  public User(String name) {
    this.name = name;
    this.passBought = false;
    this.items = new ArrayList<>();
  }
}