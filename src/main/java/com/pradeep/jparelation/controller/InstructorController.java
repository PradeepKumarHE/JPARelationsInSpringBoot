package com.pradeep.jparelation.controller;

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

import com.pradeep.jparelation.domain.Instructor;
import com.pradeep.jparelation.exception.ResourceNotFoundException;
import com.pradeep.jparelation.repository.InstructorRepository;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;


    @GetMapping
    public List < Instructor > getInstructors() {
        return instructorRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity < Instructor > getInstructorById(
        @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
        Instructor user = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public Instructor createUser(@RequestBody Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @PutMapping("{id}")
    public ResponseEntity < Instructor > updateUser(@PathVariable(value = "id") Long instructorId,
         @RequestBody Instructor userDetails) throws ResourceNotFoundException {
    	
        Instructor user = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        final Instructor updatedUser = instructorRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public Map < String, Boolean > deleteUser(
        @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
        Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));

        instructorRepository.delete(instructor);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}