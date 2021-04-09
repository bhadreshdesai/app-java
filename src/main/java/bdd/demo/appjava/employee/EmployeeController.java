package bdd.demo.appjava.employee;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Tag(name = "Employees")
@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {
    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity create(@RequestBody Employee employee, UriComponentsBuilder uriComponentsBuilder) {
        logger.info(employee.toString());
        //Long id = employeeService.create(employee);
        Long id = 1L;
        employee.setId(id);
        final URI uri = uriComponentsBuilder.path("/v1/employees/{id}")
                .build(id);
        return ResponseEntity.created(uri)
                //.build()
                .body(employee)
                ;
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        //Employee employee = employeeService.getById(id);
        Employee employee = Employee.builder().id(1L).firtName("John").lastName("Doe").dob(LocalDate.of(1970, 11, 30)).gender(Gender.MALE).build();
        return ResponseEntity.ok(employee);
    }
}
