package caspar.spaceport.industries.com.destinations;

import caspar.spaceport.industries.com.destinations.exception.DestinationAlreadyExistsException;
import caspar.spaceport.industries.com.destinations.exception.NoSuchDestinationExistsException;
import caspar.spaceport.industries.com.destinations.model.Destination;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DestinationService {

  private final Map<String, Destination> destinationsById = new HashMap<>();

  public String addDestination(String name) throws DestinationAlreadyExistsException {

    if (this.getDestinationNames().contains(name)) {
      throw new DestinationAlreadyExistsException();
    }

    String randomId = this.getRandomId();

    Destination destination = new Destination(name, randomId);

    this.destinationsById.put(randomId, destination);

    return randomId;
  }

  public List<String> getDestinations() {
    return this.getDestinationNames();
  }

  public void removeDestination(String id) throws NoSuchDestinationExistsException {
    if (this.destinationsById.remove(id) == null) {
      throw new NoSuchDestinationExistsException();
    }
    ;
  }

  private List<String> getDestinationNames() {
    return this.destinationsById
        .values()
        .stream()
        .map(Destination::getName)
        .toList();
  }

  private String getRandomId() {
    return UUID.randomUUID().toString();
  }
}
