package kata.makers.service;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EncryptionDecryptionService {

  @Autowired
  private AwsSecretsService awsSecretsService;

  private String password;
  private String algorithm;
  private String text;

  public void getUserInput() throws Exception {

    Scanner scanner = new Scanner(System.in);
    System.out.println("What's your password?");
    if (scanner.hasNext()) {
      password = scanner.nextLine();
    }

    System.out.println("""
            What's your algorithm? Enter the number:\s
            1. PBEWithHMACSHA512AndAES_256
            2. PBEWithMD5AndTripleDES"""
    );

    if (scanner.hasNext()) {
      algorithm = scanner.nextLine();
      switch (algorithm) {
        case "1" -> algorithm = "PBEWithHMACSHA512AndAES_256";
        case "2" -> algorithm = "PBEWithMD5AndTripleDES";
        default -> throw new Exception("Incorrect input. Please enter a valid algorithm.");
      }
    }

    System.out.println("Enter the text to encrypt/decrypt.");
    if (scanner.hasNext()) {
      text = scanner.nextLine();
    }

//    this.password = awsSecretsService.getSecrets("jasypt-dev").get("password");
  }

  public String encryptPlainText() throws Exception {

    getUserInput();
    log.info("Encrypting the password...");
    StandardPBEStringEncryptor encryptor = getEncryptor();
    return encryptor.encrypt(this.text);
  }

  public String decryptText() throws Exception {

    getUserInput();
    log.info("Decrypting...");
    StandardPBEStringEncryptor encryptor = getEncryptor();
    return encryptor.decrypt(this.text);
  }

  private StandardPBEStringEncryptor getEncryptor() {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setPassword(this.password);
    encryptor.setAlgorithm(this.algorithm);
    encryptor.setIvGenerator(new RandomIvGenerator());
    return encryptor;
  }
}
