package film.lab.service.print;

import film.lab.exception.InvalidOrderNumberException;
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

  public double print(Order order) throws InvalidOrderNumberException {
    if (isFromPreviousOrder(order.getPrints())) {
      calculateCost(order.getPrints());
    } else if (isNewOrder(order)) {
      calculateCost(order.getPrints());
    } else {
      throw new InvalidOrderNumberException("Order number must be within past year");
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
    switch (quality) {
      case HIGH -> total += 4.0;
      case MEDIUM -> total += 3.0;
      case LOW -> total += 2.0;
    }
  }

  private void updateTotalFrom(Size size) {
    switch (size) {
      case SMALL -> total += 2.0;
      case MEDIUM -> total += 3.0;
      case LARGE -> total += 4.0;
    }
  }

  private boolean isFromPreviousOrder(Prints prints) {
    return developService.getCompletedOrders().contains(prints.getDevelopOrderNumber())
        && isWithinPastYear(prints.getDevelopOrderNumber());
  }

  private static boolean isNewOrder(Order order) {
    return order.getPrints().getDevelopOrderNumber() == null;
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
