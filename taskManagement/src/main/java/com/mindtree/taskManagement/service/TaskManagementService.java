package com.mindtree.taskManagement.service;

import java.util.List;

import com.mindtree.taskManagement.entity.Employee;
import com.mindtree.taskManagement.entity.Project;
import com.mindtree.taskManagement.entity.Task;

/**
 * @author Kishan Rathore
 * 
 * This is a service interface of task management pproject. 
 *
 */
public interface TaskManagementService {

	/**
	 * @return the list of project available.
	 */
	List<Project> getProjectList();

	/**
	 * @param poject id.
	 * @return get the employee. 
	 */
	List<Employee> getEmployee(int projectId);

	/**
	 * @param task
	 * @param pid
	 * @param emp
	 * 
	 * This method save the task details in data base.
	 */
	void saveTaskDetails(Task task, int projectId, List<String> employee);

	/**
	 * @return list of task available.
	 */
	List<Task> getTaskList();

	/**
	 * @param project Id
	 * @return List of task based on project.
	 */
	List<Task> getTaskList(int projectId);

}
