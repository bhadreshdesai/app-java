package bdd.demo.appjava.employee;

import bdd.demo.appjava.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class EmployeeService extends BaseService<Employee, Long, EmployeeRepository> {

}
