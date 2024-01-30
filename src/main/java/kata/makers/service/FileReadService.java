package kata.makers.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FileReadService {

  public void getContentFromFile(String path) throws IOException {

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        // Process the line, e.g., print it
        log.debug(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
