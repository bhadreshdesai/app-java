package bdd.demo.appjava.base;

import bdd.demo.appjava.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class BaseService<E extends BaseEntity<ID>, ID, R extends JpaRepository<E, ID>> {
    @Autowired
    private R repository;

    @Transactional
    public ID create(E entity) {
        entity = repository.save(entity);
        return entity.getId();
    }

    @Transactional
    public E update(E entity) {
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public Optional<E> getById(@NotNull ID id) {
        return repository.findById(id);
    }

    @Transactional
    public void deleteById(@NotNull ID id) {
        repository.deleteById(id);
    }
}
