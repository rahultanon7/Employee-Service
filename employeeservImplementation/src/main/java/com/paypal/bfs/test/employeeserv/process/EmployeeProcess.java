package com.paypal.bfs.test.employeeserv.process;

        import com.paypal.bfs.test.employeeserv.api.model.Employee;
        import com.paypal.bfs.test.employeeserv.bean.EmpTable;
        import com.paypal.bfs.test.employeeserv.dao.EmployeeDb;
        import com.paypal.bfs.test.employeeserv.mapper.EmployeeMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.Map;
        import java.util.Objects;
        import java.util.Optional;
        import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeProcess {

    @Autowired
    private EmployeeDb employeeDb;

    @Autowired
    private EmployeeMapper employeeMapper;


    private final Map<Integer, Employee> employeeMap = new ConcurrentHashMap<>();

    public EmployeeProcess(EmployeeDb employeeDb, EmployeeMapper employeeMapper) {
        this.employeeDb = employeeDb;
        this.employeeMapper = employeeMapper;
    }

    public Optional<Employee> byId(String id){

        Employee employee = employeeMap.get(Integer.valueOf(id));
        if(Objects.nonNull(employee)){
            return Optional.of(employee);
        }

        Optional<EmpTable> et = employeeDb.findById(Integer.valueOf(id));
        if(et.isPresent()){
            Employee e = employeeMapper.forAPI(et.get());
            if(Objects.nonNull(e)){
                employeeMap.put(e.getId(),e);
                return Optional.of(e);
            }

        }
        return Optional.empty();
    }

    public boolean create(Employee employeeRequest){
        EmpTable employeeTable = null;
        try{
            employeeTable = employeeDb.save(employeeMapper.forDB(employeeRequest));
            employeeMap.put(employeeRequest.getId(), employeeRequest);
            if(Objects.nonNull(employeeTable)){
                return true;
            }
        }catch(Exception e){
            System.out.println("Error in table creating...");
        }

        return false;
    }

}
