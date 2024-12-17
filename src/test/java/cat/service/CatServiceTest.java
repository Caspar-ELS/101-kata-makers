package cat.service;

import static cat.utils.TestUtilities.buildCat;
import static cat.utils.TestUtilities.buildNotACat;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class CatServiceTest {

  @InjectMocks
  private CatService catService;

  @BeforeEach
  public void beforeEach() {
    openMocks(this);
  }

  @Test
  void shouldReturnTrueIfCat() {
    Assertions.assertTrue(catService.isCat(buildCat()));
  }

  @Test
  void shouldReturnFalseIfNotACat() {
    Assertions.assertFalse(catService.isCat(buildNotACat()));
  }


}