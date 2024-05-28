package kata.makers;

import java.util.List;
import kata.makers.exception.RequiredFieldsEmptyException;
import org.springframework.beans.factory.annotation.Autowired;

public class ShowService {

  @Autowired
  private ShowsDatabase showsDatabase;

  public List<Show> listAll() {
    return showsDatabase.listAll();
  }

  public void addShow(Show show) throws RequiredFieldsEmptyException {
    showsDatabase.add(show);
  }

  public void filterBy(String condition, String value) {
    showsDatabase.filterBy(condition, value);
  }

}
