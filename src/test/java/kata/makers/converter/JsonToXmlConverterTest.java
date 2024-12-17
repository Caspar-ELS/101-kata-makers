package kata.makers.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class JsonToXmlConverterTest {

  private static final String JSON_INPUT = "{\"root\": {" +
      "  \"element1\": \"value1\"," +
      "  \"element2\": \"value2\"," +
      "  \"element3\": \"value3\"" +
      "}}";

  private static final String EXPECTED_XML =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>\n" +
          "  <element1>value1</element1>\n" +
          "  <element2>value2</element2>\n" +
          "  <element3>value3</element3>\n" +
          "</root>\n";

  private static final String JSON_INPUT_TWO = "{\"root\": {" +
      "\"id\": \"0001\"," +
      "\"type\": \"donut\"," +
      "\"name\": \"Cake\"," +
      "\"ppu\": 0.55," +
      "\"batters\": {" +
      "  \"batter\": [" +
      "    { \"id\": \"1001\", \"type\": \"Regular\" }," +
      "    { \"id\": \"1002\", \"type\": \"Chocolate\" }," +
      "    { \"id\": \"1003\", \"type\": \"Blueberry\" }," +
      "    { \"id\": \"1004\", \"type\": \"Devil's Food\" }" +
      "  ]" +
      "}," +
      "\"topping\": [" +
      "  { \"id\": \"5001\", \"type\": \"None\" }," +
      "  { \"id\": \"5002\", \"type\": \"Glazed\" }," +
      "  { \"id\": \"5005\", \"type\": \"Sugar\" }," +
      "  { \"id\": \"5007\", \"type\": \"Powdered Sugar\" }," +
      "  { \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" }," +
      "  { \"id\": \"5003\", \"type\": \"Chocolate\" }," +
      "  { \"id\": \"5004\", \"type\": \"Maple\" }" +
      "]" +
      "}}";

  private static final String EXPECTED_XML_TWO =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>\n" +
          "  <ppu>0.55</ppu>\n" +
          "  <batters>\n" +
          "    <batter>\n" +
          "      <id>1001</id>\n" +
          "      <type>Regular</type>\n" +
          "    </batter>\n" +
          "    <batter>\n" +
          "      <id>1002</id>\n" +
          "      <type>Chocolate</type>\n" +
          "    </batter>\n" +
          "    <batter>\n" +
          "      <id>1003</id>\n" +
          "      <type>Blueberry</type>\n" +
          "    </batter>\n" +
          "    <batter>\n" +
          "      <id>1004</id>\n" +
          "      <type>Devil's Food</type>\n" +
          "    </batter>\n" +
          "  </batters>\n" +
          "  <name>Cake</name>\n" +
          "  <id>0001</id>\n" +
          "  <type>donut</type>\n" +
          "  <topping>\n" +
          "    <id>5001</id>\n" +
          "    <type>None</type>\n" +
          "  </topping>\n" +
          "  <topping>\n" +
          "    <id>5002</id>\n" +
          "    <type>Glazed</type>\n" +
          "  </topping>\n" +
          "  <topping>\n" +
          "    <id>5005</id>\n" +
          "    <type>Sugar</type>\n" +
          "  </topping>\n" +
          "  <topping>\n" +
          "    <id>5007</id>\n" +
          "    <type>Powdered Sugar</type>\n" +
          "  </topping>\n" +
          "  <topping>\n" +
          "    <id>5006</id>\n" +
          "    <type>Chocolate with Sprinkles</type>\n" +
          "  </topping>\n" +
          "  <topping>\n" +
          "    <id>5003</id>\n" +
          "    <type>Chocolate</type>\n" +
          "  </topping>\n" +
          "  <topping>\n" +
          "    <id>5004</id>\n" +
          "    <type>Maple</type>\n" +
          "  </topping>\n" +
          "</root>\n";

  @Test
  void willConvertJsonStringToXml() {
    JsonToXmlConverter jsonToXmlConverter = new JsonToXmlConverter();
    String actualXml = jsonToXmlConverter.convertJsonToXml(JSON_INPUT);

    assertEquals(EXPECTED_XML, actualXml);
  }

  @Test
  void willConvertJsonStringToXmlFile() throws IOException {
    JsonToXmlConverter jsonToXmlConverter = new JsonToXmlConverter();

    jsonToXmlConverter.convertJsonToXmlFile(JSON_INPUT);

    Path pathToOutput = Paths.get("output.xml");
    String fileContent = new String(Files.readAllBytes(pathToOutput));

    assertTrue(Files.exists(pathToOutput));
    assertEquals(EXPECTED_XML, fileContent);
  }

  @Test
  void willConvertComplexJsonStringToXmlFile() throws IOException {
    JsonToXmlConverter jsonToXmlConverter = new JsonToXmlConverter();

    jsonToXmlConverter.convertJsonToXmlFile(JSON_INPUT_TWO);

    Path pathToOutput = Paths.get("output.xml");
    String fileContent = new String(Files.readAllBytes(pathToOutput));

    assertTrue(Files.exists(pathToOutput));
    assertEquals(EXPECTED_XML_TWO, fileContent);
  }

}