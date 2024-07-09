package film.lab.model;

public enum Format {
  STANDARD_35MM("35mm"),
  MEDIUM_120("120");

  private String format;

  Format(String format) {
    this.format = format;
  }
}
