package kata.makers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import kata.makers.exception.RequiredFieldsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ShowServiceTest {

  @Captor
  private ArgumentCaptor<Show> argumentCaptor;

  @Mock
  private ShowsDatabase showsDatabase;

  @InjectMocks
  private ShowService showService;

  @BeforeEach
  public void setUp() {
    openMocks(this);
  }

  @Test
  void userCanAddShow() throws RequiredFieldsEmptyException {
    Show show = Show.builder().genre("crime").build();
    showService.addShow(show);
    verify(showsDatabase, times(1)).add(argumentCaptor.capture());
    Assertions.assertEquals(argumentCaptor.getValue().getGenre(), show.getGenre());
  }

  @Test
  void userCanListAllAvailableShows() {
    showService.listAll();
    verify(showsDatabase, times(1)).listAll();
  }

  @Test
  void userCanFilterByYear() {
    showService.filterBy("yearReleased", "2024");
    verify(showsDatabase, times(1)).filterBy("yearReleased","2024");
  }

  @Test
  void userCanFilterByTitle() {
    showService.filterBy("title", "2024");
    verify(showsDatabase, times(1)).filterBy("title","2024");
  }

}