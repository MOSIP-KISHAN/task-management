package com.mindtree.taskManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
public class Project {

	/*
	 * The ProjectId
	 */
	@Id
	@GeneratedValue
	private int projectId;

	public Project(int projectId, String projectName) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
	}


	/*
	 * Project Name
	 */
	@Column(nullable = false)
	private String projectName;

	/*
	 * Task List 
	 */
	@OneToMany(mappedBy = "project")
	@JsonBackReference
	private List<Task> task = new ArrayList<Task>();


	@OneToMany(mappedBy = "project")
	@JsonBackReference
	private List<Employee> employee = new ArrayList<Employee>();

}
