package kata.makers.service;

import kata.makers.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinancialService {

  private static final Logger log = LoggerFactory.getLogger(FinancialService.class);
  public FinancialService(){

  }

  public void transferMoney(User from, User to, Double amount){
    deductMoneyFrom(from, amount);
    addMoneyTo(to, amount);
    log.info("Successfully transfer ${} from {} to {}", amount, from.getName(), to.getName());
  }

  private void deductMoneyFrom(User user, Double amount){
    user.setBalance(user.getBalance() - amount);
    log.info("Successfully deduct ${} from {}", amount, user.getName());
  }

  private void addMoneyTo(User user, Double amount){
    user.setBalance(user.getBalance() + amount);
    log.info("Successfully add ${} to {}", amount, user.getName());
  }

}
