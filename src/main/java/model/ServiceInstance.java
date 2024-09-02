package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ServiceInstance {

  private String name;
  private boolean isRunning;

}
