package bdd.demo.appjava.api.employee;

import bdd.demo.appjava.employee.Employee;
import bdd.demo.appjava.employee.GenderConverter;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static bdd.demo.appjava.employee.Constants.APIPATH_EMPLOYEES;

// https://rieckpil.de/testing-spring-boot-applications-with-rest-assured/

@Slf4j
public class EmployeeStepDef {
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @DataTableType
    public Employee employeeEntryTransformer(Map<String, String> entry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        GenderConverter genderConverter = new GenderConverter();
        return Employee.builder()
                .id(Long.parseLong(entry.get("id")))
                .firstName(entry.get("firstName"))
                .lastName(entry.get("lastName"))
                .dob(LocalDate.parse(entry.get("dob"), formatter))
                .gender(genderConverter.convertToEntityAttribute(entry.get("gender")))
                .build();
    }

    @Given("the user wants to create an employee with the following attributes")
    public void user_wants_to_create_an_employee_with_the_following_attributes(List<Employee> employeeList) throws JSONException {
        String body = "{'firstName':'Jack', 'lastName': 'Jones'}";
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        String url = createURLWithPort(APIPATH_EMPLOYEES);
        log.info(url);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST, entity, String.class);
        log.info(response.toString());
        String actual = response.getBody();
        String expected = "{id:Course1,name:Spring,description:10Steps}";
        log.info(actual);
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
