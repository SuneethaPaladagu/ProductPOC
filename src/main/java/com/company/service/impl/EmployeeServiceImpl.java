package com.company.service.impl;

import com.company.controller.EmployeeMapper;
import com.company.employee.Employee;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by suneetha on 6/12/17.
 */
@Service
@PreAuthorize("hasRole('ROLE_USER')")
public class EmployeeServiceImpl  implements EmployeeService{

    @Autowired
    @Qualifier("postgresJdbcTemplate")
    private JdbcTemplate postgresTemplate;

    @Override
    public List<Employee> getAll() {
        // TODO Auto-generated method stub

        String SQL = "select * from employee";
        List <Employee> emps = postgresTemplate.query(SQL,
                new EmployeeMapper());
        return emps;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Employee employee) {

        postgresTemplate.update("INSERT INTO employee(id, fname, lname) VALUES(?,?,?)", employee.getId(), employee.getFname(),employee.getLname());

    }
}
