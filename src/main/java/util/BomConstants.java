package util;

import java.util.List;
import java.util.Map;

public class BomConstants {

  public static final String BILLING = "billing";
  public static final String ORDER_MANAGEMENT = "order_management";
  public static final String FULFILLMENT = "fulfillment";
  public static final String REVENUE_RECOGNITION = "revenue_recognition";
  public static final String CORE_BOM = "core_bom";
  public static final String TEST_UTILITIES = "test_utilities";

  public static final List<String> bomServices = List.of("trds", "trsb", "trdr", "inas", "cats", "atss",
      "trsr", "insr", "insp", "cnsp", "tltt", "orcx", "orrx", "oisu", "fucx", "aofc", "arfx",
      "asft", "asfc", "pofc", "fusp", "rrspv3", "rrsrv3", "rersv3", "reacv3", "rertv3", "arfs",
      "bocs", "nesx", "eier", "tekp");

  public static final Map<String, List<String>> regressionComponents = Map.of(
      "Orders", List.of(ORDER_MANAGEMENT),
      "Invoices", List.of(BILLING),
      "CreditNotes", List.of(BILLING),
      "TransactionStatuses", List.of(BILLING),
      "AccountsReceivablesRevenueRecognition", List.of(ORDER_MANAGEMENT, FULFILLMENT,
          TEST_UTILITIES),
      "GeneralLedgerRevenueRecognitionV3", List.of(ORDER_MANAGEMENT, REVENUE_RECOGNITION)
  );

  public static final Map<String, List<String>> bomComponents = Map.of(
      BILLING, List.of("trds", "trsb", "trdr", "inas", "cats", "atss", "trsr", "insr",
          "insp", "cnsp", "tltt"),
      ORDER_MANAGEMENT, List.of("orcx", "orrx", "oisu"),
      FULFILLMENT, List.of("fucx", "aofc", "arfx", "asft", "asfc", "pofc", "fusp"),
      REVENUE_RECOGNITION, List.of("rrspv3", "rrsrv3", "rersv3", "reacv3", "rertv3", "arfs"),
      CORE_BOM, List.of("bocs", "nesx"),
      TEST_UTILITIES, List.of("eier", "tekp")
  );

}
