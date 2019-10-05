package com.forceclose.employee.service.service;

import com.forceclose.employee.service.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Employee register(Employee employee);

    Optional<Employee> findById(Long employeeId);

    List<Employee> findByName(String name);
}
