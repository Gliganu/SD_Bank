<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<p>
	<h3>${message}</h3>
</p>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href=<c:url value="/viewAllCustomers "/>>View All Customers (Admin) </a>
	</p>

</sec:authorize>


<sec:authorize access="isAuthenticated()">

<p>
	<a href=<c:url value="/updateClientInfo "/>>Update Client Information</a>
</p>

<p>
	<a href=<c:url value="/updateAccountsInfo "/>>Update Account Information</a>
</p>

<p>
	<a href=<c:url value="/viewBills "/>>Show Utility Bills</a>
</p>


<p>
	<a href=<c:url value="/transferMoney "/>>Transfer Money</a>
</p>

</sec:authorize>

<sec:authorize access="!isAuthenticated()">
	
	<h2>Welcome !</h2>

</sec:authorize>




