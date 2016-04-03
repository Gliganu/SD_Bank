



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Update Accounts</h2>

<table class="allCenter" border=1 width=100%>
	<tr>
		<th>ID Number</th>
		<th>Money Amount</th>
		<th>Action</th>
	</tr>

	<c:forEach var="bill" items="${userBills}" varStatus="loop">
		<tr>
			<td>${bill.idNumber}</td>
			<td>${bill.ammountToPay}</td>
			<td><a href=<c:url value="/payBill?id=${bill.idNumber}"/>>Pay</a></td>
		</tr>
	</c:forEach>
</table>
