<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>
hi ${currentUserName}!<br>

user snippet page available to users only

<br>
<a href="/user/update-user-details">update user details</a>
<br>
<a href="/user/update-user-password">update user password</a>
<br>
<a href="/user/delete">delete account</a>
<br>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj usera">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
