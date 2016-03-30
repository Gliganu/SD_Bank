



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>View All Customers</h2>

<table border=1 width=100%>
	<tr>
		<th>Username</th>
		<th>Name</th>
		<th>Personal Code</th>
		<th>Address</th>
		<th>Authority</th>
		<th>Edit</th>
	</tr>

	<c:forEach var="user" items="${allUsers}">
		<tr>
			<td>${user.username}</td>
			<td>${user.name}</td>
			<td>${user.personalCode}</td>
			<td>${user.address}</td>
			<td>${user.authority}</td>
			<td><a href= <c:url value="/updateClientInfo?username=${user.username}"/>>Edit</a></td>
		</tr>
	</c:forEach>
</table>




