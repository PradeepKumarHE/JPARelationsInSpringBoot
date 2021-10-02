package com.pradeep.jparelation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.jparelation.domain.Instructor;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}