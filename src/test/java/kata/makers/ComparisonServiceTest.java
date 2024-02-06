package kata.makers;

import static kata.makers.constant.KeyName.ORIGINAL_KEY;
import static kata.makers.constant.KeyName.UPDATED_KEY;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kata.makers.service.ComparisonService;
import kata.makers.service.FileReadService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@Slf4j
public class ComparisonServiceTest {

  @Mock
  FileReadService fileReadService;

  @InjectMocks
  ComparisonService comparisonService;

  private static final String ORIGINAL_FILE_NAME = "original.txt";
  private static final String UPDATED_FILE_NAME = "updated.txt";

  private static final String ORIGINAL_LINE_1 = "I like Java a lot.";
  private static final String ORIGINAL_LINE_2 = "platform platform implement platform platform";
  private static final String ORIGINAL_LINE_3 = "This goes great with server.";
  private static final String ORIGINAL_LINE_4 = "Java is oops based.";

  private static final String UPDATED_LINE_1 = "I like Javascript very very much.";
  private static final String UPDATED_LINE_2 = "platform platform platform platform plank plank platform platform";
  private static final String UPDATED_LINE_3 = "Browser loves it.";
  private static final String UPDATED_LINE_4 = "Javascript is procedural based.";

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void compareTest() throws IOException {
    when(fileReadService.getContentFromFile(ORIGINAL_FILE_NAME)).thenReturn(List.of(
        ORIGINAL_LINE_1,
        ORIGINAL_LINE_2,
        ORIGINAL_LINE_3,
        ORIGINAL_LINE_4));

    when(fileReadService.getContentFromFile(UPDATED_FILE_NAME)).thenReturn(List.of(
        UPDATED_LINE_1,
        UPDATED_LINE_2,
        UPDATED_LINE_3,
        UPDATED_LINE_4));

    Assertions.assertEquals(
        Map.of(
            "1", Map.of(ORIGINAL_KEY, ORIGINAL_LINE_1, UPDATED_KEY, UPDATED_LINE_1),
            "2", Map.of(ORIGINAL_KEY, ORIGINAL_LINE_2, UPDATED_KEY, UPDATED_LINE_2),
            "3",
            Map.of(ORIGINAL_KEY, ORIGINAL_LINE_3, UPDATED_KEY, UPDATED_LINE_3),
            "4", Map.of(ORIGINAL_KEY, ORIGINAL_LINE_4, UPDATED_KEY, UPDATED_LINE_4)
        ),
        comparisonService.findDiffBetweenFiles(ORIGINAL_FILE_NAME, UPDATED_FILE_NAME)
    );
  }

  @Test
  void compareTestIfUpdatedIsLonger() throws IOException {
    when(fileReadService.getContentFromFile(ORIGINAL_FILE_NAME)).thenReturn(List.of(
        ORIGINAL_LINE_1, ORIGINAL_LINE_2
    ));

    when(fileReadService.getContentFromFile(UPDATED_FILE_NAME)).thenReturn(List.of(
        UPDATED_LINE_1,
        UPDATED_LINE_2,
        UPDATED_LINE_3,
        UPDATED_LINE_4));

    Map<String, String> line3 = new HashMap<>();
    line3.put(ORIGINAL_KEY, null);
    line3.put(UPDATED_KEY, UPDATED_LINE_3);

    Map<String, String> line4 = new HashMap<>();
    line4.put(ORIGINAL_KEY, null);
    line4.put(UPDATED_KEY, UPDATED_LINE_4);

    Assertions.assertEquals(
        Map.of(
            "1", Map.of(ORIGINAL_KEY, ORIGINAL_LINE_1, UPDATED_KEY, UPDATED_LINE_1),
            "2", Map.of(ORIGINAL_KEY, ORIGINAL_LINE_2, UPDATED_KEY, UPDATED_LINE_2),
            "3", line3,
            "4", line4
        ),
        comparisonService.findDiffBetweenFiles(ORIGINAL_FILE_NAME, UPDATED_FILE_NAME)
    );
  }

  @Test
  void compareTestIfOriginalIsLonger() throws IOException {
    when(fileReadService.getContentFromFile(ORIGINAL_FILE_NAME)).thenReturn(List.of(
        ORIGINAL_LINE_1,
        ORIGINAL_LINE_2,
        ORIGINAL_LINE_3,
        ORIGINAL_LINE_4));

    when(fileReadService.getContentFromFile(UPDATED_FILE_NAME)).thenReturn(List.of(UPDATED_LINE_1));

    Map<String, String> line2 = new HashMap<>();
    line2.put(ORIGINAL_KEY, ORIGINAL_LINE_2);
    line2.put(UPDATED_KEY, null);

    Map<String, String> line3 = new HashMap<>();
    line3.put(ORIGINAL_KEY, ORIGINAL_LINE_3);
    line3.put(UPDATED_KEY, null);

    Map<String, String> line4 = new HashMap<>();
    line4.put(ORIGINAL_KEY, ORIGINAL_LINE_4);
    line4.put(UPDATED_KEY, null);

    Map<String, Map<String, String>> map = Map.of(
        "1", Map.of(ORIGINAL_KEY, ORIGINAL_LINE_1, UPDATED_KEY, UPDATED_LINE_1),
        "2", line2,
        "3", line3,
        "4", line4
    );

    Assertions.assertEquals(
        map,
        comparisonService.findDiffBetweenFiles(ORIGINAL_FILE_NAME, UPDATED_FILE_NAME)
    );
  }

  @Test
  void onlyReturnParticularLine() throws IOException {
    when(fileReadService.getContentFromFile(ORIGINAL_FILE_NAME)).thenReturn(List.of(
        ORIGINAL_LINE_1,
        ORIGINAL_LINE_2,
        ORIGINAL_LINE_3,
        ORIGINAL_LINE_4));

    when(fileReadService.getContentFromFile(UPDATED_FILE_NAME)).thenReturn(List.of(
        ORIGINAL_LINE_1,
        UPDATED_LINE_2,
        ORIGINAL_LINE_3,
        UPDATED_LINE_4));

    Map<String, Map<String, String>> map = Map.of(
        "2", Map.of(ORIGINAL_KEY, ORIGINAL_LINE_2, UPDATED_KEY, UPDATED_LINE_2),
        "4", Map.of(ORIGINAL_KEY, ORIGINAL_LINE_4, UPDATED_KEY, UPDATED_LINE_4)

    );

    Assertions.assertEquals(
        map,
        comparisonService.findDiffBetweenFiles(ORIGINAL_FILE_NAME, UPDATED_FILE_NAME)
    );
  }

}
