package com.forceclose.employee.service.service;

import com.forceclose.employee.service.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee register(Employee employee);
}
