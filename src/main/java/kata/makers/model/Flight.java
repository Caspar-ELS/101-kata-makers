package kata.makers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Flight {
  String from;
  String destination;

  @Getter
  Double price;
}
