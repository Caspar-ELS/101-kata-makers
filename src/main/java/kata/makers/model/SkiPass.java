package kata.makers.model;

import lombok.Getter;

public class SkiPass {

  @Getter
  private Long id;

  public SkiPass(long nextId) {
    this.id = nextId;
  }
}
