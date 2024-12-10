package kata.makers.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class XmlToJsonConverterTest {

  private static final String XML_INPUT = "<root><element1>value1</element1><element2>value2</element2><element3>value3</element3></root>";
  private static final String EXPECTED_JSON = "{\"root\": {\n" +
      "  \"element1\": \"value1\",\n" +
      "  \"element2\": \"value2\",\n" +
      "  \"element3\": \"value3\"\n" +
      "}}";

  @Test
  void willConvertXmlStringToJson() {
    XmlToJsonConverter xmlToJsonConverter = new XmlToJsonConverter();
    String actualJson = xmlToJsonConverter.convertXmlToJson(XML_INPUT);

    assertEquals(EXPECTED_JSON, actualJson);
  }

  @Test
  void willConvertXmlStringToJsonFile() throws IOException {
    XmlToJsonConverter xmlToJsonConverter = new XmlToJsonConverter();

    xmlToJsonConverter.convertXmlToJsonFile(XML_INPUT);

    Path pathToOutput = Paths.get("output.json");
    String fileContent = new String(Files.readAllBytes(pathToOutput));

    assertTrue(Files.exists(pathToOutput));
    assertEquals(EXPECTED_JSON, fileContent);
  }
}

