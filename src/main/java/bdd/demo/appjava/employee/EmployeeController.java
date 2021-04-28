package bdd.demo.appjava.employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static bdd.demo.appjava.employee.Constants.*;

// https://github.com/dariawantech/spring-boot-rest-springdoc-openapi/blob/master/src/main/java/com/dariawan/contactapp/controller/ContactController.java
@Tag(name = TAG_EMPLOYEES, description = DESC_EMPLOYEES_API)
@RestController
@RequestMapping(path = APIPATH_EMPLOYEES)
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> create(@RequestBody Employee employee, UriComponentsBuilder uriComponentsBuilder) {
        log.info(employee.toString());
        Long id = employeeService.create(employee);
        employee.setId(id);
        final URI uri = uriComponentsBuilder.path(APIPATH_EMPLOYEES + "/{id}")
                .build(id);
        return ResponseEntity.created(uri)
                //.build()
                .body(employee)
                ;
    }

    @Operation(summary = "Get employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeService.getById(id);
        return ResponseEntity.of(employee);
    }
}
