package com.forceclose.employee.service.service;

import com.forceclose.employee.service.model.Employee;
import com.forceclose.employee.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee register(Employee employee) {
        return employeeRepository.save(employee);
    }
}
