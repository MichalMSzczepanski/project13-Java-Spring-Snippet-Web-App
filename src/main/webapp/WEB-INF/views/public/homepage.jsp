<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Snippet App
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
home page available to all

<h1><spring:message code="home.welcome"/></h1>
<h1><spring:message code="not.so.funny"/></h1>
<h1><spring:message code="this.will.work"/></h1>

<sec:authorize access="!isAuthenticated()">
    <a href="/login">login</a>
    <a href="/create-account">create account</a><br><br>
</sec:authorize>



<sec:authorize access="isAuthenticated()">
<%--    below line throws exception [javax.servlet.jsp.JspException: org.springframework.beans.NotReadablePropertyException: Invalid property 'username' of bean class--%>
    <%--    <p>Zalogowany jako: <sec:authentication property="username"/></p>--%>
    <p>Posiada role: <sec:authentication property="authorities"/></p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    nazwa usera <sec:authentication property="principal.username" />
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
    <a href="/admin/dashboard">go to admin dashboard</a>
</sec:authorize>

<sec:authorize access="hasRole('USER')">
    <a href="/user/dashboard">go to user dashboard</a>
</sec:authorize>

<br>

<sec:authorize access="isAuthenticated()">
    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Wyloguj">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>

<br>


</body>
</html>
