package bdd.demo.appjava.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode // Need equals implementation for the mock to work when(employeeService.create(employee)).thenReturn(id);
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
@SuperBuilder
public abstract class BaseEntity<ID> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private ID id;
}
