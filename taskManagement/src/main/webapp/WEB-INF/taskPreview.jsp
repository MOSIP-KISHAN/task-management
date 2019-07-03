<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Viewer</title>
<style type="text/css">
fieldset {
	border-color: darkblue;
	background-color:antiquewhite;
	width: 600px;
	margin-left: 25%;
}
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
table{
width:100%;
}
</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript">
	function changeProject(projectId) {

		console.log(projectId);
		var pid = projectId;
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : '/taskManagement/task/getTaskLists/' + pid,
			dataType : 'json',
			async : true,
			success : function(data) {
				console.log(data);
				
                var task='';
				
				$.each(data,function(key,value){
					console.log(value);
					task+="<strong>Project:</strong>"+value.project.projectName+"<br/><br/>";
					task+="<strong>Task Description:</strong>"+value.taskDescription+"<br/><br/>";
					task+="<strong>Task start date:</strong>"+value.startDateOfTask+"<br/><br/>";
					task+="<strong>Task End date:</strong>"+value.endDateOfTask+"<br/><br/>";
					task+="<table><tr><th>MID</th><th>NAME</th></tr>";
					for(var i=0;i<value.employee.length;i++){
						task+="<tr><td>"+value.employee[i].empId+"</td>";
						task+="<td>"+value.employee[i].empName+"</td></tr>";
						}
					task+="</table><br/><br/>"
				
					
				
					
				});
				
				 $('#task_details').html(task);
				
				
			},
		error : function(data) {
			alert("Task not found");
			console.log(data);
		}
	
		      
		});
		
	
	}
		
</script>

</head>
<body>
	<fieldset>
		<legend>View Task</legend>
		<strong style="margin-left: 150px;">Filter by projects:</strong> <select
			id="project" name="project" onchange="changeProject(this.value)">
			<option value="-1">--Select Project--</option>
			<option value="0">--All Project--</option>
			<c:forEach var="element" items="${listProject}">
				<option value="${element.projectId}"><c:out
						value="${element.projectName}" />
				</option>
			</c:forEach>
		</select>
		<hr/>
		<br/><br/><br/>
		<div style="width:100%;" id="task_details">
		<table 	id="emp_task" style="width:100%;"></table>
		</div>
		
	</fieldset>
</body>
</html>