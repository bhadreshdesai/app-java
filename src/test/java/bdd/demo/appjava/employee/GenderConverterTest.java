package bdd.demo.appjava.employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GenderConverterTest {
    @Test
    void testCodeMToEnum() {
        GenderConverter genderConverter = new GenderConverter();
        Gender male = genderConverter.convertToEntityAttribute("M");
        assertEquals(male, Gender.MALE);
    }

    @Test
    void testCodeFToEnum() {
        GenderConverter genderConverter = new GenderConverter();
        Gender female = genderConverter.convertToEntityAttribute("F");
        assertEquals(female, Gender.FEMALE);
    }

    @Test
    void testInvalidCode() {
        GenderConverter genderConverter = new GenderConverter();
        assertThrows(IllegalArgumentException.class, () -> {
            genderConverter.convertToEntityAttribute("f");
        });
    }

    @Test
    void testNullCode() {
        GenderConverter genderConverter = new GenderConverter();
        Gender female = genderConverter.convertToEntityAttribute(null);
        assertNull(female);
    }

    @Test
    void testNullEnum() {
        GenderConverter genderConverter = new GenderConverter();
        String female = genderConverter.convertToDatabaseColumn(null);
        assertNull(female);
    }

    @Test
    void testEnumToCodeM() {
        GenderConverter genderConverter = new GenderConverter();
        String male = genderConverter.convertToDatabaseColumn(Gender.MALE);
        assertEquals(male, "M");
    }

    @Test
    void testEnumToCodeF() {
        GenderConverter genderConverter = new GenderConverter();
        String female = genderConverter.convertToDatabaseColumn(Gender.FEMALE);
        assertEquals(female, "F");
    }


}
