package bdd.demo.appjava.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class OpenApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testOpenApiSpec() throws Exception {
        final String APIPATH_APIDOCS = "/v3/api-docs";

        final MvcResult mvcResult = this.mockMvc.perform(get(APIPATH_APIDOCS))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String responseText = mvcResult.getResponse().getContentAsString();
        // Convert the json to OpenAPI
        OpenAPI openAPI = objectMapper.readValue(responseText, OpenAPI.class);

        // test Tags
        List<Tag> tags = openAPI.getTags();
        assertThat(tags.size(), is(1));
        Tag tag = tags.get(0);
        assertThat(tag.getName(), is("Employees"));

        // test Paths
        Paths paths = openAPI.getPaths();
        assertThat(paths.size(), is(4));
        assertThat(paths.keySet(), containsInAnyOrder("/api/employees", "/home/greetings/{id}", "/home/about", "/api/employees/{id}"));
    }
}
