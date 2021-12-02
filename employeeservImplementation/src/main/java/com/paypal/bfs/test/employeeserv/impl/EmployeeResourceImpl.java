package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.validator.Validator;
import com.paypal.bfs.test.employeeserv.process.EmployeeProcess;
import com.paypal.bfs.test.employeeserv.errors.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeProcess employeeProcess;

    @Autowired
    private Validator validator;

    private EmployeeResourceImpl(EmployeeProcess employeeProcess, Validator validator) {
        this.employeeProcess = employeeProcess;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Optional<Employee> employee = employeeProcess.byId(id);
        return employee.isPresent() ? new ResponseEntity<>(employee.get(),HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);


        /*Employee employee = new Employee();
        employee.setId(Integer.valueOf(id));
        employee.setFirstName("BFS");
        employee.setLastName("Developer");

        return new ResponseEntity<>(employee, HttpStatus.OK);*/
    }

    @Override
    public ResponseEntity employeeCreate(Employee employeeRequest) {

        if(null != employeeRequest.getId() ){
            Optional<Employee> employee = employeeProcess.byId(employeeRequest.getId()+"");
            if(employee.isPresent()){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Employee Exists!");
            }
        }

        Optional<List<Errors>> error = validator.getErrors(employeeRequest);
        if(error.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        return employeeProcess.create(employeeRequest) ? new ResponseEntity<>(HttpStatus.CREATED) :
                new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

}
