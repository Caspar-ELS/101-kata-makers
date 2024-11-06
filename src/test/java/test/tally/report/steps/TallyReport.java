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


  @When("Retrieve {string} for {string} from {string} between {string} and {string}")
  public void retrieveTestData(String splitBy, String query, String serviceName, String fromTime,
      String toTime) {
    List<String> kibanaResponses = this.kibanaClient.getTestData(query, serviceName, fromTime,
        toTime, splitBy);
    extractInvoiceIds(kibanaResponses);

  }

  @Then("Verify test data for {string} from {string} between {string} and {string}")
  public void VerifyData(String query, String serviceName, String fromTime, String toTime) {

  }

    @And("Create Test Tally Report")
    public void createTestTallyReport() {

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


