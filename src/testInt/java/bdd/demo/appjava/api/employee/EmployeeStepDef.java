package bdd.demo.appjava.api.employee;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class EmployeeStepDef  {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeStepDef.class);
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Given("the user wants to create an employee with the following attributes")
    public void user_wants_to_create_an_employee_with_the_following_attributes(DataTable dataTable) throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        String url = createURLWithPort("/students/Student1/courses/Course1");
        LOG.info(url);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET, entity, String.class);
        String actual = response.getBody();
        String expected = "{id:Course1,name:Spring,description:10Steps}";
        LOG.info(actual);
        //JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Given("with the following phone numbers")
    public void with_the_following_phone_numbers(DataTable dataTable) {
    }

    @When("the user saves the new employee {string}")
    public void user_saves_the_new_employee(String string) {
    }

    @Then("the save {string}")
    public void the_save(String string) {
    }
}