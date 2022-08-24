package school.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import school.model.Course;
import school.service.CourseService;
import school.service.StudentService;

@AutoConfigureMockMvc
@SpringBootTest
public class CourseControllerTest {

  private static final String TEST_COURSE_NAME = "English";
  private static final Integer TEST_COURSE_CREDITS = 100;
  private static final String TEST_COURSE_PROFESSOR = "Professor Jablonska";
  private static final String COURSE_URL = "/course";
  @MockBean
  private CourseService courseService;

  @MockBean
  private StudentService studentService;

  @Captor
  private ArgumentCaptor<Course> courseArgumentCaptor;

  @Autowired
  private MockMvc mockMvc;


  @Test
  void shouldSendACourseWithTheCorrectParametersToTheCourseService()
      throws Exception {

    String payload = new ObjectMapper().writeValueAsString(getCourse());

    mockMvc.perform(post(COURSE_URL).contentType(MediaType.APPLICATION_JSON).content(payload))
        .andExpect(status().isOk());

    verify(courseService).createCourse(courseArgumentCaptor.capture());
    Course result = courseArgumentCaptor.getValue();
    Assertions.assertThat(TEST_COURSE_NAME).isEqualTo(result.getName());
    Assertions.assertThat(TEST_COURSE_CREDITS).isEqualTo(result.getCredits());
    Assertions.assertThat(TEST_COURSE_PROFESSOR).isEqualTo(result.getProfessor());
  }

  private Course getCourse() {
    return Course.builder()
        .name(TEST_COURSE_NAME)
        .credits(TEST_COURSE_CREDITS)
        .professor(TEST_COURSE_PROFESSOR)
        .build();
  }


}
