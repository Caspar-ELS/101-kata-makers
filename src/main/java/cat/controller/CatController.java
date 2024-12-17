package cat.controller;

import lombok.extern.slf4j.Slf4j;
import cat.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cat.service.CatService;

@Slf4j
@RestController
public class CatController {

  @Autowired
  private CatService catService;

  @PostMapping(path = "/cat")
  public String isCat(@RequestBody Animal animal) {
    log.info("Received new request to check if animal is a cat: animal={}", animal);
    boolean isCat = catService.isCat(animal);
    return isCat ? "Meow!" : "Not a cat!";
  }

}
