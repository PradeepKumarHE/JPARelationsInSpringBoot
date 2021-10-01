package com.pradeep.jparelation.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
   
   /* @OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
    @JsonManagedReference 
    private InstructorDetail instructorDetail; */

    @OneToOne(cascade=CascadeType.ALL,mappedBy="instructor")
    @JsonManagedReference
    private InstructorDetail instructorDetail;     
}
