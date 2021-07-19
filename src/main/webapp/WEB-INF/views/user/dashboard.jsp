<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>
hi ${currentUserName}!<br>

user snippet page available to users only

<br>
<a href="/update-user-details">update user details</a>
<br>
<a href="/update-user-password">update user password</a>
<br>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj usera">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
