package model;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ServiceStatus {
  private String name;
  private boolean isRunning;
}
