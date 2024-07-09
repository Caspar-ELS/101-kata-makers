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
    Assertions.assertEquals(Map.of("crime_2018_1", newShow), showsDatabase.listAll());
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
  void willReturnListOfShowsFromGivenYear()
      throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(showOne), showsDatabase.filterBy(Field.YEAR_RELEASED, "2018"));
  }

  @Test
  void willReturnEmptyListIfNoShowsFromGivenYear()
      throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(), showsDatabase.filterBy(Field.YEAR_RELEASED,"2016"));
  }

  @Test
  void willReturnListOfShowsWithGivenTitle()
      throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(showOne), showsDatabase.filterBy(Field.TITLE, "Crime"));
  }

  @Test
  void willReturnEmptyListIfNoShowsWithGivenTitle()
      throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(), showsDatabase.filterBy(Field.TITLE,"Crime 3"));
  }

  @Test
  void willReturnListOfShowsWithGivenStreamingPlatform()
      throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(showOne), showsDatabase.filterBy(Field.STREAMING_PLATFORM, "Netflix"));
  }

  @Test
  void willReturnEmptyListIfNoShowsWithGivenStreamingPlatform()
      throws RequiredFieldsEmptyException {
    Show showOne = buildTestShow();
    showsDatabase.add(showOne);
    Show showTwo = buildTestShowTwo();
    showsDatabase.add(showTwo);

    Assertions.assertEquals(List.of(), showsDatabase.filterBy(Field.STREAMING_PLATFORM,"Amazon Prime"));
  }

  @Test
  void willConstructKeyForTheShow() throws RequiredFieldsEmptyException {
    Show newShow = buildTestShow();
    showsDatabase.add(newShow);
    Assertions.assertEquals("crime_2018_1", showsDatabase.getShows().keySet().stream().findFirst().get());
  }

  @Test
  void willConstructKeyForTheShowIfKeyAlreadyPresent() throws RequiredFieldsEmptyException {
    Show newShow = buildTestShow();
    showsDatabase.add(newShow);
    Show newShowTwo = buildTestShow();
    showsDatabase.add(newShowTwo);
    Show newShowThree = buildTestShow();
    showsDatabase.add(newShowThree);
    Assertions.assertEquals(newShow, showsDatabase.getShows().get("crime_2018_1"));
    Assertions.assertEquals(newShowTwo, showsDatabase.getShows().get("crime_2018_2"));
    Assertions.assertEquals(newShowThree, showsDatabase.getShows().get("crime_2018_3"));
  }

  private static Show buildTestShow() {
    return Show.builder()
        .title("Crime")
        .yearReleased("2018")
        .streamingPlatform("Netflix")
        .build();
  }

  private static Show buildTestShowTwo() {
    return Show.builder()
        .title("Crime 2")
        .yearReleased("2024")
        .build();
  }

}