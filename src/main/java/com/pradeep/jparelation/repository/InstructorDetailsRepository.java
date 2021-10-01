package com.pradeep.jparelation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.jparelation.entity.InstructorDetail;


@Repository
public interface InstructorDetailsRepository extends JpaRepository<InstructorDetail,Long>{

}
