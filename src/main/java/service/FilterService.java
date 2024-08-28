package service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FilterService {

  private static final String DEV = "dev";

  Map<String, List<String>> BOM_COMPONENTS = Map.ofEntries(
      Map.entry("billing",
          Arrays.asList("trds", "trsb", "trdr", "inas", "cats", "atss", "trsr", "insr", "insp",
              "cnsp")),
      Map.entry("order_management", Arrays.asList("orcx", "orrx", "oisu")),
      Map.entry("fulfillment",
          Arrays.asList("fucx", "aofc", "arfx", "asft", "asfc", "pofc", "fusp")),
      Map.entry("revenue_recognition",
          Arrays.asList("rrspv3", "rrsrv3", "rersv3", "reacv3", "rertv3", "arfs")),
      Map.entry("core_bom", Arrays.asList("bocs", "nesx")),
      Map.entry("test_utilities", Arrays.asList("eier", "tekp"))
  );

  Map<String, List<String>> BOM_FEATURE = Map.ofEntries(
      Map.entry("All",
          Arrays.asList("core_bom", "order_management", "revenue_recognition", "test_utilities",
              "fulfillment", "billing")),
      Map.entry("Orders", Arrays.asList("core_bom", "order_management", "billing")),
      Map.entry("Invoices", Arrays.asList("core_bom", "billing")),
      Map.entry("CreditNotes", Arrays.asList("core_bom", "billing")),
      Map.entry("TransactionStatuses", Arrays.asList("core_bom", "billing")),
      Map.entry("AccountsReceivablesRevenueRecognition",
          Arrays.asList("core_bom", "order_management", "revenue_recognition", "test_utilities",
              "fulfillment", "billing")),
      Map.entry("GeneralLedgerRevenueRecognitionV3",
          Arrays.asList("core_bom", "order_management", "revenue_recognition", "fulfillment"))
  );


  public boolean isBomServices(String serviceShortName) {
    return Objects.nonNull(serviceShortName) && BOM_COMPONENTS.values().stream().anyMatch(value -> value.contains(serviceShortName));
  }

  public boolean isDev(String environment) {
    return Objects.nonNull(environment) && matchEnvironment(environment, DEV);
  }

  private boolean matchEnvironment(String serviceEnvironment, String targetEnvironment) {
    return serviceEnvironment.equals(targetEnvironment);
  }
}