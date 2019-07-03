package com.mindtree.taskManagement.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.taskManagement.dao.TaskManagementDao;
import com.mindtree.taskManagement.entity.Employee;
import com.mindtree.taskManagement.entity.Project;
import com.mindtree.taskManagement.entity.Task;
import com.mindtree.taskManagement.errorUtils.ErrorCodes;
import com.mindtree.taskManagement.errorUtils.ErrorMessages;
import com.mindtree.taskManagement.exception.TaskManagerException;
import com.mindtree.taskManagement.service.TaskManagementService;

/**
 * @author Kishan Rathore
 * 
 *         This is a service implementation class of task management project.
 *
 */
@Service
@Transactional
public class TaskManagementServiceImpl implements TaskManagementService {
	@Autowired
	private TaskManagementDao taskManagementDao;

	Logger log = LoggerFactory.getLogger(TaskManagementServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.taskManagement.service.TaskManagementService#getProjectList()
	 */
	public List<Project> getProjectList() {
		log.info("Retrieving the list of project");
		try {
			return taskManagementDao.getProjectList();
		} catch (Exception e) {
			log.error("Error while retrieving list of projects");
			throw new TaskManagerException(ErrorCodes.TSK_MNG_001.toString(),
					ErrorMessages.Project_details_are_not_found.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.taskManagement.service.TaskManagementService#getEmployee(int)
	 */
	public List<Employee> getEmployee(int pid) {
		log.info("Retrieving list of employee details");
		try {
			return taskManagementDao.getEmployeeList(pid);

		} catch (Exception e) {
			log.error("Error while retrieving list of employee details");
			throw new TaskManagerException(ErrorCodes.TSK_MNG_002.toString(),
					ErrorMessages.Employee_details_are_not_found.toString());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.taskManagement.service.TaskManagementService#saveTaskDetails(com
	 * .mindtree.taskManagement.entity.Task, int, java.util.List)
	 */
	public void saveTaskDetails(Task task, int pid, List<String> emp) {
		log.info("Save the task details method in service impl");
		if (task.getTaskDescription().length() > 20) {
			throw new TaskManagerException(ErrorCodes.TSK_MNG_005.toString(),
					ErrorMessages.Task_description_max_length_is_20.toString());
		}
		for (Project project : taskManagementDao.getProjectList()) {
			if (project.getProjectId() == pid) {
				task.setProject(project);
			}
		}

		for (int i = 0; i < emp.size(); i++) {
			for (Employee employee : taskManagementDao.getEmployeeList()) {
				if (employee.getEmpId().equals(emp.get(i))) {
					task.getEmployee().add(employee);
				}
			}
		}

		taskManagementDao.saveTask(task);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.taskManagement.service.TaskManagementService#getTaskList()
	 */
	public List<Task> getTaskList() {
		log.info("Retrieving List of task");
		try {
			return taskManagementDao.getTaskList();
		} catch (Exception e) {
			log.error("Error while retrieving list of employee details");
			throw new TaskManagerException(ErrorCodes.TSK_MNG_003.toString(),
					ErrorMessages.Task_details_are_not_found.toString());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.taskManagement.service.TaskManagementService#getTaskList(int)
	 */
	public List<Task> getTaskList(int projectId) {
		log.info("Retrieving list of task with project id");
		try {
			return taskManagementDao.getTaskList(projectId);
		} catch (Exception e) {
			log.error("Error while retrieving list of tasks");
			throw new TaskManagerException(ErrorCodes.TSK_MNG_004.toString(),
					ErrorMessages.Task_are_not_found.toString());
		}

	}

}
