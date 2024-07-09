package kata.makers.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;
import kata.makers.exception.CreateFlightException;
import kata.makers.model.Flight;
import org.junit.jupiter.api.Test;

class FlightServiceTest {

  private final String flightNumber = "Air force 1";

  @Test
  void canFindAFlightWithFlightNumber() {
    Flight expectedFlight = new Flight("UK", "US", 50.0);

    Map<String, Flight> flightInformation = Map.of(flightNumber, expectedFlight);

    FlightService flightService = new FlightService(flightInformation);
    Optional<Flight> optionalFlightFromService = flightService.getFlightByNumber(flightNumber);
    assertTrue(optionalFlightFromService.isPresent());
    assertEquals(expectedFlight, optionalFlightFromService.get());
  }

  @Test
  void noFlightFoundWithNonExistingFlightNumber() {
    FlightService flightService = new FlightService(new HashMap<>());
    Optional<Flight> optionalFlightFromService = flightService.getFlightByNumber(
        "NotExistingFlight");
    assertTrue(optionalFlightFromService.isEmpty());
  }

  @Test
  void ableToCreateAFlight() throws CreateFlightException {
    Map<String, Flight> flightInformation = new HashMap<>();

    String from = "from";
    String to = "to";
    double price = 10.5;

    Flight flightExpected = new Flight(from, to, price);

    String flightNumberInput = flightNumber + "\n" + from + "\n" + to + "\n" + price;
    InputStream in = new ByteArrayInputStream(flightNumberInput.getBytes());
    System.setIn(in);

    FlightService flightService = new FlightService(flightInformation);
    flightService.create();

    Flight flightCreated = flightInformation.get(flightNumber);

    assertThat(flightExpected).usingRecursiveComparison().isEqualTo(flightCreated);
  }

  @Test
  void cannotCreateAFlightBecauseOfPriceIsNotNumber() {
    Map<String, Flight> flightInformation = new HashMap<>();

    String from = "from";
    String to = "to";
    String invalidPrice = "invalidPrice";

    String flightNumberInput = flightNumber + "\n" + from + "\n" + to + "\n" + invalidPrice;
    InputStream in = new ByteArrayInputStream(flightNumberInput.getBytes());
    System.setIn(in);

    FlightService flightService = new FlightService(flightInformation);
    assertThrows(InputMismatchException.class, flightService::create);
  }

  @Test
  void cannotCreateAFlightBecauseOfDuplicateFlightNumber() {
    Flight expectedFlight = new Flight("UK", "US", 50.0);

    Map<String, Flight> flightInformation = Map.of(flightNumber, expectedFlight);

    String from = "from";
    String to = "to";
    String invalidPrice = "invalidPrice";

    String flightNumberInput = flightNumber + "\n" + from + "\n" + to + "\n" + invalidPrice;
    InputStream in = new ByteArrayInputStream(flightNumberInput.getBytes());
    System.setIn(in);

    FlightService flightService = new FlightService(flightInformation);
    assertThrows(CreateFlightException.class, flightService::create);
  }

}
