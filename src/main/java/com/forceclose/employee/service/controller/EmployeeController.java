package com.forceclose.employee.service.controller;

import com.forceclose.employee.service.exception.BadRequestException;
import com.forceclose.employee.service.exception.ResourceNotFoundException;
import com.forceclose.employee.service.model.Employee;
import com.forceclose.employee.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public HttpEntity<List<Employee>> findAll(@RequestParam(value = "name", required = false) String name) {
        List<Employee> employees;
        if(StringUtils.isEmpty(name))
            employees = employeeService.findAll();
        else
            employees = employeeService.findByName(name);


        if(employees == null || employees.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<Employee> findById(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee register(@RequestBody Employee employee) {
        return employeeService.register(employee);
    }




}
