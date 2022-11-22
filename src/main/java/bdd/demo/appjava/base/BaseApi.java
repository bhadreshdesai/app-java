package bdd.demo.appjava.base;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public interface BaseApi<E extends BaseEntity<ID>, ID, R extends PagingAndSortingRepository<E, ID>> {
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<E> create(@RequestBody E entity, HttpServletRequest request);

    @Operation(summary = "Get by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid entity id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content)})
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<E> getById(@PathVariable("id") ID id);

    @Operation(summary = "Update an existing entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid entity id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content)})
    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<E> updateEntity(@PathVariable("id") ID id, @RequestBody E entity);

    @Operation(summary = "Delete an existing entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entity deleted successfully", content = @Content)
    })
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<E> deleteEntity(@PathVariable("id") ID id);
}
