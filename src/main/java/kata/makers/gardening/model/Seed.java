package kata.makers.gardening.model;

import lombok.Getter;

@Getter
public enum Seed {
  TOMATO(1, 3, 5),
  MARIGOLD(2, 3, 6),
  ROSE(3, 4, 10),
  MINT(4, 3, 5),
  CORIANDER(5, 3, 6),
  BASIL(6, 3, 5),
  POTATO(7, 4, 10);

  private final int seedNumber;
  private final int waterRequirement;
  private final int weedRemovalTime;

  Seed(int seedNumber, int waterRequirement, int weedRemovalTime) {
    this.seedNumber = seedNumber;
    this.waterRequirement = waterRequirement;
    this.weedRemovalTime = weedRemovalTime;
  }

}
