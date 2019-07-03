package com.mindtree.taskManagement.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.taskManagement.dao.TaskManagementDao;
import com.mindtree.taskManagement.entity.Employee;
import com.mindtree.taskManagement.entity.Project;
import com.mindtree.taskManagement.entity.Task;
import com.mindtree.taskManagement.errorUtils.ErrorCodes;
import com.mindtree.taskManagement.errorUtils.ErrorMessages;
import com.mindtree.taskManagement.exception.TaskManagerException;
import com.mindtree.taskManagement.service.impl.TaskManagementServiceImpl;

@Repository
public class TaskManagementDaoImpl implements TaskManagementDao {
	
	Logger log = LoggerFactory.getLogger(TaskManagementDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Project> getProjectList() {
		log.info("In get project list method of DAO impl");
		List<Project> projectList= sessionFactory.getCurrentSession().createQuery("from Project").list();
		if(projectList==null||projectList.isEmpty()) {
			throw new TaskManagerException(ErrorCodes.TSK_MNG_002.toString(), ErrorMessages.Project_details_are_not_found.toString());
		}
		return projectList;

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeList(int pid) {
		log.info("In get employee list method of DAO impl with pid");

		List<Employee> empl_list= sessionFactory.getCurrentSession().createQuery("from Employee where project=" + pid).list();
		if(empl_list==null||empl_list.isEmpty()) {
			throw new TaskManagerException(ErrorCodes.TSK_MNG_001.toString(), ErrorMessages.Employee_details_are_not_found.toString());
		}
		return empl_list;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeList() {

		log.info("In get project list method of DAO impl");
		List<Employee> empl_list =sessionFactory.getCurrentSession().createQuery("from Employee").list();
		if(empl_list==null||empl_list.isEmpty()) {
			throw new TaskManagerException(ErrorCodes.TSK_MNG_001.toString(), ErrorMessages.Employee_details_are_not_found.toString());
		}
		return empl_list;
	}

	public void saveTask(Task task) {

		log.info("In save task method of DAO impl");
		sessionFactory.getCurrentSession().save(task);
		log.info("In save method of dao impl");
	}

	@SuppressWarnings("unchecked")
	public List<Task> getTaskList() {

		log.info("In get task list method of DAO impl");
		List<Task> taskList= sessionFactory.getCurrentSession().createQuery("from Task").list();
		if(taskList==null||taskList.isEmpty()) {
			throw new TaskManagerException(ErrorCodes.TSK_MNG_003.toString(), ErrorMessages.Task_are_not_found.toString());
		}
		return taskList;
	}

	@SuppressWarnings("unchecked")
	public List<Task> getTaskList(int projectId) {

		log.info("In get task list with pid method of DAO impl");
		List<Task> taskList= sessionFactory.getCurrentSession().createQuery("from Task where projectId=" + projectId).list();
		if(taskList==null||taskList.isEmpty()) {
			throw new TaskManagerException(ErrorCodes.TSK_MNG_003.toString(), ErrorMessages.Task_are_not_found.toString());
		}
		return taskList;
		
	}
}
