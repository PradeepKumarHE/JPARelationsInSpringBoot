package com.pradeep.jparelations.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project extends AuditModel {

 private static final long serialVersionUID = 1L;

 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "title")
    private String title;   
    
    @ManyToMany(mappedBy = "projects", cascade = { CascadeType.ALL })
    private Set<Employee> employees = new HashSet<Employee>();
}
