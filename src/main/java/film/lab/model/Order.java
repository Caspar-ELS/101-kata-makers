package film.lab.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

  private CustomerDetails customerDetails;
  private Film film;
  private Prints prints;

}
