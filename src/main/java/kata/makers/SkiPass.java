package kata.makers;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkiPass {

  private String id;
  private int rides;
  private int credit;

  public SkiPass() {
    this.id = String.valueOf(UUID.randomUUID());
    this.rides = 5;
    this.credit = 0;
  }

  public int topUp(int topUpAmount) {
    this.credit += topUpAmount;
    return this.credit;
  }

  public int charge(int chargeAmount) {
    this.credit -= chargeAmount;
    return this.credit;
  }
}
