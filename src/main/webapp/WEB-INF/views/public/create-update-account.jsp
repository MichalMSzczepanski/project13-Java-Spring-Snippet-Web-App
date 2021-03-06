<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <sec:authorize access="!isAuthenticated()">
            Create Account
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
            Update account
        </sec:authorize>
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            <sec:authorize access="!isAuthenticated()">
                <h2>Create Account</h2><br>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
                <h2>Update account</h2><br>
            </sec:authorize>

            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="user">
                <form:hidden path="id"/>
                <form:hidden path="enabled"/>
                <form:hidden path="roles"/>
                <form:hidden path="apiKey"/>

                Username <span title="<spring:message code="username.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="text" path="username"/><br>
                <form:errors path="username"/><br>

                Email <span title="<spring:message code="email.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="email" path="email"/><br>
                <form:errors path="email"/><br>

                password <span title="<spring:message code="password.pattern"/>">&#9432;</span><br>
                <input type="password" name="password" path="password"/><br>

                confirm password<br>
                <input type="password" name="passwordConfirmation"/><br>
                <c:if test="${not empty passwordMismatch}">
                        <spring:message code="passwords.dont.match"/>
                </c:if>
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
