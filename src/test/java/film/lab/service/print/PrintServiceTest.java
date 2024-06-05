package film.lab.service.print;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import film.lab.model.Order;
import film.lab.model.Prints;
import film.lab.model.Quality;
import film.lab.model.Size;
import film.lab.service.develop.DevelopService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class PrintServiceTest {

  @Mock
  private DevelopService developService;

  @InjectMocks
  private PrintService printService;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void willCalculateCostForPrintsFromPreviousOrderWithinPastYear() {
    Prints prints = new Prints();
    prints.setSize(Size.MEDIUM);
    prints.setQuality(Quality.HIGH);
    prints.setDevelopOrderNumber("DOE_2024-06-03");

    Order order = new Order();
    order.setPrints(prints);

    when(developService.getCompletedOrders()).thenReturn(List.of("DOE_2024-06-03"));

    printService.print(order);

    assertEquals(7.0, printService.getTotal());
  }
}
