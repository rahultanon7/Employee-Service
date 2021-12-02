package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping(value="/v1/bfs/employees/{id}", method = RequestMethod.GET)
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);

    /**
     * Retrieves the {@link Employee} resource.
     *
     * @param id employee id.
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param address
     * @return {@link Employee} resource.
     */
    @PostMapping(value = "/v1/bfs/employee/", consumes = APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity employeeCreate(@Valid @RequestBody final Employee employee);


    // ----------------------------------------------------------
    // TODO - add a new operation for creating employee resource.
    // ----------------------------------------------------------
}
