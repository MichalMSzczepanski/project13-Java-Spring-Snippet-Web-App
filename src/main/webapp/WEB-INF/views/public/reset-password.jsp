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
            <h2>Input your email to reset your password</h2>
            <form class="p-3 w-75 border border-dark" method="post">

                <b>Your email</b><br>
                <input name="email"/><br>

                We'll send an email with a link that'll allow you to reset your password<br>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <input type="submit">

                <c:if test="${not empty emailNotRegistered}"><p class="d-block mt-2 alert alert-danger">${emailNotRegistered}</p></c:if>

            </form>



            <a class="btn btn-warning btn-lg" href="/" role="button">Homepage</a><br>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
