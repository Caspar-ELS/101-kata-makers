package kata.makers;

import kata.makers.service.DemoService;
import kata.makers.service.FileReadService;
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
  private DemoService demoService;

  @Autowired
  private FileReadService fileReadService;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    int exitCode = SpringApplication.exit(context, () -> 0);
    System.exit(exitCode);
  }

  @Override
  public void run(String... args) {

    fileReadService.compareContent("src/main/resources/firstFile", "src/main/resources/secondFile");

  }
}
