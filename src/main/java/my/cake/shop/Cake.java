package my.cake.shop;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import my.cake.shop.model.CakeColor;
import my.cake.shop.model.CakeSize;

@Getter
@Setter
public class Cake {

  private CakeColor color;
  private String type;
  private CakeSize size;
  private String name;
  private Map<CakeSize, Integer> priceList = Map.of(
      CakeSize.SMALL, 20,
      CakeSize.MEDIUM, 30,
      CakeSize.LARGE, 50);
  private Duration expiresIn;
  private long cakePreparedTime;

  public Cake() {
    this.cakePreparedTime = Instant.now().toEpochMilli();
    this.expiresIn = Duration.of(5, ChronoUnit.SECONDS);
  }

  public Cake(CakeColor cakeColor, String cakeType, CakeSize cakeSize) {
    this();
    this.color = cakeColor;
    this.type = cakeType;
    this.size = cakeSize;
    this.name = String.format("%s %s %s cake", this.getSize(), this.getColor().name(),
        this.getType());
  }

  public String getName() {
    return this.name;
  }

  public String getPrice() {
    return "£" + priceList.get(this.getSize());
  }

  public Integer getCakePrice() {
    return priceList.get(this.getSize());
  }

  public String getExpiresIn() {
    return isCakeExpired() ? "Expired"
        : String.format("Cake will expire in another %s seconds",
            (expiresIn.toMillis() - getTimePassed()) / 1000);
  }

  private long getTimePassed(){
    return (Instant.now().toEpochMilli()) - this.getCakePreparedTime();
  }
  public boolean isCakeExpired() {
    return (getTimePassed() - this.expiresIn.toMillis()) > 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cake cake = (Cake) o;
    return color == cake.color && Objects.equals(type, cake.type)
        && Objects.equals(size, cake.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, type, size);
  }
}