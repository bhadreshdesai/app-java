package bdd.demo.appjava.employee;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

// https://programmingtechie.com/2020/10/16/spring-boot-testing-tutorial-unit-testing-with-junit-5-and-mockito/
@Slf4j
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Captor
    private ArgumentCaptor<Employee> employeeArgumentCaptor;

    @Captor
    private ArgumentCaptor<Long> employeeId;

    @Test
    @DisplayName("Create employee test")
    void createTest() {
        log.info("Test create employee");
        final Long ID = 999L;
        Employee employee = Employee.builder().build();
        Employee employeeReturn = Employee.builder().id(ID).build();
        when(employeeRepository.save(employee)).thenReturn(employeeReturn);
        Long id = employeeService.create(employee);
        assertEquals(employeeReturn.getId(), id);
    }

    @Test
    void updateTest() {
        log.info("Test update employee");
        final Long ID = 999L;
        Employee employee = Employee.builder().id(ID).build();
        Employee employeeReturn = Employee.builder().id(ID).build();
        when(employeeRepository.save(employee)).thenReturn(employeeReturn);
        when(employeeRepository.findById(ID)).thenReturn(Optional.of(employeeReturn));
        employeeService.update(ID, employee);
        verify(employeeRepository, times(1)).save(employeeArgumentCaptor.capture());
        assertThat(employeeArgumentCaptor.getValue().getId()).isEqualTo(ID);
    }

    @Test
    void getByIdTest() {
        log.info("Get employee by ID");
        final Long ID = 999L;
        Optional<Employee> employeeReturn = Optional.of(Employee.builder().id(ID).build());
        when(employeeRepository.findById(ID)).thenReturn(employeeReturn);
        Optional<Employee> employee = employeeService.getById(ID);
        assertThat(employee.isPresent());
        assertThat(employee.get().getId()).isEqualTo(ID);
    }

    // https://www.baeldung.com/mockito-void-methods
    @Test
    void deleteByIdTest() {
        log.info("Delete employee by ID");
        final Long ID = 999L;
        doNothing().when(employeeRepository).deleteById(employeeId.capture());
        employeeService.deleteById(ID);
        verify(employeeRepository, times(1)).deleteById(ID);
        assertThat(employeeId.getValue()).isEqualTo(ID);
    }

}
