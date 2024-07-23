package service;

import java.util.List;
import java.util.Objects;

public class FilterService {
  private static final String DEV = "dev";
  private static final List<String> BOM_SERVICES = List.of(
      "orrx",
      "orcx",
      "arfs",
      "fucx",
      "arfx",
      "pofc",
      "asfc",
      "fusp",
      "asft",
      "aofc",
      "rercv3",
      "rertv3",
      "rrsrv3",
      "rrspv3",
      "trdr",
      "oisu",
      "cats",
      "atss",
      "trsb",
      "trds",
      "trsr",
      "inas",
      "insr",
      "insp",
      "cnsp"
  );

  public boolean isBomServices(String serviceShortName){
    return Objects.nonNull(serviceShortName) && BOM_SERVICES.contains(serviceShortName);
  }

  public boolean isDev(String environment){
    return Objects.nonNull(environment) && matchEnvironment(environment, DEV);
  }

  private boolean matchEnvironment(String serviceEnvironment, String targetEnvironment){
    return serviceEnvironment.equals(targetEnvironment);
  }

}
