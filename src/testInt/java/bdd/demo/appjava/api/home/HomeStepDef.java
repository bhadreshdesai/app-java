package bdd.demo.appjava.api.home;

import bdd.demo.appjava.api.AbstractApiStepDef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class HomeStepDef extends AbstractApiStepDef {
    private static final Logger LOG = LoggerFactory.getLogger(HomeStepDef.class);

    @Given("the user calls the HOME REST API endpoint")
    public void the_user_calls_the_home_rest_api_endpoint() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        String url = getUrl("/home/about");
        LOG.info(url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        testContext().setResponse(response);
    }

    @Then("the result is success")
    public void the_result_is_success() throws JSONException {
        String actual = testContext().getResponse().getBody();
        String expected = "{\"name\":\"Demo Java Application\",\"shortname\":\"DEMO-JAVA-APP\",\"version\":\"1.0.0\"}";
        LOG.info(actual);
        JSONAssert.assertEquals(expected, actual, false);
    }
}
