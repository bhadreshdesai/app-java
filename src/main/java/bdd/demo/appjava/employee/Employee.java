package bdd.demo.appjava.employee;

import bdd.demo.appjava.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.time.LocalDate;

//@lombok.Getter // Need getters for the Controller to convert object to json
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Employee extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    LocalDate dob;
    @Convert(converter=GenderConverter.class)
    Gender gender;
}
