package com.mindtree.taskManagement.dao;

import java.util.List;

import com.mindtree.taskManagement.entity.Employee;
import com.mindtree.taskManagement.entity.Project;
import com.mindtree.taskManagement.entity.Task;

public interface TaskManagementDao {
	List<Project> getProjectList();

	List<Employee> getEmployeeList(int pid);

	List<Employee> getEmployeeList();

	void saveTask(Task task);

	List<Task> getTaskList();

	List<Task> getTaskList(int projectId);
}
