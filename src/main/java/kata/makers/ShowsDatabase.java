package kata.makers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kata.makers.exception.RequiredFieldsEmptyException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ShowsDatabase {

  private final Map<String, Show> shows = new HashMap<>();

  public void add(Show show) throws RequiredFieldsEmptyException {

    try {
      if (!show.getTitle().isBlank() && !show.getYearReleased().isBlank() && !show.getYearReleased().isBlank()) {
        shows.put(getKey(show), show);
        log.info("Added to the database.");
      } else {
        log.info("The title, year of release and director name are required fields.");
      }
    } catch (NullPointerException nullPointerException) {
      throw new RequiredFieldsEmptyException("The title and the year of release are required.");
    }
  }

  public Map<String, Show> listAll() {
    return shows;
  }

  public List<Show> filterBy(Field condition, String value) {
    if (condition.equals(Field.TITLE)) {
      return shows.values().stream().filter(show -> show.getTitle() != null && show.getTitle().equals(value)).toList();
    } else if (condition.equals(Field.YEAR_RELEASED)) {
      return shows.values().stream().filter(show -> show.getYearReleased() != null && show.getYearReleased().equals(value)).toList();
    } else if (condition.equals(Field.DIRECTOR)) {
      return shows.values().stream().filter(show -> show.getDirector() != null && show.getDirector().equals(value)).toList();
    } else if (condition.equals(Field.GENRE)) {
      return shows.values().stream().filter(show -> show.getGenre() != null && show.getGenre().equals(value)).toList();
    } else if (condition.equals(Field.LANGUAGE)) {
      return shows.values().stream().filter(show -> show.getLanguage() != null && show.getLanguage().equals(value)).toList();
    } else if (condition.equals(Field.STREAMING_PLATFORM)) {
      return shows.values().stream().filter(show -> show.getStreamingPlatform() != null && show.getStreamingPlatform().equals(value)).toList();
    }

    return List.of();
  }

  private String getKey(Show show) {
    String[] splitTitle = show.getTitle().toLowerCase().split(" ");
    String titleAndYear = String.join("_", splitTitle) + "_" + show.getYearReleased();
    String finalKey = titleAndYear + "_1";
    List<String> matchingKeys = shows.keySet().stream().filter(key -> key.contains(titleAndYear)).toList();
    if (!matchingKeys.isEmpty()) {
      finalKey = updateKeyWithNumber(matchingKeys);
    }
    return finalKey;
  }

  private static String updateKeyWithNumber(List<String> matchingKeys) {
    String finalKey;
    String[] splitKey = matchingKeys.get(0).split("_");
    splitKey[splitKey.length-1] = String.valueOf(
        Integer.parseInt(splitKey[splitKey.length-1]) + 1);
    finalKey = String.join("_", splitKey);
    return finalKey;
  }
}
