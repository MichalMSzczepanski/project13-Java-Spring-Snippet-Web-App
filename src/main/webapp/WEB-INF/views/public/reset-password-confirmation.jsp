<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Login
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            <h2>Reset your password</h2>
            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="userPasswordResetDTO">

                <b>New password</b> <span title="<spring:message code="password.pattern"/>">&#9432;</span><br>
                <form:password name="password" path="password"/><br>
                <b>Confirm password</b><br>
                <form:password name="passwordConfirmation" path="passwordConfirmation"/><br><br>

                <form:errors cssClass="d-block mt-2 alert alert-danger" path="password"/><br>

                <input type="submit">
            </form:form>

            <a class="btn btn-warning btn-lg" href="/" role="button">Homepage</a><br>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
