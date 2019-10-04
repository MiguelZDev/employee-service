package com.forceclose.employee.service.controller;

import com.forceclose.employee.service.model.Employee;
import com.forceclose.employee.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public HttpEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.findAll();
        if(employees == null || employees.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee register(@RequestBody Employee employee) {
        return employeeService.register(employee);
    }


}
