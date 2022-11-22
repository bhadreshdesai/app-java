package bdd.demo.appjava.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

@Slf4j
public class BaseController<E extends BaseEntity<ID>, ID, R extends JpaRepository<E, ID>> implements BaseApi<E, ID, R> {
    @Autowired
    BaseService<E, ID, R> service;

    @Override
    public ResponseEntity<E> create(@RequestBody E entity, HttpServletRequest request) {
        log.info(entity.toString());
        ID id = service.create(entity);
        entity.setId(id);
        final URI uri = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .build(id);
        return ResponseEntity.created(uri)
                .body(entity)
                ;
    }

    @Override
    public ResponseEntity<E> getById(@PathVariable("id") ID id) {
        Optional<E> entity = service.getById(id);
        return ResponseEntity.of(entity);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        //log.info("Handled: IllegalArgumentException", ex);
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

}
