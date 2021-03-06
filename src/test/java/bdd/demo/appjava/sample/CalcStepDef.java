package bdd.demo.appjava.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalcStepDef {
    Calculator calculator;
    Integer result;

    @Given("I have a calculator")
    public void i_have_a_calculator() {
        calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void i_add_and(Integer int1, Integer int2) {
        result = calculator.add(int1, int2);
    }

    @When("I subtract {int} from {int}")
    public void i_subtract_from(Integer int1, Integer int2) {
        result = calculator.subtract(int1, int2);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(Integer expectedResult) {
        assertEquals(expectedResult, result);
    }
}
