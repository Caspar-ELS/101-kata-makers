package kata.makers;

import java.util.Objects;
import java.util.Scanner;
import kata.makers.service.DecryptionService;
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
  @Autowired
  DecryptionService decryptionService;

  private static final String ENCRYPT = "ENCRYPT";
  private static final String DECRYPT = "DECRYPT";
  int actionChoice;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    int exitCode = SpringApplication.exit(context, () -> 0);
    System.exit(exitCode);
  }

  @Override
  public void run(String... args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Please choose your action: Enter 1 for \"Encrypt\"; 2 for \"Decrypt\"");
    if (scanner.hasNext()) {
      actionChoice = Integer.parseInt(scanner.nextLine());
    }

    String action = getActionByChoice(actionChoice);
    if (Objects.isNull(action)) {
      System.out.println("Invalid option of action, please try again");
    } else {
      switch (action) {
        case ENCRYPT -> {
          try {
            System.out.printf("Encrypted text is: %s", encryptionService.process());
          } catch (Exception exception) {
            System.out.println(String.format("Error when encrypting: %s", exception.getMessage()));
          }
        }
        case DECRYPT -> {
          try {
            decryptionService.process();
          } catch (Exception exception) {
            System.out.println(String.format("Error when decrypting: %s", exception.getMessage()));
          }
        }
      }
    }

  }

  private String getActionByChoice(int actionChoice) {
    switch (actionChoice) {
      case 1 -> {
        return ENCRYPT;
      }
      case 2 -> {
        return DECRYPT;
      }
      default -> {
        return null;
      }
    }
  }

}
