



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>All transactions</h2>

<table border=1 width=100%>
	<tr>
		<th>Id</th>
		<th>From</th>
		<th>To</th>
		<th>Date</th>
		<th>Amount</th>
	</tr>

	<c:forEach var="transaction" items="${allTransactions}">
		<tr>
			<td>${transaction.id}</td>
			<td>${transaction.fromUser.username}</td>
			<td>${transaction.toUser.username}</td>
			<td>${transaction.date}</td>
			<td>${transaction.amount} $</td>
		</tr>
	</c:forEach>
</table>




