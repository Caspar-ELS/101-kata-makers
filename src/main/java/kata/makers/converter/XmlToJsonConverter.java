package kata.makers.converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.XML;

public class XmlToJsonConverter {

  public String convertXmlToJson(String xmlToConvert) {
    JSONObject jsonObject = XML.toJSONObject(xmlToConvert);
    return jsonObject.toString(2);
  }


  public void convertXmlToJsonFile(String xmlToConvert) throws IOException {
    String json = convertXmlToJson(xmlToConvert);
    Files.write(Paths.get("output.json"), json.getBytes());
  }
}
