<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Manage Users Accounts
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            <h2>Create User/ Admin Account</h2>

            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="user">
                <form:hidden path="id"/>

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

                Enabled <br>
                <form:select path="enabled">
                    <form:option value="0" label="--Select Status--" disabled="true"/>
                    <form:option value="1">1</form:option>
                    <form:option value="2">2</form:option>
                </form:select><br><br>

                Roles <br>
                <form:select path="roles">
                    <form:option value="0" label="--Select Roles--" disabled="true"/>
                    <form:options items="${roleList}"/>
                </form:select><br><br>

                Api Key <br>
                <form:input type="text" path="apiKey"/><br><br>

                <input type="submit">
            </form:form>

            <a class="btn btn-warning btn-lg" href="/" role="button">Homepage</a><br>


        </div>
    </div>
</div>
</body>
</html>
