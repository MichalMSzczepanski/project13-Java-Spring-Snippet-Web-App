<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Create Account
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            <h2>Create Account</h2><br>
            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="createUserDTO">
                <form:hidden path="id"/>
                <form:hidden path="enabled"/>
                <form:hidden path="role"/>
                <form:hidden path="apiKey"/>

                Username <span title="<spring:message code="username.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="text" path="username"/><br>
                <form:errors path="username"/><br>

                Email <span title="<spring:message code="email.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="email" path="email"/><br>
                <form:errors path="email"/><br>

                password <span title="<spring:message code="password.pattern"/>">&#9432;</span><br>
                <form:password name="password" path="password"/><br><br>
                confirm password<br>
                <form:password name="passwordConfirmation" path="passwordConfirmation"/><br>
                <form:errors path="password"/><br>

                <input type="submit">
            </form:form>
            <div class="d-flex flex-row">
                <a class="btn btn-secondary btn-lg mx-2" href="/login" role="button">Login</a>
                <a href="/" class="btn btn-warning btn-lg mx-2">Homepage</a>
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
