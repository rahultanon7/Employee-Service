package com.paypal.bfs.test.employeeserv.mapper;


        import com.paypal.bfs.test.employeeserv.api.model.Employee;
        import com.paypal.bfs.test.employeeserv.bean.EmpTable;

        import org.springframework.stereotype.Component;

        import java.util.Optional;

@Component
public class EmployeeMapper {

    public EmpTable forDB(Employee employee){
        EmpTable employeeTable = new EmpTable();
        employeeTable.setFirst_name(employee.getFirstName());
        employeeTable.setLast_name(employee.getLastName());
        employeeTable.setDate_of_birth(employee.getDateOfBirth());
        employeeTable.setLine1(employee.getLine1());
        employeeTable.setLine2(employee.getLine2());
        employeeTable.setCity(employee.getCity());
        employeeTable.setState(employee.getState());
        employeeTable.setZip_code(employee.getZipCode());
        employeeTable.setCountry(employee.getCountry());
        return employeeTable;
    }

    public Employee forAPI(EmpTable empTable){
        Employee employee = new Employee();
        employee.setId(Integer.valueOf(empTable.getId()));
        employee.setFirstName(empTable.getFirst_name());
        employee.setLastName(empTable.getLast_name());
        employee.setDateOfBirth(empTable.getDate_of_birth());
        employee.setLine1(empTable.getLine1());
        employee.setLine2(empTable.getLine2());
        employee.setCity(empTable.getCity());
        employee.setState(empTable.getState());
        employee.setZipCode(empTable.getZip_code());
        employee.setCountry(empTable.getCountry());
        return employee;

    }

}