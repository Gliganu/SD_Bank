<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<h2>Edit Account ${account.idNumber}</h2>


<c:url var="UPDATE_ACCOUNT_INFO_URL" value="/editAccount"></c:url>


<form:form id="details" action="${UPDATE_CLIENT_INFO_URL}" method="post"
	commandName="account">
	<table>

		
		<tr>
			<td>Money Amount:</td>
			<td><form:input name="moneyAmount" path="moneyAmount" type="text"/></td>
			<td><form:errors path="moneyAmount" cssClass="error" /></td>
		</tr>
		
		
		<tr>
		<td><form:input name="idNumber" path="idNumber" type="hidden"/></td>
		<td><form:input name="creationDate" path="creationDate" type="hidden"/></td>
		</tr>
		
		
		<tr>
			<td><input value="Update Account Info" type="submit" /></td>
		</tr>
		
	</table>
</form:form>

<p><a href= <c:url value="/deleteAccount?id=${account.idNumber}"/>>Delete Account</a></p>


