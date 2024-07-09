package kata.makers;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import kata.makers.exception.CreateFlightException;
import kata.makers.exception.CreateFlightReservationException;
import kata.makers.exception.NoCustomerFoundException;
import kata.makers.model.User;
import kata.makers.service.BookingService;
import kata.makers.service.FlightService;
import kata.makers.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  static FlightService flightService = new FlightService(new HashMap<>());
  static BookingService bookingService = new BookingService(flightService);
  static UserService userService = new UserService();
  static User currentCustomer = null;

  public static void main(String[] args) throws NoCustomerFoundException {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      try {
        log.info("""
            Enter:
            1 to list all the flight
            2 to create a flight
            3 to book a flight
            4 to create user
            5 to get user information
            or q to quit
            """);
        String input = scanner.nextLine();

        if ("1".equals(input)) {
          listAllFlights();
        } else if ("2".equals(input)) {
          createFlight();
        } else if ("3".equals(input)) {
          createFlightReservation();
        } else if ("4".equals(input)) {
          createNewCustomer();
        } else if ("5".equals(input)) {
          getCurrentCustomerInfo();
        } else if ("q".equals(input)) {
          log.info("Exiting...");
          break;
        } else {
          log.error("Invalid input. Please enter 1, 2, 3, 4, 5 or q.");
        }
      } catch (Exception exception) {
        log.error("Error: {}", exception.getMessage());
      }
    }

    scanner.close();
  }

  private static void listAllFlights() {
    flightService.listAll();
  }

  private static void createFlight() {
    try {
      flightService.create();
    } catch (CreateFlightException createFlightException) {
      log.error("Error when creating a flight: {}", createFlightException.getMessage());
    }
  }

  private static void createFlightReservation() throws NoCustomerFoundException {
    if (Objects.isNull(currentCustomer)) {
      throw new NoCustomerFoundException("Please create a user first");
    }
    try {
      bookingService.createFlightReservationAndTransferMoney(currentCustomer);
    } catch (CreateFlightReservationException createFlightReservationException) {
      log.error("Error when making flight reservation: {}",
          createFlightReservationException.getMessage());
    }
  }

  private static void createNewCustomer() {
    log.info("Please input user name");
    Scanner scanner = new Scanner(System.in);
    String userName = scanner.nextLine();

    log.info("Please input user balance");
    Double balance = scanner.nextDouble();

    User newCustomer = userService.createCustomer(userName, balance);
    log.info("Successfully create user - {}", newCustomer.getName());

    currentCustomer = newCustomer;
  }

  private static void getCurrentCustomerInfo() throws NoCustomerFoundException {
    if (Objects.isNull(currentCustomer)) {
      throw new NoCustomerFoundException("Please create a user first");
    }

    log.info("Current customer: {}", currentCustomer.getName());
    log.info("Balance: {}", currentCustomer.getBalance());
  }
}
