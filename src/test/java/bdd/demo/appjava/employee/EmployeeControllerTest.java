package bdd.demo.appjava.employee;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerTest.class);

    private static final String URL = "/api/employees";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(get(URL + "/1"))
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
