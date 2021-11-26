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
            <h2>Edit snippet "${snippet.title}"</h2>
    <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="snippet">
        <form:hidden path="id"/>
        <form:hidden path="createdOn"/>
        <form:hidden path="updatedOn"/>

        <b>Title</b><br>
        <form:input type="text" path="title"/><br>
        <form:errors cssClass="d-block mt-2 alert alert-danger" path="title"/><br>

        <b>Favorite</b><br>
        No<form:radiobutton path="favorite" value="no" checked="checked"/>
        Yes<form:radiobutton path="favorite" value="yes"/><br><br>

        <b>Visibility</b><br>
        Public<form:radiobutton path="visibility" value="public" checked="checked"/>
        Private<form:radiobutton path="visibility" value="private"/><br><br>

        <b>Choose one folder... </b><br>
        <form:select path="folder">
            <form:options items="${folderList}"></form:options>
        </form:select><br><br>

        <b>Programming language</b><br>
        <form:select path="programmingLanguage">
            <form:options items="${programmingLanguages}"></form:options>
        </form:select><br><br>

        <b>... or create new folder</b><br>
        <input type="text" name="inputedFolder"><br><br>

        <div class="d-flex flex-row">
            <div class="w-50">
                <b>Choose tags</b><br>
                <c:choose>
                    <c:when test="${not empty userTags}">
                        <form:select path="tags" multiple="true" class="w-75">
                                                        <form:options items="${userTags}"/>
                            <c:forEach items="${userTags}" var="tag">
                                <form:option value="${tag.id}" label="${tag.tagName}" />
                            </c:forEach>
                            <form:errors cssClass="d-block mt-2 alert alert-danger" path="tags"/><br>
                        </form:select>
                    </c:when>
                    <c:otherwise>
                        no tags found, create some!
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="w-50">
                <b> and/ or add new tags</b><br>
                <input type="text" name="inputtedTags" class="w-75"><br><br>
            </div>
        </div>

        <b>Snippet content</b><br>
        <form:textarea path="snippetContent" rows="15" cols="75"/><br>
        <form:errors cssClass="d-block mt-2 alert alert-danger" path="snippetContent"/><br>

        <input type="submit">
    </form:form><br><br>

            <a class="btn btn-primary btn-lg" href="/user/delete-snippet/${snippet.id}" role="button">Delete snippet</a><br><br>
        </div>
    </div>

</div>
<%@include file="../footer.jsp" %>
</body>
</html>
