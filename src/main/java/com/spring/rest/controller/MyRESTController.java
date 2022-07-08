package com.spring.rest.controller;


import com.spring.rest.entity.Employee;
import com.spring.rest.exeption_handling.EmployeeIncorrectData;
import com.spring.rest.exeption_handling.NoSuchEmployeeExeption;
import com.spring.rest.service.EmployeeService;
import com.sun.rmi.rmid.ExecPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee showEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if(employee == null) {
            throw new NoSuchEmployeeExeption("There is no employee with ID = " + id +
                " in Database");
        }
        return employee;
    }


    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);

        return employee;

    }



    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);

        return employee;

    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        employeeService.deleteEmployee(id);

        if(employee == null) {
            throw new NoSuchEmployeeExeption("There is no employee with ID = " + id +
                    " in Database");
        }
        return "Employee " + id + " was deleted";
    }



}
