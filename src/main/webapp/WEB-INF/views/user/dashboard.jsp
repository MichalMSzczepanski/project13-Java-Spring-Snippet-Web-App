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
    <h3>hi ${currentUser.username}!</h3>
    <p>your Api Key is: ${currentUser.apiKey}</p>
    <p>container with form for adding snippet</p>
    <a class="btn btn-primary btn-lg" href="/user/add-snippet" role="button">Add snippet</a><br><br>
</div>
<div class="container mb-5">
    <div class="row justify-content-start">
        <div class="col-2 border border-dark">
            FOLDER
        </div>
        <div class="col-2 border border-dark">
            SNIPPETS
        </div>
        <div class="col-8 border border-dark">
            <c:if test="${not empty currentSnippet}">
                ${currentSnippet.getTitle()}
            </c:if>
        </div>
    </div>
    <div class="row justify-content-start">
        <div class="col-2 border border-dark">
            <c:forEach items="${folderList}" var="folder">
                <a href="/user/dashboard/${folder}">${folder}</a><br>
            </c:forEach>
        </div>
        <div class="col-2 border border-dark">
            <c:if test="${empty snippetList}">
            choose folder
            </c:if>
            <c:forEach items="${snippetList}" var="snippet">
                <a href="/user/dashboard/${snippet.folder}/${snippet.id}">${snippet.title}</a><br>
            </c:forEach>
        </div>
        <div class="col-8 border border-dark ">
            <c:if test="${empty currentSnippet}">
                <c:if test="${not empty snippetList}">
                    choose snippet
                </c:if>
            </c:if>
            <c:if test="${not empty currentSnippet}">
            <div>
                Language: ${currentSnippet.programmingLanguage} |
                Visibility: ${currentSnippet.favorite} |
                <a href="/user/snippet-details/${currentSnippet.id}">DETAILS</a> |
                <a href="/user/edit-snippet/${currentSnippet.id}">EDIT</a>
            </div>
            <div>
                <textarea rows="20" cols="75"> ${currentSnippet.getSnippetContent()}</textarea>

            </div>
            </c:if>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
