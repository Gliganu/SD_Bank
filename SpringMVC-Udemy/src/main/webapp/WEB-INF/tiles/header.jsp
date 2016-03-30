
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<p>



<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a class="title" href= <c:url value="/"/> > Bank App ( Admin ) </a> <br/> <br/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a class="title" href= <c:url value="/"/> > Bank App </a> <br/> <br/>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
	<a class="title" href= <c:url value="/"/> > Bank App </a> <br/> <br/>
</sec:authorize>



<sec:authorize access="!isAuthenticated()"> 
<a class="login" href= <c:url value="/login"/> >Click here to log in</a> <br/> <br/>
</sec:authorize>


<sec:authorize access="isAuthenticated()"> 
<a class="login" href= <c:url value="j_spring_security_logout"/> >Click here to log out</a> <br/> <br/>
</sec:authorize>

</p>

