package kata.makers.unit.gardening.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;
import kata.makers.gardening.model.Location;
import kata.makers.gardening.model.Seed;
import kata.makers.gardening.model.Soil;
import kata.makers.gardening.service.PlantingService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlantingServiceTest {

  private PlantingService plantingService;

  @BeforeEach
  void setUp() {
    plantingService = new PlantingService(Location.BALCONY, Soil.WET_SOIL, Seed.TOMATO);
  }

  @Test
  void shouldPlantSeed() {
    plantingService.plantSeed();
    assertEquals(1, plantingService.getLocation().getSeedsPlanted());
  }

  @Test
  void shouldThrowExceptionWhenLocationIsFull() {
    plantingService.plantSeed();
    plantingService.plantSeed();
    plantingService.plantSeed();
    assertThrows(IllegalStateException.class, () -> plantingService.plantSeed());
  }

  @Test
  void shouldThrowExceptionWhenSoilIsDryForTomato() {
    plantingService = new PlantingService(Location.BALCONY, Soil.DRY_SOIL, Seed.TOMATO);
    assertThrows(IllegalStateException.class, () -> plantingService.plantSeed());
  }

  @Test
  void shouldThrowExceptionWhenNeedsWatering() {
    plantingService.plantSeed();
    Awaitility.await()
        .atMost(5, TimeUnit.SECONDS)
        .untilAsserted(() ->
            assertThrows(IllegalStateException.class,
                () -> plantingService.reminderForWatering()));
  }

  @Test
  void shouldNotThrowExceptionWhenDoesNotNeedWatering() {
    plantingService.plantSeed();
    assertDoesNotThrow(() -> plantingService.reminderForWatering());
  }

  @Test
  void shouldThrowExceptionWhenNeedsWeedRemoval() {
    plantingService.plantSeed();
    Awaitility.await()
        .atMost(5, TimeUnit.SECONDS)
        .untilAsserted(() ->
            assertThrows(IllegalStateException.class,
                () -> plantingService.reminderForWeedRemoval()));
  }

  @Test
  void shouldNotThrowExceptionWhenDoesNotNeedWeedRemoval() {
    plantingService.plantSeed();
    assertDoesNotThrow(() -> plantingService.reminderForWeedRemoval());
  }
}