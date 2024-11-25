package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ApplicationTest {

  @Test
  void testMain() {
    // Arrange
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Act
    Application.main(new String[]{});

    // Assert
    String expectedOutput = "Tomek, Aleksa, John";
    assertEquals(expectedOutput, outContent.toString().trim());
  }


}