<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
	
<h2>Update Your Info</h2>

<c:url var="UPDATE_CLIENT_INFO_URL" value="/updateClientInfo"></c:url>


<form:form id="details" action="${UPDATE_CLIENT_INFO_URL}" method="post"
	commandName="finalUser">
	<table>


		<tr>
			<td>Name:</td>
			<td><form:input name="name" path="name" type="text" /></td>
			<td><form:errors path="name" cssClass="error" /></td>
		</tr>

		<tr>
			<td>Personal Code:</td>
			<td><form:input name="personalCode" path="personalCode"
					type="text" /></td>
			<td><form:errors path="personalCode" cssClass="error" /></td>
		</tr>

		<tr>
			<td>Address:</td>
			<td><form:input name="address" path="address" type="text" /></td>
			<td><form:errors path="address" cssClass="error" /></td>
		</tr>

		<tr>
			<td><form:input name="username" path="username" type="hidden" /></td>
			<td><form:input name="password" path="password" type="hidden" /></td>
			<td><form:input name="authority" path="authority" type="hidden" /></td>
			<td><form:input name="enabled" path="enabled" type="hidden" /></td>
		</tr>


		<tr>
			<td><input value="Update Info" type="submit" /></td>
		</tr>

	</table>
</form:form>


<sec:authorize access="hasRole('ROLE_ADMIN')">

	<p>
		<a href=<c:url value="/deleteCustomerProfile?username=${finalUser.username}"/>>Delete
			Customer Profile</a>
	</p>

	<p>
		<a
			href=<c:url value="/generateTransactionReport?username=${finalUser.username}"/>>Generate
			Transaction Report</a>
	</p>
</sec:authorize>