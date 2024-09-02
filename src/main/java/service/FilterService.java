package service;

import static constants.ComponentConstant.BOM_COMPONENTS;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import model.ServiceStatus;

public class FilterService {
  private static final String DEV = "dev";


  private static final List<String> BOM_SERVICES = BOM_COMPONENTS.values().stream()
      .flatMap(List::stream)
      .collect(Collectors.toList());

  public boolean isBomServices(String serviceShortName){
    return Objects.nonNull(serviceShortName) && BOM_SERVICES.contains(serviceShortName);
  }

  public boolean isDev(String environment){
    return Objects.nonNull(environment) && matchEnvironment(environment, DEV);
  }

  private boolean matchEnvironment(String serviceEnvironment, String targetEnvironment){
    return serviceEnvironment.equals(targetEnvironment);
  }

  public boolean isAllServicesRunningIn(String component, Map<String, List<ServiceStatus>> serviceRunningMap){

    return serviceRunningMap.get(component).stream().allMatch(ServiceStatus::isRunning);
  }

  public List<String> getComponentWithNonRunningServices(
      Map<String, List<ServiceStatus>> componentServicesStatusMap, List<String> components) {
    return components.stream().filter(component -> !isAllServicesRunningIn(component,
        componentServicesStatusMap)).toList();
  }

}
