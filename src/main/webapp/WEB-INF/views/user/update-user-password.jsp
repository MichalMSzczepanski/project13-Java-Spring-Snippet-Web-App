<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Update user password
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            Update you password

            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="userPasswords">

<%--                <b>password</b> <span title="<spring:message code="password.pattern"/>">&#9432;</span><br>--%>
<%--                <input type="password" name="password" path="password"/><br>--%>
<%--                <form:errors path="password"/><br>--%>

<%--                <b>confirm password</b><br>--%>
<%--                <form: type="password" name="passwordConfirmation"/><br>--%>
<%--                <c:if test="${not empty passwordMismatch}">--%>
<%--                        <spring:message code="passwords.dont.match"/>--%>
<%--                </c:if>--%>
<%--                <form:errors path="password"/><br>--%>

                <b>password</b> <span title="<spring:message code="password.pattern"/>">&#9432;</span><br>
                <form:password name="password" path="password"/><br>
                <b>confirm password</b><br>
                <form:password name="passwordConfirmation" path="passwordConfirmation"/><br>
                <form:errors path="password"/><br>

                <input type="submit">
            </form:form>

            <a class="btn btn-warning btn-lg" href="/" role="button">Homepage</a><br>
            <sec:authorize access="!isAuthenticated()">
                <a class="btn btn-secondary btn-lg" href="/login" role="button">Login</a>
            </sec:authorize>

        </div>
    </div>
</div>
</body>
</html>
