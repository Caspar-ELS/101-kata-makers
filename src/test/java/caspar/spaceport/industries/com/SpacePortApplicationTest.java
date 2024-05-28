package caspar.spaceport.industries.com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import caspar.spaceport.industries.com.destinations.exception.DestinationAlreadyExistsException;
import caspar.spaceport.industries.com.destinations.exception.NoSuchDestinationExistsException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpacePortApplicationTest {

  SpacePortApplication application;

  @BeforeEach
  public void cleanUp() {
    this.application = new SpacePortApplication();
  }

  @Test
  public void canAddAndRetrieveDestinations() throws DestinationAlreadyExistsException {
    this.application.addDestination("Theta-13-zx.i");
    this.application.addDestination("Planet X");
    this.application.addDestination("Dimension 5");

    List<String> result = this.application.getDestinations();

    assertEquals(3, result.size());
    assertTrue(result.contains("Theta-13-zx.i"));
    assertTrue(result.contains("Planet X"));
    assertTrue(result.contains("Dimension 5"));
  }

  @Test
  public void noDuplicateDestinations() throws DestinationAlreadyExistsException {
    this.application.addDestination("Theta-13-zx.i");

    List<String> result = this.application.getDestinations();

    assertThrows(
        DestinationAlreadyExistsException.class,
        () -> this.application.addDestination("Theta-13-zx.i")
    );
    assertEquals(1, result.size());
    assertEquals("Theta-13-zx.i", result.get(0));
  }

  @Test
  public void removeDestinationById()
      throws DestinationAlreadyExistsException, NoSuchDestinationExistsException {
    String id = this.application.addDestination("Theta-13-zx.i");

    List<String> before = this.application.getDestinations();

    assertEquals(1, before.size());
    assertEquals("Theta-13-zx.i", before.get(0));

    this.application.removeDestinationById(id);

    List<String> after = this.application.getDestinations();

    assertEquals(0, after.size());
  }

  @Test
  public void shouldThrowIfDestinationCannotBeRemoved() {
    assertThrows(
        NoSuchDestinationExistsException.class,
        () -> this.application.removeDestinationById("random-id")
    );
  }

  @Test
  public void canAddWeeklyOperationsItemWithCostAndReturnTotal() {
    this.application.addWeeklyOperationsCost("Fuel", 500000);
    this.application.addWeeklyOperationsCost("Renting the base", 1500000);
    this.application.addWeeklyOperationsCost("Employee salaries", 2000000);
    this.application.addWeeklyOperationsCost("Maintaining the ships", 5000000);

    assertEquals(9000000, this.application.getWeeklyOperationsCost());
  }

  @Test
  public void canRemoveWeeklyOperationsById() throws NoSuchDestinationExistsException {
    String id = this.application.addWeeklyOperationsCost("Fuel", 500000);

    assertEquals(500000, this.application.getWeeklyOperationsCost());

    this.application.removeWeeklyOperationsCostById(id);

    assertEquals(0, this.application.getWeeklyOperationsCost());
  }

  @Test
  public void shouldThrowIfDestinationCostCannotBeRemoved() {
    assertThrows(
        NoSuchDestinationExistsException.class,
        () -> this.application.removeDestinationById("random-id")
    );
  }

}