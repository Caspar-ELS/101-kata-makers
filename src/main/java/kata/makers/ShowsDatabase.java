package kata.makers;

import java.util.ArrayList;
import java.util.List;
import kata.makers.exception.RequiredFieldsEmptyException;
import lombok.Getter;

@Getter
public class ShowsDatabase {

  private final List<Show> shows = new ArrayList<>();

  public void add(Show show) throws RequiredFieldsEmptyException {

    try {
      if (!show.getTitle().isBlank() && !show.getYearReleased().isBlank()) {
        shows.add(show);
      }
    } catch (NullPointerException nullPointerException) {
      throw new RequiredFieldsEmptyException("The title and the year of release are required.");
    }
  }

  public List<Show> listAll() {
    return shows;
  }

  public List<Show> filterBy(String condition, String value) {
    if (condition.equals("title")) {
      return shows.stream().filter(show -> show.getTitle().equals(value)).toList();
    } else if (condition.equals("yearReleased")) {
      return shows.stream().filter(show -> show.getYearReleased().equals(value)).toList();
    }

    return List.of();
  }

}
