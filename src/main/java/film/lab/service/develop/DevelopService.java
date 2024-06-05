package film.lab.service.develop;

import film.lab.model.CustomerDetails;
import film.lab.model.Film;
import film.lab.model.Format;
import film.lab.model.Order;
import film.lab.model.Type;
import film.lab.service.print.PrintService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DevelopService {

  private final PrintService printService;
  private double total;
  private final List<String> completedOrders = new ArrayList<>();

  public DevelopService(PrintService printService) {
    this.printService = printService;
  }

  public void develop(Order order) {
    calculateCost(order.getFilm());

    if (order.getPrints() != null) {
      printService.print(order);
    }

    completedOrders.add(createOrderNumber(order.getCustomerDetails()));
  }

  public List<String> getCompletedOrders() {
    return completedOrders;
  }

  public double getTotal() {
    return total;
  }

  private void calculateCost(Film film) {
    updateTotalFrom(film.getFormat());
    updateTotalFrom(film.getType());
  }

  private void updateTotalFrom(Type type) {
    if (type.equals(Type.BLACK_AND_WHITE)) {
      total += 2.5;
    } else if (type.equals(Type.COLOUR)) {
      total += 3.5;
    }
  }

  private void updateTotalFrom(Format format) {
    if (format.equals(Format.STANDARD_35MM)) {
      total += 5.0;
    } else if (format.equals(Format.MEDIUM_120)) {
      total += 6.0;
    }
  }

  private static String createOrderNumber(CustomerDetails customerDetails) {
    return customerDetails.getLastName().toUpperCase()
        + "_"
        + LocalDate.now();
  }
}
