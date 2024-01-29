package kata.makers.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Collectors;
import kata.makers.model.JasyptSecret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AwsSecretManagerService {

  public JasyptSecret getSecrets(String name) {
    ObjectMapper mapper = new ObjectMapper();

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

    try {
      return mapper.readValue(secret, JasyptSecret.class);
    } catch (JsonProcessingException jsonProcessingException) {
      return null;
    }
  }

}
