package kata.makers;

import java.util.List;

public class BookStoreService {
  private BookStore bookStoreInstanceForInventory;

  public BookStoreService() {
    this.bookStoreInstanceForInventory = new BookStore();
  }

  public void addBook(Book bkobj) {
    bookStoreInstanceForInventory.addNewBookToInventoryMap(bkobj);
  }

  public boolean removeBook(Long bookid) {
    return bookStoreInstanceForInventory.removeBookByIdFromInventory(bookid);
  }

  public Book getBook(Long idx) {
    return bookStoreInstanceForInventory.getBk(idx);
  }

  public List<Book> searchBooks(String kwd) {
    return bookStoreInstanceForInventory.searchBooksUsingKeywordString(kwd);
  }

  public double getTotalInventoryValue() {
    return bookStoreInstanceForInventory.calculateTotalValueOfAllBooksInStore();
  }

  public List<Book> getAllBooksSortedByPrice() {
    return bookStoreInstanceForInventory.getBooksByTypeOfSort("price");
  }

  public List<Book> getAllBooksSortedByTitle() {
    return bookStoreInstanceForInventory.getBooksByTypeOfSort("title");
  }
}