<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css"
	href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add New Task</title>
</head>
<body>
	<script>
		$(function(){
			$('input[name=dateUpdated]').datepicker();
		})
	</script>
	
	<form method="POST" action='taskController' name="frmAddTask">
        Task ID : <input type="text" readonly="readonly" name="id"
            value="<c:out value="${task.id}" />" /> <br /> 
        Name : <input
            type="text" name="name"
            value="<c:out value="${task.name}" />" /> <br /> 
        Description : <input
            type="text" name="description"
            value="<c:out value="${task.description}" />" /> <br /> 
        Date Created (dd/mm/yyyy) : <input
            type="text" name="dateCreated"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${task.dateCreated}" />" /> <br /> 
        Date Updated (dd/mm/yyyy) : <input type="text" name="dateUpdated"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${task.dateUpdated}" />" /> <br />
            <input type="submit" value="Submit" />
    </form>
</body>
</html>