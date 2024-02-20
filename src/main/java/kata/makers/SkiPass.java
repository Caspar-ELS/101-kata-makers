package kata.makers;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkiPass {

  private String id;
  private int rides;

  public SkiPass() {
    this.id = String.valueOf(UUID.randomUUID());
    this.rides = 5;
  }
}
