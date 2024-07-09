package kata.makers.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import kata.makers.enums.Role;
import kata.makers.exception.CreateFlightReservationException;
import kata.makers.model.Flight;
import kata.makers.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingServiceTest {

  final Flight flight = new Flight("somewhere", "great place", 10.0);
  User customer;

  @BeforeEach
  void beforeEach(){
    customer = new User(Role.CUSTOMER, "customer", 1000.0);
  }

  @Test
  void ableToBookAFlightWithExistingFlight() throws CreateFlightReservationException {

      String flightNumberInput = "ABC123";
      InputStream in = new ByteArrayInputStream(flightNumberInput.getBytes());
      System.setIn(in);

      FlightService flightService = mock(FlightService.class);
      when(flightService.getFlightByNumber(any())).thenReturn(Optional.ofNullable(flight));

      BookingService bookingService = new BookingService(flightService);
      bookingService.createFlightReservationAndTransferMoney(customer);
      verify(flightService, times(1)).getFlightByNumber(any());

  }

  @Test
  void cannotBookAnNotExistingFlight() {

    String flightNumberInput = "NotExist";
    InputStream in = new ByteArrayInputStream(flightNumberInput.getBytes());
    System.setIn(in);

    FlightService flightService = mock(FlightService.class);
    when(flightService.getFlightByNumber(any())).thenReturn(Optional.empty());

    BookingService bookingService = new BookingService(flightService);
    assertThrows(CreateFlightReservationException.class,
        () -> bookingService.createFlightReservationAndTransferMoney(customer));

  }

}
