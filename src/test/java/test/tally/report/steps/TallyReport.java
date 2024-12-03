package test.tally.report.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import test.tally.report.KibanaService;

@CucumberContextConfiguration
@Slf4j
public class TallyReport {

  KibanaService kibanaClient = new KibanaService();

  private final List<String> invoiceIds = new ArrayList<>();
  private final List<String> SubmittedInvoiceIds = new ArrayList<>();
  private final List<String> FailedInvoiceIds = new ArrayList<>();


  @When("Retrieve {string} for {string} from {string} between {string} and {string}")
  public void retrieveTestData(String splitBy, String query, String serviceName, String fromTime,
      String toTime) {
    List<String> kibanaResponses = this.kibanaClient.getTestData(query, serviceName, fromTime,
        toTime, splitBy);
    extractInvoiceIds(kibanaResponses);

  }

  @Then("Verify test data for {string} from {string} between {string} and {string}")
  public void VerifyData(String query, String serviceName, String fromTime, String toTime) {
    for (String invoiceId : invoiceIds) {
      String queryWithInvoiceId = query.replace("<REPLACE_WITH_INVOICE_ID>", invoiceId);
      log.info("Query = {}", queryWithInvoiceId);
      List<String> kibanaResponse = this.kibanaClient.getTestData(queryWithInvoiceId, serviceName, fromTime, toTime, "Payload: ");
      log.info("kiabanaResponse = {}", kibanaResponse);
      log.info("kiabanaResponse boo = {}", kibanaResponse.isEmpty());
      if (kibanaResponse.isEmpty()) {
        this.FailedInvoiceIds.add(invoiceId);
      } else {
        this.SubmittedInvoiceIds.add(invoiceId);
      }
    }

  }

    @And("Create Test Tally Report")
    public void createTestTallyReport() {
      log.info("=============Creating Test Tally Report===========");
      log.info("invoiceIds={}", this.invoiceIds);
      log.info("Total Invoices = {}", this.invoiceIds.size());
      log.info("SubmittedInvoiceIds = \n{}", this.SubmittedInvoiceIds);
      log.info("Total Submitted Invoices = {}", this.SubmittedInvoiceIds.size());
      log.info("FailedInvoiceIds = \n{}", this.FailedInvoiceIds);
      log.info("Total Failed Invoices = {}", this.FailedInvoiceIds.size());
      log.info("=============End of Test Tally Report===========");

    }

    private void extractInvoiceIds (List < String > kibanaResponses) {
      log.info("We are in extract now");
      for (String kibanaResponse : kibanaResponses) {
        JSONObject kibanaResponseJson = new JSONObject(kibanaResponse);
        String invoiceId = kibanaResponseJson.getJSONObject("payload").getJSONObject("invoice")
            .get("id")
            .toString();
        this.invoiceIds.add(invoiceId);
      }
    }
}


