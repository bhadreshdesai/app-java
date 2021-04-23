package bdd.demo.appjava.employee;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

import static bdd.demo.appjava.employee.Constants.APIPATH_EMPLOYEES;
import static bdd.demo.appjava.employee.Constants.TAG_EMPLOYEES;

// https://github.com/dariawantech/spring-boot-rest-springdoc-openapi/blob/master/src/main/java/com/dariawan/contactapp/controller/ContactController.java
@Tag(name = TAG_EMPLOYEES)
@RestController
@RequestMapping(path = APIPATH_EMPLOYEES)
@Slf4j
public class EmployeeController {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> create(@RequestBody Employee employee, UriComponentsBuilder uriComponentsBuilder) {
        log.info(employee.toString());
        //Long id = employeeService.create(employee);
        Long id = 1L;
        employee.setId(id);
        final URI uri = uriComponentsBuilder.path(APIPATH_EMPLOYEES + "/{id}")
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
