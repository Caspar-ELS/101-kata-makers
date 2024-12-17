package cat.service;

import cat.model.Animal;
import org.springframework.stereotype.Service;

@Service
public class CatService {

  public boolean isCat(Animal animal) {
    return animal.getNumberOfPaws() == 4
        && animal.isHasTail()
        && animal.isTailLong()
        && animal.isCarnivore()
        && animal.isHasWhiskers()
        && animal.isHasRetractableClaws()
        && animal.isPurrs()
        && animal.isMeows()
        && animal.isDomesticated();
  }
}
