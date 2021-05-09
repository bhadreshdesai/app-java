package bdd.demo.appjava.employee;

import javax.persistence.*;
import java.time.LocalDate;

//@lombok.Getter // Need getters for the Controller to convert object to json
@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Data
@lombok.NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    LocalDate dob;
    @Convert(converter=GenderConverter.class)
    Gender gender;
}
