package bdd.demo.appjava.employee;

import java.time.LocalDate;

@lombok.Getter // Need getters for the Controller to convert object to json
@lombok.Builder
public class Employee {
    private Long id;
    private String firtName;
    private String lastName;
    LocalDate dob;
    Gender gender;
}
