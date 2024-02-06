package kata.makers.service;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LineComparisonService {

  private final String SPACE = " ";
  private final String SEPARATOR = " | ";
  private final String ARROW = " -> ";

  public String findDiffForTwoLine(String lineNumber, String original, String updated) {
    String[] splittedOriginal = getSplittedStringOrEmptyArrayFrom(original.trim());
    String[] splittedUpdate = getSplittedStringOrEmptyArrayFrom(updated.trim());

    if (splittedOriginal.length < 1 || splittedUpdate.length < 1) {
      return getResultBy(lineNumber, splittedOriginal, splittedUpdate);
    }

    int diffStartFrom = 0;
    for (int originalWordIndex = 0; originalWordIndex < splittedOriginal.length;
        originalWordIndex++) {
      try {
        String correspondingUpdate = splittedUpdate[originalWordIndex];
        if (splittedOriginal[originalWordIndex].equals(correspondingUpdate)) {

          if (originalWordIndex > diffStartFrom) {
            // start of diff
            splittedOriginal[diffStartFrom] = "[" + splittedOriginal[diffStartFrom];
            splittedUpdate[diffStartFrom] = "{" + splittedUpdate[diffStartFrom];
            // end of diff
            splittedOriginal[originalWordIndex - 1] = splittedOriginal[originalWordIndex - 1] + "]";
            splittedUpdate[originalWordIndex - 1] = splittedUpdate[originalWordIndex - 1] + "}";
          }

          diffStartFrom = originalWordIndex + 1; // assume the next is different
        }
      } catch (
          IndexOutOfBoundsException indexOutOfBoundsException) { // only appear when original is longer
        // from the diffStartFrom all the way to the end of updated version is different
        // stop comparison immediately as no point to do

        break;
      }
    }

    if (diffStartFrom < splittedOriginal.length) {
      // start of diff
      splittedOriginal[diffStartFrom] = "[" + splittedOriginal[diffStartFrom];
      splittedUpdate[diffStartFrom] = "{" + splittedUpdate[diffStartFrom];

      // end of diff
      splittedOriginal[splittedOriginal.length - 1] =
          splittedOriginal[splittedOriginal.length - 1] + "]";
      splittedUpdate[splittedUpdate.length - 1] = splittedUpdate[splittedUpdate.length - 1] + "}";
    }

    return getResultBy(lineNumber, splittedOriginal, splittedUpdate);
  }

  private String getResultBy(String lineNumber, String[] splittedOriginal, String[] splittedUpdate) {
    String original = getJoinedStringOrEmptyStringFlag(splittedOriginal);
    String updated = getJoinedStringOrEmptyStringFlag(splittedUpdate);

    return lineNumber + ARROW + original + SEPARATOR + updated;
  }

  private String[] getSplittedStringOrEmptyArrayFrom(String sentence) {
    return StringUtils.isEmpty(sentence) ? new String[]{} : sentence.split(SPACE);
  }

  private String getJoinedStringOrEmptyStringFlag(String[] splittedString) {
    return splittedString.length > 0 ? String.join(SPACE, splittedString) : "<EMPTY_LINE>";
  }
}
