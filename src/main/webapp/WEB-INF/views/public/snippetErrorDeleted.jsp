<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Snippet App
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>

<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            <h2><spring:message code="snippetMissing"/></h2>
<%--            <c:if test="${not empty lastAdminConfirmed}"><h3>Shoots, you tried to delete the last admin! Can't do that, sorry!</h3></c:if>--%>
<%--            <h3><spring:message code="marty.go.back"/></h3>--%>
<%--            <br>--%>
<%--            <a class="btn btn-warning btn-lg" href="/" role="button">Homepage</a><br>--%>
<%--            <sec:authorize access="!isAuthenticated()">--%>
<%--                <a class="btn btn-secondary btn-lg" href="/login" role="button">Login</a>--%>
<%--            </sec:authorize>--%>

        </div>
    </div>
</div>

</body>
</html>