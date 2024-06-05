package film.lab.service.develop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import film.lab.model.CustomerDetails;
import film.lab.model.Film;
import film.lab.model.Format;
import film.lab.model.Order;
import film.lab.model.Prints;
import film.lab.model.Quality;
import film.lab.model.Size;
import film.lab.model.Type;
import film.lab.service.print.PrintService;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class DevelopServiceTest {

  @Mock
  private PrintService printService;

  @InjectMocks
  private DevelopService developService;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void willCalculateCostOfDevelopingRollOf35mmBlackAndWhiteFilm() {
    Film film = new Film();
    film.setFormat(Format.STANDARD_35MM);
    film.setType(Type.BLACK_AND_WHITE);

    Order order = new Order();
    order.setFilm(film);
    order.setCustomerDetails(getCustomerDetails());

    developService.develop(order);

    assertEquals(7.5, developService.getTotal());

  }

  @Test
  void willCalculateCostOfDevelopingRollOf120ColourFilm() {
    Film film = new Film();
    film.setFormat(Format.MEDIUM_120);
    film.setType(Type.COLOUR);

    Order order = new Order();
    order.setFilm(film);
    order.setCustomerDetails(getCustomerDetails());

    developService.develop(order);

    assertEquals(9.5, developService.getTotal());
  }

  @Test
  void willAddDevelopedFilmToCompletedOrders() {
    Film film = new Film();
    film.setFormat(Format.MEDIUM_120);
    film.setType(Type.COLOUR);

    Order order = new Order();
    order.setFilm(film);
    order.setCustomerDetails(getCustomerDetails());

    developService.develop(order);

    assertEquals(1, developService.getCompletedOrders().size());
    assertEquals("DOE_" + LocalDate.now(), developService.getCompletedOrders().get(0));
  }

  @Test
  void willCallPrintServiceIfPrintsPresentOnTheOrder() {
    Film film = new Film();
    film.setFormat(Format.MEDIUM_120);
    film.setType(Type.COLOUR);

    Prints prints = new Prints();
    prints.setQuality(Quality.HIGH);
    prints.setSize(Size.MEDIUM);

    Order order = new Order();
    order.setFilm(film);
    order.setCustomerDetails(getCustomerDetails());
    order.setPrints(prints);

    developService.develop(order);

    verify(printService).print(any());

    when(printService.print(any())).thenReturn(7.0);
    assertEquals(9.5, developService.getTotal());
  }

  private static CustomerDetails getCustomerDetails() {
    CustomerDetails customerDetails = new CustomerDetails();
    customerDetails.setFirstName("John");
    customerDetails.setLastName("Doe");
    return customerDetails;
  }
}