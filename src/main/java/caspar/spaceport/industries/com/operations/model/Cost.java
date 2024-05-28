package caspar.spaceport.industries.com.operations.model;

public class Cost {

  private final String name;

  private final String id;

  private final Long cost;

  public Cost(String name, String id, Long cost) {
    this.name = name;
    this.id = id;
    this.cost = cost;
  }

  public Long getCost() {
    return cost;
  }
}
