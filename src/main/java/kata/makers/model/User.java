package kata.makers.model;

import kata.makers.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
  Role role;
  String name;
  Double balance;
}
