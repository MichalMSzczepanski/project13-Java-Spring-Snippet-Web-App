<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
admin panel available only to admins

<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj admina">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
