package bdd.demo.appjava.employee;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Home")
@RestController
@RequestMapping(path = "/api/pub/home")
public class EmployeeController {
}
