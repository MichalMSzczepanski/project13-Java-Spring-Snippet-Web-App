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
            <h2>Add snippet</h2>
    <form:form class="p-3 w-75 border border-dark" method="post" modelAttribute="snippet">
        <form:hidden path="id"/>
        <form:hidden path="createdOn"/>
        <form:hidden path="updatedOn"/>
        <form:hidden path="tags"/>

        <b>Title</b><br>
        <form:input type="text" path="title"/><br>
        <form:errors path="title"/><br>

        <b>Favorite</b><br>
        No<form:radiobutton path="favorite" value="no" checked="checked"/>
        Yes<form:radiobutton path="favorite" value="yes"/><br><br>

        <b>Visibility</b><br>
        Public<form:radiobutton path="visibility" value="public" checked="checked"/>
        Private<form:radiobutton path="visibility" value="private"/><br><br>

        <b>Programming language</b><br>
        <form:select path="programmingLanguage">
            <form:options items="${programmingLanguages}"></form:options>
        </form:select><br><br>

        <b>Choose one folder... </b><br>
        <form:select path="folder">
            <form:options items="${folderList}"></form:options>
        </form:select><br><br>

        <b>... or create new folder</b><br>
        <input type="text" name="inputedFolder"><br><br>

<%--       tags--%>

        <b>Snippet content</b><br>
        <form:textarea path="snippetContent" rows="15" cols="75"/><br>
        <form:errors path="snippetContent"/><br>

        <input type="submit">
    </form:form>
        </div>
    </div>

</div>
<%@include file="../footer.jsp" %>
</body>
</html>
