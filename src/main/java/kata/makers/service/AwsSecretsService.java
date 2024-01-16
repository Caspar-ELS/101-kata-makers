package kata.makers.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AwsSecretsService {

  public Map<String, String> getSecrets(String name) {
    AWSSecretsManager awsSecretsManager =
        AWSSecretsManagerClientBuilder
            .standard()
            .withRegion(Regions.EU_WEST_1)
            .build();

    GetSecretValueRequest valueRequest = new GetSecretValueRequest();
    valueRequest.setSecretId(name);

    String secret = awsSecretsManager
        .getSecretValue(valueRequest)
        .getSecretString();

    JSONObject secretsResponseJson = new JSONObject(secret);

    return secretsResponseJson.toMap().entrySet().stream()
        .map(entry -> new SimpleEntry<>(entry.getKey(), entry.getValue().toString()))
        .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
  }
}
