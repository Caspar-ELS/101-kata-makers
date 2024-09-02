package service;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import enums.Component;
import enums.Test;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import model.ServiceInstance;

public class Filter {

  private static final List<String> bomServices = List.of("trds", "trsb", "trsr", "trdr", "inas", "cats",
      "atss", "insr", "insp", "cnsp", "orcx", "orrx", "oisu", "fucx", "arfs", "arfx", "aofc", "asft",
      "asfc", "pofc", "fusp", "eier", "trsr", "trdr", "trds", "trsb", "cats", "atss", "rrspv3",
      "rrsrv3", "rersv3", "reacv3", "rertv3", "bocs", "bosp", "tekp", "eier", "kevin", "kevex", "nesx");

  private static final EnumMap<Component, List<String>> servicesByComponent = new EnumMap<>(Component.class);

  private static EnumMap getEnumMap() {
    servicesByComponent.put(Component.BILLING, List.of("trds", "trsb", "trdr", "inas", "cats", "atss", "trsr", "insr", "insp", "cnsp"));
    servicesByComponent.put(Component.ORDER_MANAGEMENT, List.of("orcx", "orrx", "oisu"));
    servicesByComponent.put(Component.FULFILLMENT, List.of("fucx", "aofc", "arfx", "asft", "asfc", "pofc", "fusp"));
    servicesByComponent.put(Component.REVENUE_RECOGNITION, List.of("rrspv3", "rrsrv3", "rersv3", "reacv3", "rertv3", "arfs"));
    servicesByComponent.put(Component.CORE_BOM, List.of("bocs", "nesx"));
    servicesByComponent.put(Component.TEST_UTILITIES, List.of("eier", "tekp"));
    return servicesByComponent;
  }

  public static Map<Test, List<Component>> componentsForTests = Map.of(
      Test.ORDERS, List.of(Component.ORDER_MANAGEMENT),
      Test.INVOICES, List.of(Component.BILLING),
      Test.CREDIT_NOTES, List.of(Component.BILLING),
      Test.TRANSACTION_STATUSES, List.of(Component.BILLING),
      Test.ACCOUNTS_RECEIVABLES_REVENUE_RECOGNITION, List.of(Component.ORDER_MANAGEMENT, Component.FULFILLMENT, Component.TEST_UTILITIES),
      Test.GENERAL_LEDGER_REVENUE_RECOGNITION_V3, List.of(Component.ORDER_MANAGEMENT, Component.REVENUE_RECOGNITION)
  );

  public static ArrayList<ServiceInstance> checkForComponentServices(List<ServiceInstance> runningServicesList, Component component) {
    List componentsServices = (List) getEnumMap().get(component);
    ArrayList<ServiceInstance> inactiveServices = new ArrayList<>();
    componentsServices.forEach(service -> {
      if (runningServicesList.stream().filter(runningService -> runningService.getName().equals(service)).findAny().isEmpty()) {
        inactiveServices.add(ServiceInstance.builder().name((String) service).isRunning(false).build());
      }
    });
    return inactiveServices;
  }

  public static boolean belongsToEnumComponent(Component component, String instanceName) {
    List values = (List) getEnumMap().get(component);
    return values.contains(instanceName);
  }

//  public static String getComponentFor(String testName) {
//    List<Component> componentsList = componentsForTests.get(testName);
//    StringBuilder formattedComponentsList = new StringBuilder();
//    for (Component component : componentsList) {
//      formattedComponentsList.append(component.name()).append("\n");
//    }
//    return formattedComponentsList.toString();
//  }

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
