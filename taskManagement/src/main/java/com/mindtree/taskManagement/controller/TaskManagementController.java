package com.mindtree.taskManagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.taskManagement.entity.Employee;
import com.mindtree.taskManagement.entity.Task;

@Controller
@RequestMapping("/task")
public class TaskManagementController {

	@Autowired
	private com.mindtree.taskManagement.service.TaskManagementService service;

	Logger logger = LoggerFactory.getLogger(TaskManagementController.class);

	@RequestMapping(value = "/assigner")
	public ModelAndView assignTask() {
		logger.info("In TaskManagement inside the assignTask method");
		ModelAndView assignTask = new ModelAndView("taskAssigner");
		assignTask.addObject("listProject", service.getProjectList());
		return assignTask;
	}

	@RequestMapping(value = "/preview")
	public ModelAndView taskView() {
		logger.info("In TaskManagement inside the taskView method");
		ModelAndView viewTask = new ModelAndView("taskPreview");
		viewTask.addObject("listProject", service.getProjectList());
		return viewTask;
	}

	@RequestMapping(value = "/assignment", method = RequestMethod.POST)
	public ModelAndView getTaskDetail(@RequestParam("description") String description,
			@RequestParam("startDate") String fromDate, @RequestParam("endDate") String toDate,
			@RequestParam("project") int pid, @RequestParam("employee") List<String> employees) {
		logger.info("In TaskManagement inside the getTaskDetails method");
		Task task = new Task();
		task.setTaskDescription(description);
		task.setStartDateOfTask(fromDate);
		task.setEndDateOfTask(toDate);
		service.saveTaskDetails(task, pid, employees);
		ModelAndView mv = new ModelAndView("../index");
		mv.addObject("msg", "Details saved Successfully");
		return mv;

	}

	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getProjectId(@PathVariable int projectId) {
		logger.info("In TaskManagement inside the getProjectId method");
		return service.getEmployee(projectId);
	}

	@RequestMapping(value = "/getTaskLists/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Task> getTaskdetails(@PathVariable int projectId) {
		logger.info("In TaskManagement inside the getTaskDetails method");
		if (projectId == 0) {
			return service.getTaskList();
		} else if (projectId > 0) {
			return service.getTaskList(projectId);
		} else {

			return null;
		}
	}

}
