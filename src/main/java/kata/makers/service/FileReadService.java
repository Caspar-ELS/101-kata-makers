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

    List<String> lines = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }

  public void compareContent(String firstPath, String secondPath) {

    List<String> firstFileLines = getContentFromFile(firstPath);
    List<String> secondFileLines = getContentFromFile(secondPath);
    List<String> longerFile;
    List<String> shorterFile;

    if (firstFileLines.size() >= secondFileLines.size()) {
      longerFile = firstFileLines;
      shorterFile = secondFileLines;
    } else {
      longerFile = secondFileLines;
      shorterFile = firstFileLines;
    }

    compareLinesUpToTheEndOfShorterFile(firstFileLines, longerFile, shorterFile);
    compareAdditionalLines(firstFileLines, longerFile, shorterFile);
  }

  private static void compareLinesUpToTheEndOfShorterFile(List<String> firstFileLines, List<String> longerFile,
      List<String> shorterFile) {
    for (int i = 0; i < shorterFile.size(); i++) {
      if (!longerFile.get(i).equals(shorterFile.get(i))) {
        if (longerFile == firstFileLines) {
          System.out.printf("Found a difference in line #%s: %s | %s %n", i + 1,
              longerFile.get(i),
              shorterFile.get(i));
        } else {
          System.out.printf("Found a difference in line #%s: %s | %s %n", i + 1,
              shorterFile.get(i),
              longerFile.get(i));
        }
      }
    }
  }

  private static void compareAdditionalLines(List<String> firstFileLines, List<String> longerFile,
      List<String> shorterFile) {
    for (int i = shorterFile.size(); i < longerFile.size(); i++) {
      if (longerFile == firstFileLines) {
        System.out.printf("Found a difference in line #%s: %s | EMPTY %n", i + 1,
            longerFile.get(i));
      } else {
        System.out.printf("Found a difference in line #%s: EMPTY | %s %n", i + 1,
            longerFile.get(i));
      }
    }
  }
}
