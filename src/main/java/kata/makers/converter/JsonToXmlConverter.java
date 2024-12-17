package kata.makers.converter;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONObject;
import org.json.XML;

public class JsonToXmlConverter {

  public String convertJsonToXml(String jsonToConvert) {
    JSONObject jsonObject = new JSONObject(jsonToConvert);
    String xml = XML.toString(jsonObject);
    return prettyPrint(xml);
  }

  public void convertJsonToXmlFile(String xmlToConvert) throws IOException {
    String xml = convertJsonToXml(xmlToConvert);
    String prettyPrintedXml = prettyPrint(xml);
    assert prettyPrintedXml != null;
    Files.write(Paths.get("output.xml"), prettyPrintedXml.getBytes());
  }

  private String prettyPrint(String xml) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

      StreamSource source = new StreamSource(new java.io.StringReader(xml));
      StringWriter writer = new StringWriter();
      StreamResult result = new StreamResult(writer);
      transformer.transform(source, result);
      return writer.toString().replaceAll("(?m)^[ \t]*\r?\n", "");
    } catch (TransformerException exception) {
      System.out.println("An error occurred while converting JSON to XML: " + exception.getMessage());
    }
    return null;
  }
}
