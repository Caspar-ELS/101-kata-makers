package film.lab.service.print;

import film.lab.model.Order;
import film.lab.model.Prints;
import film.lab.model.Quality;
import film.lab.model.Size;
import film.lab.service.develop.DevelopService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrintService {

  private final DevelopService developService;

  private double total;

  public PrintService(DevelopService developService) {
    this.developService = developService;
  }

  public double print(Order order) {
    if (isFromPreviousOrder(order.getPrints())) {
      calculateCost(order.getPrints());
    }
    return total;
  }

  public double getTotal() {
    return total;
  }

  private void calculateCost(Prints prints) {
    updateTotalFrom(prints.getQuality());
    updateTotalFrom(prints.getSize());
  }

  private void updateTotalFrom(Quality quality) {
    if (quality.equals(Quality.HIGH)) {
      total += 4.0;
    } else if (quality.equals(Quality.MEDIUM)) {
      total += 3.0;
    } else if (quality.equals(Quality.LOW)) {
      total += 2.0;
    }
  }

  private void updateTotalFrom(Size size) {
    if (size.equals(Size.SMALL)) {
      total += 2.0;
    } else if (size.equals(Size.MEDIUM)) {
      total += 3.0;
    } else if (size.equals(Size.LARGE)) {
      total += 4.0;
    }
  }

  private boolean isFromPreviousOrder(Prints prints) {
    return developService.getCompletedOrders().contains(prints.getDevelopOrderNumber())
        && isWithinPastYear(prints.getDevelopOrderNumber());
  }

  private boolean isWithinPastYear(String developOrderNumber) {
    LocalDate date = LocalDate.parse(extractDateFrom(developOrderNumber),
        DateTimeFormatter.ISO_LOCAL_DATE);
    return date.isAfter(LocalDate.now().minusYears(1));
  }

  private String extractDateFrom(String developOrderNumber) {
    return developOrderNumber.split("_")[1];
  }
}
