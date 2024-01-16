package kata.makers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoService {

  @Autowired
  private EncryptionDecryptionService encryptionDecryptionService;

  private String encryptedPassword;
  private String decryptedText;


  public void foo(){
    log.info("Hello world!");
  }

  public String encrypt() throws Exception {
    encryptedPassword = encryptionDecryptionService.encryptPlainText();
    log.info("Encrypted password is: {}", encryptedPassword);
    return encryptedPassword;
  }

  public String decrypt() throws Exception {
    decryptedText = encryptionDecryptionService.decryptText();
    log.info("Decrypted text is: {}", decryptedText);
    return decryptedText;
  }
}
