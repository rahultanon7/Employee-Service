package com.paypal.bfs.test.employeeserv.dao;

import com.paypal.bfs.test.employeeserv.bean.EmpTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDb extends JpaRepository<EmpTable,Integer> {
}