package bdd.demo.appjava.employee;

import bdd.demo.appjava.base.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static bdd.demo.appjava.employee.Constants.*;

// https://github.com/dariawantech/spring-boot-rest-springdoc-openapi/blob/master/src/main/java/com/dariawan/contactapp/controller/ContactController.java
@Tag(name = TAG_EMPLOYEES, description = DESC_EMPLOYEES_API)
@RestController
@RequestMapping(path = APIPATH_EMPLOYEES)
@Slf4j
public class EmployeeController extends BaseController<Employee, Long, EmployeeRepository> {

}
