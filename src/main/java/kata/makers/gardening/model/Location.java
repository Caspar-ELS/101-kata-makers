package kata.makers.gardening.model;

import lombok.Getter;

@Getter
public enum Location {
  BALCONY(3);

  private final int numberOfPlants;

  private int seedsPlanted = 0;

  Location(int numberOfPlants) {
    this.numberOfPlants = numberOfPlants;
  }
  public boolean isLocationFull() {
    return seedsPlanted >= numberOfPlants;
  }

  public void incrementSeedsPlanted() {
    this.seedsPlanted = this.seedsPlanted+1;
  }
}
