package kata.makers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreServiceTest {

  private BookStoreService service;
  private Book book1;
  private Book book2;
  private Book book3;

  @BeforeEach
  void setUp() {
    service = new BookStoreService();
    book1 = new Book(1L, "Effective Java", "Joshua Bloch", 45.0, 2);
    book2 = new Book(2L, "Clean Code", "Robert C. Martin", 40.0, 1);
    book3 = new Book(3L, "Spring in Action", "Craig Walls", 50.0, 3);
    service.addBook(book1);
    service.addBook(book2);
    service.addBook(book3);
  }

  @Test
  void testAddBook() {
    Book newBook = new Book(4L, "Domain-Driven Design", "Eric Evans", 60.0, 1);
    service.addBook(newBook);
    assertEquals(newBook, service.getBook(4L));
  }

  @ParameterizedTest
  @CsvSource({
      "2,true",
      "99,false"
  })
  void testRemoveBook(long id, boolean expectedResult) {
    boolean result = service.removeBook(id);
    assertEquals(expectedResult, result);
    if (expectedResult) {
      assertNull(service.getBook(id));
    }
  }

  @ParameterizedTest
  @CsvSource({
      "java,1,Effective Java",
      "martin,1,Clean Code",
      "in A,1,Spring in Action"
  })
  void testSearchBooks(String keyword, int expectedCount, String expectedTitle) {
    List<Book> results = service.searchBooks(keyword);
    assertEquals(expectedCount, results.size());
    assertEquals(expectedTitle, results.get(0).getTitle());
  }

  @Test
  void testGetTotalInventoryValue() {
    double expected = (45.0 * 2) + (40.0 * 1) + (50.0 * 3);
    assertEquals(expected, service.getTotalInventoryValue(), 0.001);
  }

  @Test
  void testGetAllBooksSortedByPrice() {
    List<Book> sortedBooks = service.getAllBooksSortedByPrice();
    assertEquals(3, sortedBooks.size());
    assertEquals("Clean Code", sortedBooks.get(0).getTitle());
    assertEquals("Effective Java", sortedBooks.get(1).getTitle());
    assertEquals("Spring in Action", sortedBooks.get(2).getTitle());
  }

  @Test
  void testGetAllBooksSortedByTitle() {
    List<Book> sortedBooks = service.getAllBooksSortedByTitle();
    assertEquals(3, sortedBooks.size());
    assertEquals("Clean Code", sortedBooks.get(0).getTitle());
    assertEquals("Effective Java", sortedBooks.get(1).getTitle());
    assertEquals("Spring in Action", sortedBooks.get(2).getTitle());
  }
}

