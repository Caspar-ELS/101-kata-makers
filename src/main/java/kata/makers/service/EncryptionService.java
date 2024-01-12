package kata.makers.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.stereotype.Component;

@Component
public class EncryptionService {
public String encryptWithParameter(String plainText, String algorithm, String jasyptPassword){
  StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
  encryptor.setPassword(jasyptPassword);
  encryptor.setAlgorithm(algorithm);
  if ("PBEWITHHMACSHA512ANDAES_256".equals(algorithm)) {
    encryptor.setIvGenerator(new RandomIvGenerator());
  }

  return encryptor.encrypt(plainText);
}
}
