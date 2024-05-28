package kata.makers;

import java.util.HashMap;
import java.util.Scanner;
import kata.makers.exception.CreateFlightException;
import kata.makers.exception.CreateFlightReservationException;
import kata.makers.service.BookingService;
import kata.makers.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    FlightService flightService = new FlightService(new HashMap<>());
    BookingService bookingService = new BookingService(flightService);

    Scanner scanner = new Scanner(System.in);
    while (true) {
      log.info("""
          Enter:
          1 to list all the flight
          2 to create a flight
          3 to book a flight
          or q to quit
          """);
      String input = scanner.nextLine();

      if ("1".equals(input)) {
        flightService.listAll();
      } else if ("2".equals(input)) {
        try {
          flightService.create();
        } catch (CreateFlightException createFlightException) {
          log.error("Error when creating a flight: {}", createFlightException.getMessage());
        }
      } else if ("3".equals(input)) {
        try {
          bookingService.createFlightReservation();
        } catch (CreateFlightReservationException createFlightReservationException) {
          log.error("Error when making flight reservation: {}",
              createFlightReservationException.getMessage());
        }
      } else if ("q".equals(input)) {
        log.info("Exiting...");
        break;
      } else {
        log.error("Invalid input. Please enter 1, 2, or q.");
      }
    }

    scanner.close();
  }
}
