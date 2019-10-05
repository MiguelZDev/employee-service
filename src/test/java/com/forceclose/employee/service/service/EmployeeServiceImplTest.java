package com.forceclose.employee.service.service;

import com.forceclose.employee.service.model.Employee;
import com.forceclose.employee.service.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void init() {
        this.employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void findAll() {
        Employee employee = new Employee();
        employee.setEmail("jperez@gmail.com");
        employee.setFirstName("Juan");
        employee.setLastName("Perez");
        List<Employee> employees = List.of(employee);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> response = employeeService.findAll();

        assertThat(response).hasSize(1);
        assertThat(response.get(0))
                .hasFieldOrPropertyWithValue("firstName", "Juan")
                .hasFieldOrPropertyWithValue("lastName", "Perez")
                .hasFieldOrPropertyWithValue("email", "jperez@gmail.com");
    }

    @Test
    void register() {
    }
}