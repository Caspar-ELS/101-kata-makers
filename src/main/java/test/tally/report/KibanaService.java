package test.tally.report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

@Slf4j
public class KibanaService {

  private static final String KIBANA_EXCEPTION = "Encountered a Kibana exception. Please retry.";
  public static final String SIT_URL = "https://vpc-mercury-sit-es-tl6p6wgk7ockzivdqw4xktzya4.eu-west-1.es.amazonaws.com/_search";
  public static final String DEV_URL =  "https://vpc-mercury-dev-es-c5pk3ls3hfp3uvf4zvhvd5dshu.eu-west-1.es.amazonaws.com/_search";
  public static final String UAT_URL = "https://vpc-mercury-uat-es-yoap2adwzwrjyc2asempavzxju.eu-west-1.es.amazonaws.com/_search";
  public static final String PROD_URL = "https://vpc-qtc-prod-logdata-es-fgu6wyuax6nbluv4ma3smlp5rq.eu-west-1.es.amazonaws.com/_search";

  private static final String KIBANA_URL = getKibanaUrl();
  public static final String UNABLE_TO_GET_KIBANA_OUTPUT = "Unable to get Kibana output:";
  public static final String QUERY = "query";

  public static String getKibanaUrl() {
    return switch ("PROD") {
      case "SIT" -> SIT_URL;
      case "DEV" -> DEV_URL;
      case "UAT" -> UAT_URL;
      case "PROD" -> PROD_URL;
      default -> UAT_URL;
    };
  }

  public List<String> getTestData(String query, String service, String fromTime, String toTime, String splitBy) {
    try (CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(getHttpPost(query, service, fromTime, toTime))) {
      return getMessages(readResponce(response), splitBy);
    } catch (IOException ioException) {
      log.error(UNABLE_TO_GET_KIBANA_OUTPUT + " {}", ioException.getMessage());
    }
    return Collections.emptyList();
  }

  private HttpPost getHttpPost(String query, String service, String fromTime, String toTime) throws IOException {
    HttpPost httpPost = new HttpPost(KIBANA_URL);
    String kibanaQuery = buildKibanaQuery(query, service, fromTime, toTime);
    httpPost.setEntity(new StringEntity(kibanaQuery));
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-Type", "application/json");
    httpPost.setHeader("gajellip","God1sgreat@1");
    return httpPost;
  }

  private String readResponce(CloseableHttpResponse response) throws IOException {
    InputStream inputStream = response.getEntity().getContent();
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        .lines().collect(Collectors.joining("\n"));
  }

  private String buildKibanaQuery(String queryString, String service, String fromTime, String toTime) {
    String defaultQuery = readFile("/kibana_query.json");
    JSONObject defaultQueryJson = new JSONObject(defaultQuery);
    JSONObject booleanQuery = defaultQueryJson.getJSONObject(QUERY).getJSONObject("bool");

    booleanQuery.getJSONArray("must").getJSONObject(0).getJSONObject("query_string").put(QUERY, queryString);
    booleanQuery.getJSONArray("filter").getJSONObject(0).getJSONObject("match_phrase").getJSONObject("fields.service").put(
        QUERY, service);
    JSONObject timestampRange = booleanQuery.getJSONArray("filter").getJSONObject(1).getJSONObject("range").getJSONObject("@timestamp");
    timestampRange.put("gte", fromTime);
    timestampRange.put("lte", toTime);

    return defaultQueryJson.toString();
  }


  private List<String> getMessages(String from, String splitBy) {
    try {
      JSONArray hits = new JSONObject(from).getJSONObject("hits").getJSONArray("hits");
      List<String> messages = new ArrayList<>();
      hits.forEach(hit -> messages.add(((JSONObject) hit).getJSONObject("_source").getString("message")));
//      log.info("Messages from Kibana: {}", messages);
      return splitBy != null ? messages.stream().map(msg -> msg.split(splitBy)[1]).toList() : messages;
    } catch (Exception e) {
      log.error("Received an unexpected response from Kibana: {}", from);
      return Collections.singletonList(KIBANA_EXCEPTION);
    }
  }

  public static String readFile(String fileName) {
    try {
      return IOUtils.toString(
          Objects.requireNonNull(KibanaService.class.getResourceAsStream(fileName)),
          StandardCharsets.UTF_8);
    } catch (IOException ioException) {
      log.error(UNABLE_TO_GET_KIBANA_OUTPUT + " {}", ioException.getMessage());
      return null;
    }
  }
}