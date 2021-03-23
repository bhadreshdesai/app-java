package bdd.demo.appjava.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void addTest() {
        Calculator calculator = new Calculator();
        Integer result = calculator.add(1, 2);
        Integer expectedResult = 3;
        assertEquals(expectedResult, result);
    }

    @Test
    void subtractTest() {
        Calculator calculator = new Calculator();
        Integer result = calculator.subtract(3, 5);
        Integer expectedResult = 2;
        assertEquals(expectedResult, result);
    }

    @Test
    void multiplyTest() {
        Calculator calculator = new Calculator();
        Integer result = calculator.multiply(3, 5);
        Integer expectedResult = 15;
        assertEquals(expectedResult, result);
    }
}
