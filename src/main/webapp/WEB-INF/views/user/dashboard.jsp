<%@include file="../taglibs.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
user snippet page available to users only

<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj usera">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
