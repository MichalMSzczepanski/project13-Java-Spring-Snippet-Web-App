<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:if test="${newSnippet == false}">Edit snippet</c:if>
        <c:if test="${newSnippet == true}">Add snippet</c:if>
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container mb-5">
    <div style="height: auto;">
        <div class="p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
            <h2>
                <c:if test="${newSnippet == false}">Edit snippet</c:if>
                <c:if test="${newSnippet == true}">Add snippet</c:if>
            </h2>
            <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="snippet">
                <form:hidden path="id"/>
                <form:hidden path="createdOn"/>
                <form:hidden path="updatedOn"/>

                <b>Title</b><br>
                <form:input type="text" path="title" class="w-100"/><br>
                <%--        <form:errors path="*"/><br>--%>
                <form:errors cssClass="d-block mt-2 alert alert-danger" path="title"/><br>

                <div class="d-flex flex-row">
                    <div class="w-50">
                        <b>Favorite</b><br>
                        No<form:radiobutton path="favorite" value="no" checked="checked"/>
                        Yes<form:radiobutton path="favorite" value="yes"/><br><br>
                    </div>
                    <div class="w-50">
                        <b>Visibility</b><br>
                        Private<form:radiobutton path="visibility" value="private" checked="checked"/>
                        Public<form:radiobutton path="visibility" value="public"/><br><br>
                    </div>
                </div>

                <div class="d-flex flex-row">
                    <div class="w-50">
                        <b>Choose a folder... </b><br>
                        <form:select path="folder" class="w-75">
                            <form:options items="${folderList}"/>
                        </form:select><br>
                        <form:errors cssClass="d-block mt-2 alert alert-danger" path="folder"/><br>
                    </div>
                    <div class="w-50">
                        <b>or create a new one</b><br>
                        <input type="text" name="inputtedFolder" class="w-75"><br><br>
                    </div>
                </div>

                <div class="d-flex flex-row">
                    <div class="w-50">
                        <b>Programming language</b><br>
                        <form:select path="programmingLanguage" class="w-75">
                            <form:options items="${programmingLanguages}"/>
                        </form:select><br><br>
                    </div>
                    <div class="w-50">
                        <b>Link</b><br>
                        <form:input type="text" path="link" class="w-75"/><br>
                    </div>
                </div>
                <c:if test="${newSnippet == false}">
                    <b>Snippet tags:</b>
                    <c:if test="${empty snippet.tags}">
                        no tags associated
                    </c:if>
                    <c:forEach items="${snippet.tags}" var="tag">
                        ${tag.tagName};
                    </c:forEach><br><br>
                </c:if>
                <div class="d-flex flex-row">
                    <div class="w-50">
                        <b>Choose tags</b><br>
                        <c:choose>
                            <c:when test="${not empty userTags}">
                                <form:select path="tags" multiple="true" class="w-75">
                                    <c:forEach items="${userTags}" var="tag">
                                        <form:option value="${tag.id}" label="${tag.tagName}"/>
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
                        <b> and/ or add new tags (delimiter: ",")</b><br>
                        <input type="text" name="inputtedTags" class="w-75"><br><br>
                    </div>
                </div>
                <br>

                <b>Snippet content</b><br>
                <form:textarea path="snippetContent" rows="15" cols="75"/><br>
                <form:errors path="snippetContent" cssClass="d-block mt-2 alert alert-danger"/><br>

                <input type="submit">
            </form:form>
        </div>
    </div>

</div>
<%@include file="../footer.jsp" %>
</body>
</html>
