package filter;

import java.util.List;

public class BomServiceFilter {

  List<String> bomServices = List.of("bocs", "tltt", "trsr", "cats", "inas", "tekp", "nesx", "insr",
      "arfx", "aofc", "asfc", "orrx", "trsb", "trds", "asft", "oisu", "kevex", "insp", "insp",
      "orcx", "rertv3", "rersv3", "pofc", "fucx", "rrspv3", "reacv3");

  public boolean isBomService(String service) {
    return bomServices.contains(service);
  }

}
