<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Dashboard
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>

<div class="container">
    <div class="h-50 d-flex flex-column justify-content-center">
        <h3>hi ${currentUser.username}!</h3>
        <p>your Api Key is: ${currentUser.apiKey}</p>
        <p>container with form for adding snippet</p>
    </div>

</div>

<div class="container mb-5">
    <div class="row justify-content-start">
        <div class="col-2 border border-dark">
            dropdown for snippet langage choice?
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
<%@include file="../footer.jsp" %>
</body>
</html>
