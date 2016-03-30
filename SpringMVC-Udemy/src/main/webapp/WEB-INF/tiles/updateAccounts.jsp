



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Update Accounts</h2>

<table class="allCenter" border=1 width=100%>
	<tr>
		<th>ID Number</th>
		<th>Creation Date</th>
		<th>Money Amount</th>
		<th>Edit</th>
	</tr>

	<c:forEach var="account" items="${userAccounts}" varStatus="loop">
		<tr>
			<td>${account.idNumber}</td>
			<td>${account.creationDate}</td>
			<td>${account.moneyAmount}</td>
			<td><a href=<c:url value="/editAccount?id=${account.idNumber}"/>>Edit</a></td>
		</tr>
	</c:forEach>
</table>

<p>
	<a
		href=<c:url value="/createNewAccount"/>>Create New Account</a>
</p>