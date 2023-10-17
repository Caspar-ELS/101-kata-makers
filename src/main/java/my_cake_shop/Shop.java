package my_cake_shop;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import my_cake_shop.exception.CakeException;

public class Shop {

  private List<Cake> cakes = new ArrayList<>();
  private int profit = 0;
  private final Inventory inventory;

  public Shop() {
    Thread thread = new Thread(this::continuouslyCheckIfCakeIsExpiresSoonAndRemove);
    thread.start();

    this.inventory = new Inventory();
  }

  public int howMuchCake() {
    return cakes.size();
  }

  public int howMuchProfit() {
    return profit;
  }

  public void add(Cake cake) {
    cakes.add(cake);
  }

  public Cake sellCakeTo(Customer customer) throws CakeException {
    for (int i = 0; i < cakes.size(); i++) {
      Cake cake = cakes.get(i);

      if ((customer.getCakeColor() != null) && cake.getColor() != customer.getCakeColor()) {
        continue;
      }
      if ((customer.getCakeType() != null) && cake.getTypes() != customer.getCakeType()) {
        continue;
      }
      if ((customer.getCakeSize() != null) && cake.getSizes() != customer.getCakeSize()) {
        continue;
      }

      cakes = cakes.stream()
          .filter(cakesToKeep -> !cakesToKeep.getId().equals(cake.getId()))
          .collect(Collectors.toList());

      increaseProfitBy(cake.getPrice());

      return cake;
    }

    return makeNewCake(customer);
  }

  private Cake makeNewCake(Customer customer) throws CakeException {
    increaseProfitBy(-this.inventory.takeIngredientsForCakeAndReturnPrice(customer.getCakeSize()));

    Cake cake = new Cake(customer.getCakeSize(), customer.getCakeColor(), customer.getCakeType());

    increaseProfitBy(cake.getPrice());

    return cake;
  }

  private void increaseProfitBy(int amount) {
    profit += amount;
  }

  @SneakyThrows
  private void continuouslyCheckIfCakeIsExpiresSoonAndRemove() {
    while(true) {
      Thread.sleep(100);

      cakes = cakes.stream()
          .filter(cake -> cake.expiresInSeconds() >= 2)
          .collect(Collectors.toList());
    }
  }
}
