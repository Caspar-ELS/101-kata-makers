package kata.makers.gardening.service;

import java.util.Map;
import kata.makers.gardening.model.Location;
import kata.makers.gardening.model.Seed;
import kata.makers.gardening.model.Soil;
import lombok.Getter;

@Getter
public class PlantingService {

  public static final Long WATERING_INTERVAL = 5L;
  private final Location location;
  private final Soil soil;
  private final Seed seed;
  private long dateSeedPlanted;

  private static final Map<Seed, Soil> seedMap = Map.of(
      Seed.MARIGOLD, Soil.DRY_SOIL,
      Seed.ROSE, Soil.DRY_SOIL,
      Seed.MINT, Soil.WET_SOIL,
      Seed.CORIANDER, Soil.WET_SOIL,
      Seed.BASIL, Soil.WET_SOIL,
      Seed.POTATO, Soil.DRY_SOIL,
      Seed.TOMATO, Soil.WET_SOIL);
  public PlantingService(Location location, Soil soil, Seed seed) {
    this.location = location;
    this.soil = soil;
    this.seed = seed;
  }

  public void plantSeed() {
    if (location.isLocationFull()) {
      throw new IllegalStateException("Location is full");
    }
    if (seedMap.get(this.seed) != this.soil) {
      throw new IllegalStateException("Wrong soil");
    }

    dateSeedPlanted = System.currentTimeMillis();
    location.incrementSeedsPlanted();
  }

  public void reminderForWatering() {
    if (System.currentTimeMillis() - dateSeedPlanted > seed.getWaterRequirement() ) {
      throw new IllegalStateException("Water me!");
    }
  }

  public void reminderForWeedRemoval() {
    if (System.currentTimeMillis() - dateSeedPlanted > seed.getWeedRemovalTime()) {
      throw new IllegalStateException("Remove weed!");
    }
  }
}
