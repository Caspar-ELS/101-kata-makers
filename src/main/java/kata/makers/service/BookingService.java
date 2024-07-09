package kata.makers.service;

import java.util.Optional;
import java.util.Scanner;
import kata.makers.enums.Role;
import kata.makers.exception.CreateFlightReservationException;
import kata.makers.model.Flight;
import kata.makers.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingService {

  FlightService flightService;
  FinancialService financialService;

  private static final Logger log = LoggerFactory.getLogger(BookingService.class);

  User merchant = new User(Role.MERCHANT, "Travel agency", 0.0);
  User user = new User(Role.CUSTOMER, "jason", 1000.0);

  public BookingService(FlightService flightService) {
    this.flightService = flightService;
    this.financialService = new FinancialService();
  }

  public void createFlightReservationAndTransferMoney(User customer) throws CreateFlightReservationException {

    Scanner scanner = new Scanner(System.in);
    log.info("Please input flight number");
    String flightNumber = scanner.nextLine();

    Flight flight = getFlightByFlightNumber(flightNumber);

    log.info("Successfully book flight - {}", flightNumber);
    transferMoney(customer, merchant, flight.getPrice());
  }

  private Flight getFlightByFlightNumber(String flightNumber) throws CreateFlightReservationException {
    Optional<Flight> flightOptional = flightService.getFlightByNumber(flightNumber);
    if (flightOptional.isEmpty()) {
      throw new CreateFlightReservationException("Cannot find flight" + flightNumber);
    }

    return flightOptional.get();
  }

  private void transferMoney(User transferFrom, User transferTo, Double amount) {
    financialService.transferMoney(transferFrom, transferTo, amount);
  }
}
