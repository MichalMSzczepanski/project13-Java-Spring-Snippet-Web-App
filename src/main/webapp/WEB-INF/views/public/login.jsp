<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>GENERAL LOGIN PAGE</h2>
<form method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input class="btn btn-primary" type="submit" value="Sign In"/></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<c:if test="${not empty loginError}">
    <spring:message code="login.error"/><br>
</c:if>
<a href="/create-account" class="btn btn-primary btn-lg">create account</a>
<a href="/"  class="btn btn-warning btn-lg">homepage</a>
</body>
</html>
