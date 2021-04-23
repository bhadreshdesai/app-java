package bdd.demo.appjava.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Long create(Employee employee) {
        employee = employeeRepository.save(employee);
        return employee.getId();
    }

    @Transactional
    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> getById(@NotNull Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public void deleteById(@NotNull Long id) {
        employeeRepository.deleteById(id);
    }
}
