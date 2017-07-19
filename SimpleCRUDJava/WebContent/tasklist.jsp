<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRUD System(Tasks List)</title>
</head>
<body>
	<table border=1>
        <thead>
            <tr>
                <th>Task Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Date Created</th>
                <th>Date Updated</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tasks}" var="task">
                <tr>
                    <td><c:out value="${task.id}" /></td>
                    <td><c:out value="${task.name}" /></td>
                    <td><c:out value="${task.description}" /></td>
                    <td><fmt:formatDate pattern="dd-MMM-yyyy" value="${task.dateCreated}" /></td>
                    <td><fmt:formatDate pattern="dd-MMM-yyyy" value="${task.dateUpdated}" /></td>
                    <td><a href="taskController?action=edit&id=<c:out value="${task.id}"/>">Update</a></td>
                    <td><a href="taskController?action=delete&id=<c:out value="${task.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="taskController?action=insert">Add New Task</a></p>
</body>
</html>