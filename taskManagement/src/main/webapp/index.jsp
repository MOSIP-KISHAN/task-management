<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Task Manager</title>
<style type="text/css">
fieldset{
background-color:antiquewhite;
padding-bottom:initial;
border-color: darkblue;
	width: 100%;
	height: 100%;
	margin: 100%;
}
background-color:antiquewhite;
padding-bottom:initial;
border-color: darkblue;
	width: 100%;
	height: 100%;
	margin: 100%;
a {
	text-decoration: none;
	margin-left: 100px;
	margin-top: 25px;
	display: inline-block;
	
}

</style>
</head>
<body>
	<h1>${msg}</h1>
	<fieldset style="width: 300px; margin: 10% 35%; height: 150px;">
		<legend>Task Management:</legend>
		<a href="/taskManagement/task/assigner">Assign Tasks</a><br /> 
		<a href="/taskManagement/task/preview">Task View</a>
	</fieldset>

</body>
</html>
