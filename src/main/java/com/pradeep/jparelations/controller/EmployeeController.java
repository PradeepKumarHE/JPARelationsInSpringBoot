package com.pradeep.jparelations.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.jparelations.domain.Employee;
import com.pradeep.jparelations.exception.ResourceNotFoundException;
import com.pradeep.jparelations.repository.EmployeeRepository;

@RequestMapping("/api/employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping
    public List <Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity <Employee> getEmployeeById(
        @PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
    	Employee user = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + employeeId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity <Employee> updateEmployee(@PathVariable(value = "id") Long instructorId,
         @RequestBody Employee employee) throws ResourceNotFoundException {
    	
    	Employee user = employeeRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        user.setFirstName(employee.getFirstName());
        user.setLastName(employee.getLastName());
        final Employee updatedUser = employeeRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public Map < String, Boolean > deleteEmployeer(
        @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
    	Employee instructor = employeeRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));

        employeeRepository.delete(instructor);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
