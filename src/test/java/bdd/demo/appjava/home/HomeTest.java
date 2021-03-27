package bdd.demo.appjava.home;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeTest {
    private static final Logger LOG = LoggerFactory.getLogger(HomeTest.class);

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public HomeTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testGreetings() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(
                get("/home/greetings/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn().getResponse();
        String responseText = response.getContentAsString();
        LOG.info("testGreetings response: " + responseText);
        GreetingsResponse greetingsResponse = objectMapper.readValue(responseText, GreetingsResponse.class);
        assertEquals(1, greetingsResponse.getId());
        assertEquals("Hello", greetingsResponse.getGreetings());
    }

    @Test
    public void testGreetings_ES() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(
                get("/home/greetings/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", "es-ES")
        ).andExpect(status().isOk()).andReturn().getResponse();
        String responseText = response.getContentAsString();
        LOG.info("testGreetings response: " + responseText);
        GreetingsResponse greetingsResponse = objectMapper.readValue(responseText, GreetingsResponse.class);
        assertEquals(1, greetingsResponse.getId());
        assertEquals("Hola", greetingsResponse.getGreetings());
    }

    @Test
    public void testGreetingsMood_ES() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(
                get("/home/greetings/1?mood=buen")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", "es-ES")
        ).andExpect(status().isOk()).andReturn().getResponse();
        String responseText = response.getContentAsString();
        LOG.info("testGreetings response: " + responseText);
        GreetingsResponse greetingsResponse = objectMapper.readValue(responseText, GreetingsResponse.class);
        assertEquals(1, greetingsResponse.getId());
        assertEquals("Hola", greetingsResponse.getGreetings());
        assertEquals("Estas de buen humor", greetingsResponse.getMood());
    }

    @Test
    public void testGreetingsMood_FR() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(
                get("/home/greetings/1?mood=bonne")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", "fr-FR")
        ).andExpect(status().isOk()).andReturn().getResponse();
        String responseText = response.getContentAsString();
        LOG.info("testGreetings response: " + responseText);
        GreetingsResponse greetingsResponse = objectMapper.readValue(responseText, GreetingsResponse.class);
        assertEquals(1, greetingsResponse.getId());
        assertEquals("Hello", greetingsResponse.getGreetings());
        assertEquals("You are in a bonne mood", greetingsResponse.getMood());
    }
}
