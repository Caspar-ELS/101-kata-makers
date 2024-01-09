package kata.makers;

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

  String input;
  String secondInput;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    int exitCode = SpringApplication.exit(context, () -> 0);
    System.exit(exitCode);
  }

  @Override
  public void run(String... args) {
    // sample code for getting input
    Scanner scanner = new Scanner(System.in);
    System.out.println("What your input?");
    if (scanner.hasNext()) {
      input = scanner.nextLine();
    }

    System.out.println("What your second input?");
    if (scanner.hasNext()) {
      secondInput = scanner.nextLine();
    }

    log.info("Input: {}", input);
    log.info("Second Input: {}", secondInput);

    // sample code for autowiring a service
    demoService.foo();
  }
}
