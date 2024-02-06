package kata.makers;

import static org.mockito.MockitoAnnotations.openMocks;

import java.io.IOException;
import kata.makers.service.FileReadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


public class FileReaderServiceTest {
  @InjectMocks
  FileReadService fileReadService;

  @BeforeEach
  void setUp() {openMocks(this);}

  @Test
  void tryRun() throws IOException {
    fileReadService.getContentFromFile("/Users/kwongj/Downloads/manual-upload-test");
  }
}
