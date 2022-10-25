<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>내가 만드는 Security Login Form</h1>
	<c:url value="j_spring_security_check" var="loginUrl"/>
	<h5>loginUrl : ${loginUrl }</h5>	
	
	<form action="${loginUrl }" method="post">
		<c:if test="${param.error != null }">
		<p>
			Login Error! <br/>
			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != NULL }">
				message : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message }"/>
			</c:if>
		</p>
		</c:if>
		ID : <input type="text" name="j_username"><br/><!-- name의 값은 springSecurity으로 보내져 처리되므로 이름고정 -->
		PW : <input type="text" name="j_password"><br/>
		<input type="submit" value="LOGIN">
	</form>
</body>
</html>