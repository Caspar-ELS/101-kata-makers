package kata.makers.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FileReadService {

  public List<String> getContentFromFile(String path) {
    List<String> list = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        // Process the line, e.g., print it
        list.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return list;
  }

}
