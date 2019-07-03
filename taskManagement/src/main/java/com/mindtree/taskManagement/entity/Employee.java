package com.mindtree.taskManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	/*
	 * The employeeId
	 */
	@Id
	private String empId;

	/*
	 * Employee Name
	 */
	@Column(nullable = false,length=20)
	private String empName;

	/*
	 * project Id
	 */
	@ManyToOne
	@JoinColumn(name = "projectId")
	@JsonManagedReference
	private Project project;

	
	@ManyToMany(mappedBy = "employee")
	@JsonBackReference
	List<Task> task = new ArrayList<Task>();

	

}
