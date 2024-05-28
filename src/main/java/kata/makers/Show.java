package kata.makers;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Show {

  private String title;
  private String yearReleased;
  private String director;
  private String genre;
  private String language;
  private String streamingPlatform;

}
