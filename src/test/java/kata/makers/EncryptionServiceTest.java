package kata.makers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.io.ByteArrayInputStream;
import kata.makers.model.JasyptSecret;
import kata.makers.service.AwsSecretManagerService;
import kata.makers.service.EncryptionService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class EncryptionServiceTest {

  @Mock
  private AwsSecretManagerService awsSecretManagerService;

  @InjectMocks
  private EncryptionService encryptionService;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void encryptPlainText() throws Exception {
    when(awsSecretManagerService.getSecrets(any())).thenReturn(new JasyptSecret(
        "mock_secret_from_AWS"));
    Mockito.mockConstruction(StandardPBEStringEncryptor.class, (mock,context)-> {
      when(mock.encrypt(any())).thenReturn("encrypted password");
    });

    provideInput("plain text to be encrypt");
    provideInput("1");
    provideInput("dev");
String outcome = encryptionService.process();
    assertEquals(outcome, "encrypted password");
  }

  void provideInput(String data) {
    ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }
}
