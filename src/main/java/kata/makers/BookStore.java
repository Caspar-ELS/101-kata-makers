package kata.makers;

import java.util.*;
import java.util.stream.Collectors;

public class BookStore {

  private final Map<Long, Book> mapOfAllBooksInStore = new HashMap<>();

  public void addNewBookToInventoryMap(Book bookToBeAdded) {
    mapOfAllBooksInStore.put(bookToBeAdded.getId(), bookToBeAdded);
  }

  public boolean removeBookByIdFromInventory(Long idOfBookToBeRemoved) {
    return mapOfAllBooksInStore.remove(idOfBookToBeRemoved) != null;
  }

  public List<Book> searchBooksUsingKeywordString(String kwdstr) {
    return mapOfAllBooksInStore.values().stream()
        .filter(b -> b.getTitle().toLowerCase().contains(kwdstr.toLowerCase())
            || b.getAuthor().toLowerCase().contains(kwdstr.toLowerCase()))
        .collect(Collectors.toList());
  }

  public double calculateTotalValueOfAllBooksInStore() {
    double ttlval = 0.0;
    for (Book currentBookInLoop : mapOfAllBooksInStore.values()) {
      double prc = currentBookInLoop.getPrice();
      int qty = currentBookInLoop.getQuantity();
      ttlval = ttlval + (prc * qty);
    }
    return ttlval;
  }

  public Book getBk(Long idx) {
    return mapOfAllBooksInStore.get(idx);
  }

  public List<Book> getBooksByTypeOfSort(String sortType) {
    List<Book> resultList = new ArrayList<>();
    if (sortType.equals("price")) {
      resultList = mapOfAllBooksInStore.values().stream()
          .sorted(Comparator.comparingDouble(Book::getPrice))
          .toList();
    } else if (sortType.equals("title")) {
      resultList = mapOfAllBooksInStore.values().stream()
          .sorted(Comparator.comparing(Book::getTitle))
          .toList();
    }
    return resultList;
  }
}