package com.pradeep.jparelation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.jparelation.domain.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
}
