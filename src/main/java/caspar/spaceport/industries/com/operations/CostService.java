package caspar.spaceport.industries.com.operations;

import caspar.spaceport.industries.com.destinations.exception.NoSuchDestinationExistsException;
import caspar.spaceport.industries.com.operations.model.Cost;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CostService {

  Map<String, Cost> weeklyCostsById = new HashMap<>();

  public String addWeeklyCost(String operation, long cost) {
    String randomId = getRandomId();

    weeklyCostsById.put(
        randomId,
        new Cost(
            operation,
            randomId,
            cost)
    );

    return randomId;
  }

  public Long getWeeklyCosts() {
    return this.weeklyCostsById
        .values()
        .stream()
        .mapToLong(Cost::getCost)
        .sum();
  }

  public void removeWeeklyCost(String id) throws NoSuchDestinationExistsException {
    if (this.weeklyCostsById.remove(id) == null) {
      throw new NoSuchDestinationExistsException();
    }
    ;
  }

  private String getRandomId() {
    return UUID.randomUUID().toString();
  }
}
