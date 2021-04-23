package bdd.demo.appjava.home;

import bdd.demo.appjava.utils.MessageTranslator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// https://github.com/zowe/sample-spring-boot-api-service/tree/master/zowe-rest-api-sample-spring
// Controller: https://github.com/zowe/sample-spring-boot-api-service/blob/master/zowe-rest-api-sample-spring/src/main/java/org/zowe/sample/apiservice/greeting/GreetingController.java

// https://github.com/zowe/sample-spring-boot-api-service/blob/master/zowe-rest-api-sample-spring/src/test/java/org/zowe/sample/apiservice/greeting/GreetingControllerTests.java
@WebMvcTest(HomeController.class)
// Import MessageTranslator so the message translation works in the Controller unit test.
@Import(MessageTranslator.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        this.mockMvc.perform(get("/home/greetings/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }

    @Test
    public void test_ES() throws Exception {
        this.mockMvc.perform(get("/home/greetings/1").header("Accept-Language", "es")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola")));
    }
}
