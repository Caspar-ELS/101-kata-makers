package cat.controller;

import static cat.utils.TestUtilities.buildCat;
import static cat.utils.TestUtilities.buildNotACat;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.service.CatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatControllerTest {

  private static final String ENDPOINT = "/cat";

  @Value("${security.username}")
  private String username;

  @Value("${security.password}")
  private String password;

  @MockBean
  private CatService catService;

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private CatController catController;

  @BeforeEach
  public void setUp() {
    openMocks(this);
  }

  @Test
  void shouldReturnMeowIfCat() throws Exception {

    String payload = new ObjectMapper().writeValueAsString(buildCat());

    mockMvc.perform(post(ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload)
            .with(httpBasic(username, password)))
        .andExpect(status().isOk())
        .andExpect(content().string("Meow!"));
  }

  @Test
  void shouldNotReturnMeowIfNotACat() throws Exception {

    String payload = new ObjectMapper().writeValueAsString(buildNotACat());

    mockMvc.perform(post(ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload)
            .with(httpBasic(username, password)))
        .andExpect(status().isOk())
        .andExpect(content().string("Not a cat!"));
  }

}