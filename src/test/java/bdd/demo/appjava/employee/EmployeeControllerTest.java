package bdd.demo.appjava.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Optional;

import static bdd.demo.appjava.employee.Constants.APIPATH_EMPLOYEES;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@Slf4j
public class EmployeeControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    public EmployeeControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testPost() throws Exception {
        Long id = 1L;
        Employee employee = Employee.builder().firtName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        when(employeeService.create(employee)).thenReturn(id);
        final MvcResult mvcResult = this.mockMvc.perform(post(APIPATH_EMPLOYEES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost" + APIPATH_EMPLOYEES + "/" + id.toString()))
                .andExpect(jsonPath("$.id", is(id), Long.class))
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGet() throws Exception {
        Long id = 1L;
        Optional<Employee> employee = Optional.of(Employee.builder().firtName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build());
        when(employeeService.getById(id)).thenReturn(employee);
        final MvcResult mvcResult = this.mockMvc.perform(get(APIPATH_EMPLOYEES + "/" + id.toString()))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firtName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.dob", is("1970-11-30")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetNotFound() throws Exception {
        Long id = 1L;
        Optional<Employee> employee = Optional.empty();
        when(employeeService.getById(id)).thenReturn(employee);
        this.mockMvc.perform(get(APIPATH_EMPLOYEES + "/" + id.toString()))
                //.andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
