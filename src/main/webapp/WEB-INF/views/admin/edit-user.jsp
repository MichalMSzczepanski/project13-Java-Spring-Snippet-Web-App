<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Edit User ${createUserDTO.username}
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">

            <h2>Edit user ${createUserDTO.username}</h2>

            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="createUserDTO">
                <form:hidden path="id"/>
                <form:hidden path="apiKey"/>
                <form:hidden path="password"/>
                <form:hidden path="passwordConfirmation"/>
                <form:hidden path="accountKey"/>
                <form:hidden path="accountKeyCreated"/>
                <form:hidden path="accountKeyExpirationDate"/>

                <b>User Id: ${createUserDTO.id}</b><br><br>

                <b>Username</b> <span title="<spring:message code="username.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="text" path="username"/><br>
                <form:errors path="username"/><br>

                <b>Email</b> <span title="<spring:message code="email.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="email" path="email"/><br>
                <form:errors path="email"/><br>

                <b>Enabled</b> <span title="<spring:message code="enabled.options"/>">&#9432;</span><br>
                <form:select path="enabled">
                    <form:options items="${enabledOptions}"></form:options>
                </form:select><br><br>

                <b>Role</b> <span title="<spring:message code="role.options"/>">&#9432;</span><br>
                <form:select path="role">
                    <form:options items="${roleList}"></form:options>
                </form:select><br><br>

                <p><b>Api Key</b>: ${createUserDTO.apiKey}</p>

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
