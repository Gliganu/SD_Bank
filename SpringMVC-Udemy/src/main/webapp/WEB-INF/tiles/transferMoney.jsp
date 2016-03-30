<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>




<h2>Transfer money</h2>



<c:url var="TRANSFER_MONEY_URL" value="/transferMoney"></c:url>



<form:form id="details" action="${TRANSFER_MONEY_URL}" method="post"
	commandName="moneyTransfer">
	<table>
		<tr>
			<td>From Account Id:</td>

			<!-- <td><form:input name="fromAccountId" path="fromAccountId"
					type="text" /></td>
			 -->

			<td><form:select name="fromAccountId" path="fromAccountId">
					<form:options items="${userAccountsIds}" />
				</form:select></td>
				
			<td><form:errors path="fromAccountId" cssClass="error" /></td>

		</tr>
		<tr>
			<td>To Account Id:</td>
			<td><form:input name="toAccountId" path="toAccountId"
					type="text" /></td>
			<td><form:errors path="toAccountId" cssClass="error" /></td>

		</tr>

		<tr>
			<td>Sum:</td>
			<td><form:input name="sum" path="sum" type="text" /></td>
			<td><form:errors path="sum" cssClass="error" /></td>

		</tr>

		<tr>
			<td><input value="Make Transfer" type="submit" /></td>
		</tr>
	</table>

</form:form>

