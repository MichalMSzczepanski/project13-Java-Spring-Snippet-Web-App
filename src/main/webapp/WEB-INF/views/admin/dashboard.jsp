<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Admin Dashboard
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>

<div class="container">
    <div class="h-50 d-flex flex-column justify-content-center">
        <h3>hi ${currentUser.username}!</h3>
        <p>your Api Key is: ${currentUser.apiKey}</p>
        <p>stuff will happen here</p>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
