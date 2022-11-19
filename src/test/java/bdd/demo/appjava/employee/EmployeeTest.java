package bdd.demo.appjava.employee;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class EmployeeTest {
    @Test
    void hashCodeNoId() {
        Employee employee1 = Employee.builder().firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        Employee employee2 = Employee.builder().firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        assertThat(employee1.hashCode(), is(employee2.hashCode()));
    }

    @Test
    void hashCodeSameId() {
        Long id = 101L;
        Employee employee1 = Employee.builder().id(id).firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        Employee employee2 = Employee.builder().id(id).firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        assertThat(employee1.hashCode(), is(employee2.hashCode()));
    }

    @Test
    void hashCodeDifferentId() {
        Long id1 = 101L;
        Long id2 = 102L;
        Employee employee1 = Employee.builder().id(id1).firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        Employee employee2 = Employee.builder().id(id2).firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        assertThat(employee1.hashCode(), is(not(employee2.hashCode())));
    }

    @Test
    void equalNoIdSameData() {
        Employee employee1 = Employee.builder().firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        Employee employee2 = Employee.builder().firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        assertThat("equalNoIdSameData", employee1.equals(employee2));
    }

    @Test
    void equalNoIdDifferentData() {
        Employee employee1 = Employee.builder().firstName("John1").lastName("Doe1").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        Employee employee2 = Employee.builder().firstName("John2").lastName("Doe2").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        assertThat("equalNoIdDifferentData", employee1.equals(employee2));
    }

    @Test
    void equalSameIdSameData() {
        Long id = 101L;
        Employee employee1 = Employee.builder().id(id).firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        Employee employee2 = Employee.builder().id(id).firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        assertThat("equalSameIdSameData", employee1.equals(employee2));
    }

    @Test
    void equalSameIdDifferentData() {
        Long id = 101L;
        Employee employee1 = Employee.builder().id(id).firstName("John1").lastName("Doe1").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        Employee employee2 = Employee.builder().id(id).firstName("John2").lastName("Doe2").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        assertThat("equalSameIdDifferentData", employee1.equals(employee2));
    }

    @Test
    void equalDifferentIdSameData() {
        Long id1 = 101L;
        Long id2 = 102L;
        Employee employee1 = Employee.builder().id(id1).firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        Employee employee2 = Employee.builder().id(id2).firstName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        assertThat("equalDifferentIdSameData", !employee1.equals(employee2));
    }
}
