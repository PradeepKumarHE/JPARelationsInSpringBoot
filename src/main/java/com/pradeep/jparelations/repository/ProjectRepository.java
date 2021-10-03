package com.pradeep.jparelations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.jparelations.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
