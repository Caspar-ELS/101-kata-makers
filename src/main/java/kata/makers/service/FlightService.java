package kata.makers.service;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import kata.makers.exception.CreateFlightException;
import kata.makers.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FlightService {
  private Map<String, Flight> flightInformation;
  private static final Logger log = LoggerFactory.getLogger(FlightService.class);
  public FlightService(Map<String, Flight> flightInformation){
   this.flightInformation = flightInformation;
  }

  public void listAll() {
    if (flightInformation.isEmpty()) {
      log.info("no flight here");
    } else {
      for (Map.Entry<String, Flight> entry : flightInformation.entrySet()) {
        log.info("Flight number {} - {}", entry.getKey(), entry.getValue());
      }
    }
  }

  public Optional<Flight> getFlightByNumber(String flightNumber){
    return Optional.ofNullable(flightInformation.get(flightNumber));
  }

  public void create() throws CreateFlightException{
    Scanner scanner = new Scanner(System.in);
    log.info("Please input flight number");
    String flightNumber = scanner.nextLine();

    if (flightInformation.containsKey(flightNumber.trim())) {
      throw new CreateFlightException("flight already exist");
    }

    log.info("Please input from");
    String from = scanner.nextLine();

    log.info("Please input destination");
    String destination = scanner.nextLine();

    log.info("Please input price");
    Double price = scanner.nextDouble();

    Flight flight = new Flight(from, destination, price);

    flightInformation.put(flightNumber, flight);
    log.info("Flight {}, from {} to {} - ${}, created successfully", flightNumber, from, destination, price);
  }
}
