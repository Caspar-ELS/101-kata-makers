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
public class DecryptionService {

  @Autowired
  AwsSecretManagerService awsSecretManagerService;

  private Map<Integer, String> algorithmMap = Map.of(1, "PBEWithMD5AndDES", 2,
      "PBEWITHHMACSHA512ANDAES_256");

  public void process() throws Exception {
    String cipherText = "";
    Integer algorithmChoice = null;
    String environment = "";

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter plain text you would like to decrypt: ");
    if (scanner.hasNext()) {
      cipherText = scanner.nextLine();
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

    System.out.println(String.format("Decrypted text is: %s", encryptor.decrypt(cipherText)));
  }

  private String getAlgorithmByChoice(int choice) {
    return algorithmMap.get(choice);
  }
}
