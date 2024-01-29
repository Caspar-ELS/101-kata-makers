package kata.makers.service;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import kata.makers.model.JasyptSecret;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncryptionService {

  @Autowired
  AwsSecretManagerService awsSecretManagerService;
  private Map<Integer, String> algorithmMap = Map.of(1, "PBEWithMD5AndDES", 2,
      "PBEWITHHMACSHA512ANDAES_256");

  public String process() throws Exception {
    String plainText = "";
    Integer algorithmChoice = null;
    String environment = "";

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter plain text you would like to encrypt: ");
    if (scanner.hasNext()) {
      plainText = scanner.nextLine();
    }

    System.out.println(
        "Choose algorithm: Press 1 for \"PBEWithMD5AndDES\" and 2 for \"PBEWITHHMACSHA512ANDAES_256\"");
    if (scanner.hasNext()) {
      algorithmChoice = Integer.parseInt(scanner.nextLine());
    }

    String algorithm = getAlgorithmByChoice(algorithmChoice);
    if (Objects.isNull(algorithm)) {
      System.out.println("Invalid choice of algorithm, please try again");
      throw new Exception("Invalid choice of algorithm, please try again");
    }

    System.out.println("Enter your environment");
    if (scanner.hasNext()) {
      environment = scanner.nextLine();
    }

    JasyptSecret jasyptSecret = awsSecretManagerService.getSecrets(
        String.format("jasypt-%s", environment));

    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setPassword(jasyptSecret.getPassword());
    encryptor.setAlgorithm(algorithm);
    if ("PBEWITHHMACSHA512ANDAES_256".equals(algorithm)) {
      encryptor.setIvGenerator(new RandomIvGenerator());
    }

    return encryptor.encrypt(plainText);
  }

  private String getAlgorithmByChoice(int choice) {
    return algorithmMap.get(choice);
  }
}
