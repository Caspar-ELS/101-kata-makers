package kata.makers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventorySystem {
  private static final String COMMA = ",";

  public static List<Map<String, String>> readProductCsv(String path) {
    List<Map<String, String>> result = new ArrayList<>();
    File file = new File(path);

    if (fileNotExistOrBlank(file)) {
      createDummyData(result);
    } else {
      try {
        addCsvDataToList(result, file);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  private static boolean fileNotExistOrBlank(File file) {
    return !file.exists() || file.length() == 0;
  }

  private static void createDummyData(List<Map<String, String>> result) {
    result.add(Map.of("ID", "1",
        "product name", "Dummy Product 1",
        "quantity", "10"));

    result.add(
        Map.of("ID", "2",
            "product name", "Dummy Product 2",
            "quantity", "20"));
  }

  private static void addCsvDataToList(List<Map<String, String>> result, File file)
      throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line;
    String[] headers = br.readLine().split(COMMA);
    while ((line = br.readLine()) != null) {
      String[] values = line.split(COMMA);
      Map<String, String> map = new HashMap<>();
      for (int i = 0; i < headers.length; i++) {
        map.put(headers[i], i < values.length ? values[i] : "");
      }
      result.add(map);
    }
  }

}
