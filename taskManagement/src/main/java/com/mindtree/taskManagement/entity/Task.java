package com.mindtree.taskManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

	/*
	 * The task Id
	 */
	@Id
	@GeneratedValue
	private int taskId;

	/*
	 * Task description
	 */
	@Column(length=20)
	private String taskDescription;

	/*
	 * Start date of task
	 */
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="start_Date_Of_Task")
	private String startDateOfTask;

	/*
	 * End date of task
	 */
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="end_Date_Of_Task")
	private String endDateOfTask;

	/*
	 * Start date of task
	 */
	@ManyToOne
	@JoinColumn(name = "projectId")
	@JsonManagedReference
	private Project project;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinTable(name = "Task_Employee", joinColumns = @JoinColumn(name = "TaskId"), inverseJoinColumns = @JoinColumn(name = "empId"))
	private List<Employee> employee = new ArrayList<Employee>();

	
}
