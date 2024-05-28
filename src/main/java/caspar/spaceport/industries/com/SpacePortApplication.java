package caspar.spaceport.industries.com;

import caspar.spaceport.industries.com.destinations.DestinationService;
import caspar.spaceport.industries.com.destinations.exception.DestinationAlreadyExistsException;
import caspar.spaceport.industries.com.destinations.exception.NoSuchDestinationExistsException;
import caspar.spaceport.industries.com.operations.CostService;
import java.util.List;

public class SpacePortApplication {

  DestinationService destinationService = new DestinationService();
  CostService costService = new CostService();

  public String addDestination(String name) throws DestinationAlreadyExistsException {
    return this.destinationService.addDestination(name);
  }

  public List<String> getDestinations() {
    return this.destinationService.getDestinations();
  }

  public void removeDestinationById(String id) throws NoSuchDestinationExistsException {
    this.destinationService.removeDestination(id);
  }

  public String addWeeklyOperationsCost(String operation, long cost) {
    return this.costService.addWeeklyCost(operation, cost);
  }

  public Long getWeeklyOperationsCost() {
    return this.costService.getWeeklyCosts();
  }

  public void removeWeeklyOperationsCostById(String id) throws NoSuchDestinationExistsException {
    this.costService.removeWeeklyCost(id);
  }
}
