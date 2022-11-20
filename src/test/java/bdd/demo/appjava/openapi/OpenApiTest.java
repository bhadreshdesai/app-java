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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
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
        List expectedTags = Arrays.asList(
                hasProperty("name", is("Employees"))
                , hasProperty("name", is("Home"))
                , hasProperty("name", is("Roles"))
                , hasProperty("name", is("Users"))
        );
        List<Tag> tags = openAPI.getTags();
        assertThat(tags.size(), is(expectedTags.size()));
        assertThat(tags, containsInAnyOrder(expectedTags));

        // test Paths
        List expectedPaths = Arrays.asList(
                equalTo("/home/about")
                , equalTo("/api/employees")
                , equalTo("/api/employees/{id}")
                , equalTo("/home/greetings/{id}")
                , equalTo("/api/roles")
                , equalTo("/api/roles/{id}")
                , equalTo("/api/users")
                , equalTo("/api/users/{id}")
        );

        Paths paths = openAPI.getPaths();
        assertThat(paths.size(), is(expectedPaths.size()));
        assertThat(paths.keySet(), containsInAnyOrder(expectedPaths));
    }
}
