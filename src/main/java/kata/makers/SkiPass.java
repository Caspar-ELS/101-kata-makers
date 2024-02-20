package kata.makers;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkiPass {

  private String id;
  private boolean scanned;

  public SkiPass() {
    this.id = String.valueOf(UUID.randomUUID());
    this.scanned = false;
  }
}
