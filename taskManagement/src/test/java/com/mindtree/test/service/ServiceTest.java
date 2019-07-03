package com.mindtree.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mindtree.taskManagement.dao.TaskManagementDao;
import com.mindtree.taskManagement.entity.Employee;
import com.mindtree.taskManagement.entity.Project;
import com.mindtree.taskManagement.entity.Task;
import com.mindtree.taskManagement.exception.TaskManagerException;
import com.mindtree.taskManagement.service.impl.TaskManagementServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @Mock
	private TaskManagementDao taskManagementDao;

	@InjectMocks
	private TaskManagementServiceImpl taskServiceImpl;
	
	List<Employee> employeeList = new ArrayList<Employee>();
	Task task = new Task(); 
	List<Task> taskList = new ArrayList<Task>();
	
	List<String> empList=new CopyOnWriteArrayList<String>();
	Project project;
	
	Employee employee;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
        employee =new Employee();
     	employee.setEmpId("1");
     	employee.setEmpName("Kishan Singh Rathore");
        project = new Project();
     	project.setProjectId(1);
     	project.setProjectName("MOSIP");
     	employee.setProject(project);
     	employeeList.add(employee);
     	
     	task.setEmployee(employeeList);
     	task.setTaskId(1);
     	task.setProject(project);
     	task.setTaskDescription("Testing");
     	task.setStartDateOfTask("10-10-2018");
     	task.setEndDateOfTask("11-11-2018");
     	
     	taskList.add(task);

     	empList.add("1");
     	
	}
	
    @Test
	public void testGetProjectList() {

		when(taskManagementDao.getProjectList()).thenReturn(projectList());

		assertEquals(1, taskServiceImpl.getProjectList().size());
		Mockito.verify(taskManagementDao).getProjectList();
  
	}
	
    @Test(expected=TaskManagerException.class)
   	public void getProjectListFailureTest() {

   		when(taskManagementDao.getProjectList()).thenThrow(new TaskManagerException(null));
   		taskServiceImpl.getProjectList();
     
   	}
    
	@Test
	public void getEmployeeListSuccessTest() {
  
		when(taskManagementDao.getEmployeeList(Mockito.anyInt())).thenReturn(employeeList);
		assertEquals(1, taskServiceImpl.getEmployee(1).get(0).getProject().getProjectId());
	}
	
	@Test(expected=TaskManagerException.class)
	public void getEmployeeListFailureTest() {
		TaskManagerException ex = new TaskManagerException(null);
		when(taskManagementDao.getEmployeeList(Mockito.anyInt())).thenThrow(ex);
		taskServiceImpl.getEmployee(1).get(0).getProject().getProjectId();
	}
	
	@Test
	public void testGetTaskList() {

		when(taskManagementDao.getTaskList()).thenReturn(taskList);
		when(taskManagementDao.getTaskList(Mockito.anyInt())).thenReturn(taskList);
		assertEquals(1, taskServiceImpl.getTaskList().size());
		assertEquals(1, taskServiceImpl.getTaskList(1).get(0).getProject().getProjectId());
		
	}
	
	@Test(expected=TaskManagerException.class)
	public void getTaskListFailureTest() {

		when(taskManagementDao.getTaskList()).thenThrow(new TaskManagerException(null));
		when(taskManagementDao.getTaskList(Mockito.anyInt())).thenThrow(new TaskManagerException(null));
		assertEquals(1, taskServiceImpl.getTaskList().size());
		assertEquals(1, taskServiceImpl.getTaskList(1).get(0).getProject().getProjectId());
		
	}
	
	@Test(expected=TaskManagerException.class)
	public void getTaskListFailureTest1() {

		when(taskManagementDao.getTaskList(Mockito.anyInt())).thenThrow(new TaskManagerException(null));
		assertEquals(1, taskServiceImpl.getTaskList(1).get(0).getProject().getProjectId());
		
	}


	private List<Project> projectList() {

		List<Project> projectList = new ArrayList<Project>();
		Project project = new Project();
		project.setProjectId(1);
		project.setProjectName("MOSIP");
		projectList.add(project);
		return projectList;
	}
}
