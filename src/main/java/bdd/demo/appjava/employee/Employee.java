package bdd.demo.appjava.employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

//@lombok.Getter // Need getters for the Controller to convert object to json
@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Data
@lombok.NoArgsConstructor
@Entity
public class Employee {
    LocalDate dob;
    Gender gender;
    @Id
    private Long id;
    private String firtName;
    private String lastName;
}
