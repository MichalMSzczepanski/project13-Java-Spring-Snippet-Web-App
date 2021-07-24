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
            <form class="p-3 w-75 border border-dark" method="post">
                <div><label> User Name : <input type="text" name="username"/> </label></div>
                <div><label> Password: <input type="password" name="password"/> </label></div>
                <div><input class="btn btn-primary" type="submit" value="Sign In"/></div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <c:if test="${not empty loginError}">
                    <br>
                    <div class="alert alert-danger" role="alert">
                        <spring:message code="login.error"/>
                    </div>
                </c:if>
            </form>
            <div class="d-flex flex-row">
                    <a href="/create-account" class="btn btn-primary btn-lg mx-2">Create Account</a>
                    <a href="/" class="btn btn-warning btn-lg mx-2">Homepage</a>
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
