<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Add Snippet
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container mb-5">
    <div style="height: auto;">
        <div class="p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            <h2>${snippet.title}</h2>
            id: ${snippet.id}<br>
<%--            tags: ${snippet.tags}<br>--%>
            programming language: ${snippet.programmingLanguage}<br>
            folder: ${snippet.folder}<br>
            favorite: ${snippet.favorite}<br>
            visibility: ${snippet.visibility}<br>
            created on: ${snippet.createdOn}<br>
            updated on: ${snippet.updatedOn}<br>
            content:<br><br>
            <code><textarea readonly rows="15" cols="100">${snippet.snippetContent}</textarea></code><br>
<%--            <code>${snippet.snippetContent}</code><br><br>--%>

            <a class="btn btn-primary btn-lg" href="/user/dashboard/${snippet.folder}/${snippet.id}" role="button">view in dashboard</a><br><br>
            <a class="btn btn-primary btn-lg" href="/user/edit-snippet/${snippet.id}" role="button">Edit snippet</a><br><br>
            <a class="btn btn-primary btn-lg" href="/user/delete-snippet/${snippet.id}" role="button">Delete snippet</a><br><br>
        </div>
    </div>

</div>
<%@include file="../footer.jsp" %>
</body>
</html>
