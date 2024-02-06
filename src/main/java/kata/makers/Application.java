package kata.makers;

import java.io.IOException;
import java.util.Scanner;
import kata.makers.service.ComparisonService;
import kata.makers.service.LineComparisonService;
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
  LineComparisonService lineComparisonService;

  @Autowired
  ComparisonService comparisonService;

  String input;
  String secondInput;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    int exitCode = SpringApplication.exit(context, () -> 0);
    System.exit(exitCode);
  }

  @Override
  public void run(String... args) throws IOException {
    // sample code for getting input

    comparisonService.findDifferentBetweenFiles("<PLEASE_CHANGE_IT_FILE_PATH_1>", "<PLEASE_CHANGE_IT_FILE_PATH_2>");
  }
}
