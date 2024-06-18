package kata.makers;

import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;
import java.util.Map;
import kata.makers.exception.RequiredFieldsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class ShowsDatabaseTest {

  @InjectMocks
  private ShowsDatabase showsDatabase;

  @BeforeEach
  public void setUp() {
    openMocks(this);
  }

  @Test
  void canAddShow() throws RequiredFieldsEmptyException {
    showsDatabase.add(buildTestShow());
    Assertions.assertEquals(1, showsDatabase.getShows().size());
  }

  @Test
  void willReturnEmptySetIfNoShowsAdded() {
    Assertions.assertEquals(Map.of(), showsDatabase.getShows());
  }

  @Test
  void willReturnListOfAddedShows() throws RequiredFieldsEmptyException {
    Show newShow = buildTestShow();
    showsDatabase.add(newShow);
    Assertions.assertEquals(Map.of("crime_2018", newShow), showsDatabase.listAll());
  }

  @Test
  void willThrowExceptionIfAddingShowWithoutTitle() {
    Assertions.assertThrows(RequiredFieldsEmptyException.class,
        () -> showsDatabase.add(Show.builder().yearReleased("2018").build()));
  }

  @Test
  void willThrowExceptionIfAddingShowWithoutYearOfRelease() {
    Assertions.assertThrows(RequiredFieldsEmptyException.class,
        () -> showsDatabase.add(Show.builder().title("Crime").build()));
  }

  @Test
  void willReturnListOfShowsFromGivenYear() throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(showOne), showsDatabase.filterBy(Field.YEAR_RELEASED, "2018"));
  }

  @Test
  void willReturnEmptyListIfNoShowsFromGivenYear() throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(), showsDatabase.filterBy(Field.YEAR_RELEASED,"2016"));
  }

  @Test
  void willReturnListOfShowsWithGivenTitle() throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(showOne), showsDatabase.filterBy(Field.TITLE, "Crime"));
  }

  @Test
  void willReturnEmptyListIfNoShowsWithGivenTitle() throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(), showsDatabase.filterBy(Field.TITLE,"Crime 3"));
  }

  private static Show buildTestShow() {
    return Show.builder()
        .title("Crime")
        .yearReleased("2018")
        .build();
  }

  private static Show buildTestShowTwo() {
    return Show.builder()
        .title("Crime 2")
        .yearReleased("2024")
        .build();
  }

}