package bdd.demo.appjava.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static bdd.demo.appjava.employee.Constants.APIPATH_EMPLOYEES;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerTest.class);

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testPost() throws Exception {
        Employee employee = Employee.builder().firtName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        final MvcResult mvcResult = this.mockMvc.perform(post(APIPATH_EMPLOYEES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","http://localhost"+ APIPATH_EMPLOYEES + "/1"))
                .andExpect(jsonPath("$.id", is(1)))
                .andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGet() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(get(APIPATH_EMPLOYEES + "/1"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firtName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.dob", is("1970-11-30")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }

}
