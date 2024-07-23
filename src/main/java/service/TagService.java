package service;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import java.util.HashMap;
import java.util.Map;

public class TagService {

  public static final String SERVICE_SHORT_NAME = "Role";
  public static final String ENVIRONMENT = "Environment";


  public Map<String, String> getTagsFrom(Instance instance) {
    Map tagMap = new HashMap<String, String>();
    for (Tag tag : instance.getTags()) {
      tagMap.put(tag.getKey(), tag.getValue());
    }
    return tagMap;
  }

}
