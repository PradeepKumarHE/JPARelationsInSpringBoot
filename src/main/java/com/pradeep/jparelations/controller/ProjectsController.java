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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.jparelations.domain.Project;
import com.pradeep.jparelations.exception.ResourceNotFoundException;
import com.pradeep.jparelations.repository.ProjectRepository;

@RequestMapping("/api/projects")
@RestController
public class ProjectsController {

    @Autowired
    private ProjectRepository projectRepository;


    @GetMapping
    public List <Project> getEmployees() {
        return projectRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity <Project> getEmployeeById(
        @PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
    	Project project = projectRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + employeeId));
        return ResponseEntity.ok().body(project);
    }

    @PostMapping
    public Project createEmployee(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    

    @DeleteMapping("{id}")
    public Map < String, Boolean > deleteEmployeer(
        @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
    	Project instructor = projectRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));

        projectRepository.delete(instructor);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
