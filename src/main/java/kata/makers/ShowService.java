package kata.makers;

import java.util.Map;
import kata.makers.exception.RequiredFieldsEmptyException;
import org.springframework.beans.factory.annotation.Autowired;

public class ShowService {

  @Autowired
  private ShowsDatabase showsDatabase;

  public Map<String, Show> listAll() {
    return showsDatabase.listAll();
  }

  public void addShow(Show show) throws RequiredFieldsEmptyException {
    showsDatabase.add(show);
  }

  public void filterBy(Field condition, String value) {
    showsDatabase.filterBy(condition, value);
  }

}
