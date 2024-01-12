package kata.makers;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import kata.makers.service.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

  @Autowired
  EncryptionService encryptionService;

  String plainText;
  int algorithmChoice;
  String jasyptPassword;

  private Map<Integer, String> algorithmMap = Map.of(1, "PBEWithMD5AndDES", 2,
      "PBEWITHHMACSHA512ANDAES_256");

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    int exitCode = SpringApplication.exit(context, () -> 0);
    System.exit(exitCode);
  }

  @Override
  public void run(String... args) {
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
      return;
    }

    System.out.println("Enter your jasypt password");
    if (scanner.hasNext()) {
      jasyptPassword = scanner.nextLine();
    }

    String cipherText = encryptionService.encryptWithParameter(plainText, algorithm,
        jasyptPassword);
    System.out.println("Encrypted password is: " + cipherText);

  }

  private String getAlgorithmByChoice(int choice) {
    return algorithmMap.get(choice);
  }
}
