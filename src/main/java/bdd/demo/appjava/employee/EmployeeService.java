package bdd.demo.appjava.employee;

import bdd.demo.appjava.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends BaseService<Employee, Long, EmployeeRepository> {

}
