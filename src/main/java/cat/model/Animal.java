package cat.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Animal {

  private int numberOfPaws;
  private boolean hasTail;
  private boolean isTailLong;
  private boolean isCarnivore;
  private boolean hasWhiskers;
  private boolean hasRetractableClaws;
  private boolean purrs;
  private boolean meows;
  private boolean isDomesticated;

}
