package caspar.spaceport.industries.com.destinations.model;

public class Destination {

  private final String name;

  private final String id;

  public Destination(String name, String id) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }
}
