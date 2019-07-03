package com.mindtree.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.mindtree.taskManagement.controller.TaskManagementController;
import com.mindtree.taskManagement.entity.Project;
import com.mindtree.taskManagement.service.TaskManagementService;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

	private MockMvc mockmvc;

	@Mock
	@Autowired
	private TaskManagementService taskServiceImpl;
	
	 @Mock
	 View mockView;
	 
	 private MediaType contentType = new MediaType("application", "json", Charset.forName("UTF-8"));


	@InjectMocks
	private TaskManagementController taskController;

	@Before
	public void setup() {

		mockmvc = MockMvcBuilders.standaloneSetup(taskController).setSingleView(mockView).build();
		List<String> emp = new ArrayList<String>();
	}

	@Test
	public void getProjectIdTest() throws Exception {
		
		List<Project> projectList =Arrays.asList(new Project(1,"MOSIP"),new Project(2,"HBS"), new Project(3,"CAPE"));

		when(taskServiceImpl.getProjectList()).thenReturn(projectList);
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/task/project/{pid}",1)
				).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(content().
				  contentType(contentType));
		         
	}
	
	@Test
	public void assignTaskTest() throws Exception {
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/task/assigner")
				).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void taskViewTest() throws Exception {
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/task/preview")
				).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getTaskDetailsTest() throws Exception {
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/task/getTaskLists/{pid}",10001)
				).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getNullTaskTest() throws Exception {
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/task/getTaskLists/{pid}",0)
				).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void taskAssignmentTest() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.post("/task/assignment").param("description", "push Notification")
				.param("startDate", "12-10-2018").param("endDate", "12-10-2018").param("project", "1001")
				.param("employee", "null")).andExpect(MockMvcResultMatchers.status().isOk());
	}
}