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
            This seems... fishy ><^>
        </sec:authorize>
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">

            <h2>Create a user or admin account</h2>

<%--            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="user">--%>
            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="createUserDTO">
                <form:hidden path="id"/>
<%--                <form:hidden path="enabled"/>--%>
<%--                <form:hidden path="role"/>--%>
                <form:hidden path="apiKey"/>

                <b>Username</b> <span title="<spring:message code="username.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="text" path="username"/><br>
                <form:errors path="username"/><br>

                <b>Email</b> <span title="<spring:message code="email.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="email" path="email"/><br>
                <form:errors path="email"/><br>

                <b>Password</b> <span title="<spring:message code="password.pattern"/>">&#9432;</span><br>
                <form:password name="password" path="password"/><br>
                <b>Confirm Password</b><br>
                <form:password name="passwordConfirmation" path="passwordConfirmation"/><br>
                <form:errors path="password"/><br>

                <b>Enabled</b> <span title="<spring:message code="enabled.options"/>">&#9432;</span><br>
                <form:select path="enabled">
                    <form:option value="0"> --SELECT--</form:option>
                    <form:options items="${enabledOptions}"></form:options>
                </form:select><br><br>

                <b>Role</b> <span title="<spring:message code="role.options"/>">&#9432;</span><br>
                <form:select path="role">
                    <form:option value="0"> --SELECT--</form:option>
                    <form:options items="${roleList}"></form:options>
                </form:select><br><br>

                <b>Api Key</b><br>
                <p>Generated api key: ${createUserDTO.apiKey}</p>

                <input type="submit">
            </form:form>
            <br>
            <a class="btn btn-warning btn-lg" href="/" role="button">Homepage</a><br>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
