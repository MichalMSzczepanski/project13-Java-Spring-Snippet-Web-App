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
<%@include file="../nav.jsp" %>
<div class="container my-5">
    <%--    <div class="bg-light text-dark d-flex flex-column justify-content-center align-items-center">--%>
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-4">
                <spring:message code="home.welcome"/>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.username"/>!
                </sec:authorize>
            </h1>
            <p class="lead">Glad to have you here. Snippety time?</p>
            <hr class="my-4">
            <sec:authorize access="!isAuthenticated()">
                First time here? Create an account!
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                What will you create today?
            </sec:authorize>
            <br><br>
            <sec:authorize access="!isAuthenticated()">
                <a class="btn btn-primary btn-lg" href="/create-account" role="button">Create Account</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
                <a class="btn btn-primary btn-lg" href="/admin/dashboard" role="button">Admin Dashboard</a>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
                <a class="btn btn-primary btn-lg" href="/user/dashboard" role="button">Dashboard</a>
            </sec:authorize>
        </div>
    </div>
    <%--    </div>--%>
    <div class="container">
        <div class="row justify-content-start">
            <div class="col-2 border border-dark">
                dropdown for snippet lanugage choice?
            </div>
            <div class="col-2 border border-dark">
                snippet search bar - ajax?
            </div>
            <div class="col-8 border border-dark">
                Title of snippet
            </div>
        </div>
        <div class="row justify-content-start">
            <div class="col-2 border border-dark">
                folder list based on snippet language choice
            </div>
            <div class="col-2 border border-dark">
                Mid column with list of snippets in language category 15%
            </div>
            <div class="col-8 border border-dark">
                "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam
                rem aperiam, eaque ipsa quae ab<br> illo inventore veritatis et quasi architecto beatae vitae dicta sunt
                explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit,<br> sed quia
                consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui
                dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius<br> modi
                tempora incidunt ut labore et dolore magnam aliquam <br>quaerat voluptatem. Ut enim ad minima veniam,
                quis nostrum exercitationem ullam corporis suscipit laboriosam,<br> nisi ut aliquid ex ea commodi
                consequatur? Quis autem vel eum iure reprehenderit<br> qui in ea voluptate velit esse quam nihil
                molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
