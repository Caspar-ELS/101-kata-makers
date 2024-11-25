package kata.makers;

import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {

  public static void main(String[] args) {
    JSONObject payload = new JSONObject();
    payload.put("sourceSystem", "SF_MQ2COM");
    payload.put("orderNumber", "test_bom_invoice_8k_line");
    payload.put("sourceSystemId", "BOM_SOURCE_SYSTEM_ID");
    payload.put("invoiceCurrency", "USD");

    JSONObject sellingEntity = new JSONObject();
    sellingEntity.put("name", "Elsevier BV");
    sellingEntity.put("reference", "ELS_BV");
    payload.put("sellingEntity", sellingEntity);

    JSONObject billingDetails = new JSONObject();
    billingDetails.put("paymentModel", "POST_PAY");
    billingDetails.put("invoicingModel", "One Off");
    JSONObject details = new JSONObject();
    details.put("paymentTerm", "30");
    billingDetails.put("details", details);
    payload.put("billingDetails", billingDetails);

    JSONObject account = new JSONObject();
    account.put("ecrId", "ECR-1");
    account.put("sourceSystem", "SF_MQ2COM");
    account.put("sourceSystemId", "BOM_SOURCE_SYSTEM_ID");
    account.put("name", "3M");

    JSONObject address = new JSONObject();
    address.put("sourceSystem", "SF_MQ2COM");
    address.put("sourceSystemId", "BOM_SOURCE_SYSTEM_ID");
    address.put("addressLine1", "addressLine1");
    address.put("city", "city");
    address.put("countryISO", "GB");

    JSONObject purchasingAccount = new JSONObject();
    purchasingAccount.put("account", account);
    purchasingAccount.put("address", address);
    payload.put("purchasingAccount", purchasingAccount);

    JSONObject billingAccount = new JSONObject();
    billingAccount.put("account", account);
    JSONObject contact = new JSONObject();
    contact.put("ecrId", "ECR-123");
    contact.put("sourceSystem", "SF_MQ2COM");
    contact.put("sourceSystemId", "BOM_SOURCE_SYSTEM_ID");
    contact.put("name", "testname");
    contact.put("email", "testemail@test.com");
    billingAccount.put("contact", contact);
    billingAccount.put("address", address);
    payload.put("billingAccount", billingAccount);

    JSONObject deliveryAccount = new JSONObject();
    deliveryAccount.put("account", account);
    JSONObject deliveryAddress = new JSONObject(address.toString());
    deliveryAddress.put("city", "city2");
    deliveryAccount.put("address", deliveryAddress);
    payload.put("deliveryAccount", deliveryAccount);

    JSONArray invoiceLines = new JSONArray();
    for (int i = 1; i <= 8000; i++) {
      JSONObject invoiceLine = new JSONObject();
      invoiceLine.put("lineNumber", i);
      invoiceLine.put("unitPrice", 2160.0);
      invoiceLine.put("quantity", 1.0);
      invoiceLine.put("netAmount", 2160.0);
      invoiceLine.put("sellingModel", "One Off");

      JSONObject product = new JSONObject();
      product.put("reference", "BOM_SOURCE_SYSTEM_ID_" + i);
      product.put("code", "EPR-100062");
      product.put("taxCode", "S011");
      product.put("title", "Applied Radiation and Isotopes - Article Publishing Charge");
      invoiceLine.put("product", product);

      JSONObject shipFromAddress = new JSONObject();
      shipFromAddress.put("addressLine1", "Radarweg 29");
      shipFromAddress.put("city", "Amsterdam");
      shipFromAddress.put("postcode", "1043 NX");
      shipFromAddress.put("country", "Netherlands");
      shipFromAddress.put("countryISO", "NL");
      invoiceLine.put("shipFromAddress", shipFromAddress);

      invoiceLines.put(invoiceLine);
    }
    payload.put("invoiceLines", invoiceLines);

    try (FileWriter file = new FileWriter("src/main/resources/payload.json")) {
      file.write(payload.toString(2));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


