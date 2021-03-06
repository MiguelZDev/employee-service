package com.forceclose.employee.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forceclose.employee.service.model.Employee;
import com.forceclose.employee.service.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clean() {
        reset(employeeService);
    }

    @Test
    @DisplayName("Test Consulta Empleado - Ok")
    void getAll_ok() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("jperez@gmail.com");
        employee.setFirstName("Juan");
        employee.setLastName("Perez");
        List<Employee> employees = List.of(employee);

        when(employeeService.findAll()).thenReturn(employees);

        this.mockMvc.perform(get("/api/employees")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Juan"))
                .andExpect(jsonPath("$[0].lastName").value("Perez"))
                .andExpect(jsonPath("$[0].email").value("jperez@gmail.com"));
    }

    @Test
    @DisplayName("Test Consulta Empleado - Lista Vacía")
    void getAll_empty() throws Exception {
        List<Employee> employees = Collections.emptyList();

        when(employeeService.findAll()).thenReturn(employees);

        this.mockMvc.perform(get("/api/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    void findById_ok() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("jperez@gmail.com");
        employee.setFirstName("Juan");
        employee.setLastName("Perez");
        employee.setId(1);
        when(employeeService.findById(Long.valueOf(1))).thenReturn(Optional.of(employee));

        this.mockMvc.perform(get("/api/employees/{id}", 1)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void register_ok() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("jperez@gmail.com");
        employee.setFirstName("Juan");
        employee.setLastName("Perez");
        String json = new ObjectMapper().writeValueAsString(employee);

        Employee employeeCreated = new Employee();
        employeeCreated.setEmail("jperez@gmail.com");
        employeeCreated.setFirstName("Juan");
        employeeCreated.setLastName("Perez");
        employeeCreated.setId(1);
        when(employeeService.register(employee)).thenReturn(employeeCreated);

        this.mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Juan"))
                .andExpect(jsonPath("$.lastName").value("Perez"))
                .andExpect(jsonPath("$.email").value("jperez@gmail.com"));

    }
}