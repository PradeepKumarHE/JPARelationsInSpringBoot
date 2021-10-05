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

import com.pradeep.jparelation.entity.InstructorDetail;
import com.pradeep.jparelation.exceptions.ResourceNotFoundException;
import com.pradeep.jparelation.repository.InstructorDetailsRepository;

@RequestMapping("/api/instructor/details")
@RestController
public class InstructorDetailsController {

	@Autowired
	InstructorDetailsRepository instructorDetailsRepository;
	
	
	@GetMapping
    public List <InstructorDetail> getInstructors() {
        return instructorDetailsRepository.findAll();
    }
	
	@GetMapping("/{id}") 
	public ResponseEntity<InstructorDetail> getInstructorDetailsById(@PathVariable(value = "id") Long instructorDetailsId) throws ResourceNotFoundException {
		InstructorDetail user = instructorDetailsRepository.findById(instructorDetailsId).orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorDetailsId));
		return ResponseEntity.ok().body(user);
	}
	
	 @PutMapping("{id}")
	    public ResponseEntity <InstructorDetail> updateInstructor(@PathVariable(value = "id") Long instructorDetailsId,@RequestBody InstructorDetail updatedInstructorDetailsInfo) throws ResourceNotFoundException {
		 InstructorDetail existingInstructorDetail = instructorDetailsRepository.findById(instructorDetailsId).orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorDetailsId));
	        BeanUtils.copyProperties(updatedInstructorDetailsInfo,existingInstructorDetail,"id");
	        System.out.println(updatedInstructorDetailsInfo.getYoutubeChannel());
	        final InstructorDetail updatedInstructorDetails = instructorDetailsRepository.save(existingInstructorDetail);
	        return ResponseEntity.ok(updatedInstructorDetails);
	    }

	    @DeleteMapping("{id}")
	    public ResponseEntity<String>  deleteInstructor(@PathVariable(value = "id") Long instructorDetailsId) throws ResourceNotFoundException {
	    	InstructorDetail instructorDetail = instructorDetailsRepository.findById(instructorDetailsId).orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorDetailsId));
	    	instructorDetailsRepository.delete(instructorDetail);
	        return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
	    }
}
