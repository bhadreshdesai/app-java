package bdd.demo.appjava.employee;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Tag(name = "Employees")
@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        //Employee employee = employeeService.getById(id);
        Employee employee = Employee.builder().id(1L).firtName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        return ResponseEntity.ok(employee);
    }
}
