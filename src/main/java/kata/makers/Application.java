package kata.makers;

import java.util.Objects;
import java.util.Scanner;
import kata.makers.service.DemoService;
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
  DemoService demoService;

  private String userChoice;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    int exitCode = SpringApplication.exit(context, () -> 0);
    System.exit(exitCode);
  }

  private String encryptOrDecrypt() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("""
            What's would you like to do? Enter the number:\s
            1. encrypt
            2. decrypt
            """);
    if (scanner.hasNext()) {
      userChoice = scanner.nextLine();
    }
    return userChoice;
  }

  @Override
  public void run(String... args) throws Exception {

    userChoice = encryptOrDecrypt();
    if (userChoice.equals("1")) {
      demoService.encrypt();
    } else if (userChoice.equals("2")) {
      demoService.decrypt();
    } else {
      log.info("Incorrect input.");
    }

  }
}
