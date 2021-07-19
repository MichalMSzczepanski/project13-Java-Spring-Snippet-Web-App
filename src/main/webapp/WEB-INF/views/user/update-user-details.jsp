<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Update account detials
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <div style="height: 800px;">
        <div class="h-100 p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            Update you account details

            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="userDetails">

                Username <span title="<spring:message code="username.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="text" path="username"/><br>
                <form:errors path="username"/><br>

                Email <span title="<spring:message code="email.not.blank.and.unique"/>">&#9432;</span><br>
                <form:input type="email" path="email"/><br>
                <form:errors path="email"/><br>

                <input type="submit">
            </form:form>

            <a class="btn btn-warning btn-lg" href="/" role="button">Homepage</a><br>

        </div>
    </div>
</div>
</body>
</html>
