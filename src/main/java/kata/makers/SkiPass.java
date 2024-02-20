package kata.makers;

import java.util.UUID;
import lombok.Getter;

@Getter
public class SkiPass {

  private String id;

  public SkiPass() {
    this.id = String.valueOf(UUID.randomUUID());
  }
}
