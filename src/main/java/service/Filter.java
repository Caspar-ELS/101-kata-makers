package service;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import java.util.List;

public class Filter {

  private static final List<String> bomServices = List.of("trds", "trsb", "trsr", "trdr", "inas", "cats",
      "atss", "insr", "insp", "cnsp", "orcx", "orrx", "oisu", "fucx", "arfs", "arfx", "aofc", "asft",
      "asfc", "pofc", "fusp", "eier", "trsr", "trdr", "trds", "trsb", "cats", "atss", "rrspv3",
      "rrsrv3", "rersv3", "reacv3", "rertv3", "bocs", "bosp", "tekp", "eier", "kevin", "kevex", "nesx");

  public static String getEnvironment(Instance instance) {
    String environment = "";
    for (Tag tag : instance.getTags()) {
      if (tag.getKey().equals("Environment")) {
        environment = tag.getValue();
      }
    }
    return environment;
  }

  public static String getServiceAcronym(Instance instance) {
    String instanceName = "";
    for (Tag tag : instance.getTags()) {
      if (tag.getKey().equals("Role")) {
        instanceName = tag.getValue();
      }
    }
    return instanceName;
  }

  public static  boolean isBomService(String instanceName) {
    return bomServices.contains(instanceName);
  }

  public static boolean isDev(String environment) {
    return environment.equals("dev");
  }

}
