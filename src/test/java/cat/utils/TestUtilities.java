package cat.utils;

import cat.model.Animal;

public class TestUtilities {

  public static Animal buildCat() {
    return Animal.builder()
        .numberOfPaws(4)
        .hasTail(true)
        .isTailLong(true)
        .isCarnivore(true)
        .hasWhiskers(true)
        .hasRetractableClaws(true)
        .purrs(true)
        .meows(true)
        .isDomesticated(true)
        .build();
  }

  public static Animal buildNotACat() {
    return Animal.builder()
        .numberOfPaws(2)
        .hasTail(true)
        .isTailLong(true)
        .isCarnivore(true)
        .hasWhiskers(true)
        .hasRetractableClaws(true)
        .purrs(true)
        .meows(false)
        .isDomesticated(true)
        .build();
  }
}
