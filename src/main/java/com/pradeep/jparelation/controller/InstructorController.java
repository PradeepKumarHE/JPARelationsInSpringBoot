package com.pradeep.jparelation.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.jparelation.entity.Instructor;
import com.pradeep.jparelation.entity.InstructorDetail;
import com.pradeep.jparelation.exceptions.ResourceNotFoundException;
import com.pradeep.jparelation.repository.InstructorDetailsRepository;
import com.pradeep.jparelation.repository.InstructorRepository;

@RequestMapping("/api/instructors")
@RestController
public class InstructorController {

	@Autowired
	InstructorRepository instructorRepository;
   
	
	@PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorRepository.save(instructor);
    }
	
	@GetMapping
    public List <Instructor> getInstructors() {
        return instructorRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity <Instructor> getInstructorById(@PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        return ResponseEntity.ok().body(instructor);
    }

    @PutMapping("{id}")
    public ResponseEntity <Instructor> updateInstructor(@PathVariable(value = "id") Long instructorId,@RequestBody Instructor updatedInstructorInfo) throws ResourceNotFoundException {
        Instructor existingInstructor = instructorRepository.findById(instructorId).orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        BeanUtils.copyProperties(updatedInstructorInfo,existingInstructor,"id");
        final Instructor updatedInstructor = instructorRepository.save(existingInstructor);
        return ResponseEntity.ok(updatedInstructor);
    }

    @DeleteMapping("{id}") 
    public ResponseEntity<String>  deleteInstructor(@PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException {
    	Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        instructorRepository.delete(instructor);
        return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
    }
}