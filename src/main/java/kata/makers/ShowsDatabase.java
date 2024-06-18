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
      if (!show.getTitle().isBlank() && !show.getYearReleased().isBlank()) {
        shows.put(getKey(show), show);
        log.info("Added to the database.");
      } else {
        log.info("The title and the year of release are required.");
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
      return shows.values().stream().filter(show -> show.getTitle().equals(value)).toList();
    } else if (condition.equals(Field.YEAR_RELEASED)) {
      return shows.values().stream().filter(show -> show.getYearReleased().equals(value)).toList();
    }

    return List.of();
  }

  private String getKey(Show show) {
    return show.getTitle().toLowerCase() + "_" + show.getYearReleased();
  }
}
