package com.company.service;

import com.company.employee.Employee;
import java.util.List;
/**
 * Created by suneetha on 6/12/17.
 */
public interface EmployeeService {
    List<Employee> getAll();

    void save(Employee employee);
}
