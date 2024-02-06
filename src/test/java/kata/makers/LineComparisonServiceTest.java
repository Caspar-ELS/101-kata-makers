package kata.makers;

import static org.mockito.MockitoAnnotations.openMocks;

import kata.makers.service.LineComparisonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class LineComparisonServiceTest {

  @InjectMocks
  LineComparisonService lineComparisonService;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void testt() {
    Assertions.assertEquals(
        "1 -> I like [Java a lot.] | I like {Javascript very very much.}",
        lineComparisonService.findDiffForTwoLine("1", "I like Java a lot.", "I like Javascript very very much.")
    );

    Assertions.assertEquals(
        "1 -> platform platform [implement] platform [platform] | platform platform {platform} platform {plank plank platform platform}",
        lineComparisonService.findDiffForTwoLine("1","platform platform implement platform platform",
            "platform platform platform platform plank plank platform platform")
    );

    Assertions.assertEquals(
        "1 -> [This goes great with server.] | {Browser loves it.}",
        lineComparisonService.findDiffForTwoLine("1","This goes great with server.", "Browser loves it.")
    );

    Assertions.assertEquals(
        "1 -> [Java] is [oops] based. | {Javascript} is {procedural} based.",
        lineComparisonService.findDiffForTwoLine("1","Java is oops based.", "Javascript is procedural based.")
    );

    Assertions.assertEquals(
        "1 -> [Z] | {A}",
        lineComparisonService.findDiffForTwoLine("1","Z", "A")
    );
  }

  @Test
  void checkOriginalIsEmpty() {
    Assertions.assertEquals(
        "1 -> Apple Orange Banana | <EMPTY_LINE>",
        lineComparisonService.findDiffForTwoLine("1","Apple Orange Banana", null));
  }

  @Test
  void checkUpdateIsEmpty() {
    Assertions.assertEquals(
        "1 -> <EMPTY_LINE> | Apple Orange Banana",
        lineComparisonService.findDiffForTwoLine("1",null, "Apple Orange Banana"));
  }

}
