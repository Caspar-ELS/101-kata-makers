package kata.makers.service;

import static kata.makers.constant.KeyName.ORIGINAL_KEY;
import static kata.makers.constant.KeyName.UPDATED_KEY;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ComparisonService {

  @Autowired
  FileReadService fileReadService;

  @Autowired
  LineComparisonService lineComparisonService;

  public void findDifferentBetweenFiles(String path1, String path2) throws IOException {
    Map<String, Map<String, String>> diffMap = findDiffBetweenFiles(path1, path2);

    for (String lineNumber : diffMap.keySet().stream()
        .sorted(Comparator.comparingInt(Integer::parseInt)).toList()) {
      String originalLine = diffMap.get(lineNumber).get(ORIGINAL_KEY);
      String updatedLine = diffMap.get(lineNumber).get(UPDATED_KEY);

      // sout for printing the result
      System.out.println(
          lineComparisonService.findDiffForTwoLine(lineNumber, originalLine, updatedLine));
    }
  }

  public Map<String, Map<String, String>> findDiffBetweenFiles(String originalPath,
      String updatePath) {
    Map<String, Map<String, String>> result = new HashMap<>();
    /*
    * Example:
    * {
    *   "lineNumber_1": {
    *     "original": "xxxxxx",
    *     "updated": "yyyyyy",
    *   },
    *   "lineNumber_2": {
    *     .....
    *   },
    *   ...
    * }
    * */

    List<String> linesFromOriginal = fileReadService.getContentFromFile(originalPath);
    List<String> linesFromUpdated = fileReadService.getContentFromFile(updatePath);

    for (int indexOfOriginal = 0; indexOfOriginal < linesFromOriginal.size(); indexOfOriginal++) {
      try {

        String originalLine = linesFromOriginal.get(indexOfOriginal).trim();
        String updatedLine = linesFromUpdated.get(indexOfOriginal).trim();

        if (!originalLine.equals(updatedLine)) {
//          System.out.println(String.format("%s || %s", linesFromOriginal.get(indexOfOriginal),
//              linesFromUpdated.get(indexOfOriginal)));

          result.put(getLineNumber(indexOfOriginal), getComparisonMapFrom(originalLine, updatedLine
          ));
        }
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
//        System.out.println(
//            String.format("%s || <empty lines>", linesFromOriginal.get(indexOfOriginal)));

        result.put(getLineNumber(indexOfOriginal),
            getComparisonMapFrom(linesFromOriginal.get(indexOfOriginal).trim(), null));
      }
    }

    // handle updated size is larger
    if (linesFromOriginal.size() < linesFromUpdated.size()) {
      for (int index = linesFromOriginal.size(); index < linesFromUpdated.size(); index++) {
//        System.out.println(
//            String.format("<empty lines> || %s", linesFromUpdated.get(index)));

        result.put(getLineNumber(index),
            getComparisonMapFrom(null, linesFromUpdated.get(index).trim()));
      }
    }
    return result;
  }

  private Map<String, String> getComparisonMapFrom(String original, String updated) {
    Map<String, String> map = new HashMap<>();
    map.put(ORIGINAL_KEY, original);
    map.put(UPDATED_KEY, updated);

    return map;
  }

  private String getLineNumber(int lineIndexNumber) {
    return String.valueOf(lineIndexNumber + 1);
  }
}
