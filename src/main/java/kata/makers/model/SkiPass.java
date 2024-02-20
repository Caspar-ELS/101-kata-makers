package kata.makers.model;

import lombok.Getter;

@Getter
public class SkiPass {

  private final Long id;

  public SkiPass(long nextId) {
    this.id = nextId;
  }
}
