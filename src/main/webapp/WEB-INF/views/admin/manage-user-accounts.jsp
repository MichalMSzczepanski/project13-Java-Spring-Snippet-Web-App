<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Manage User Accounts
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container">
    <div class=" p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
        <h2>Manage User Accounts</h2><br><br>
        <div>
            <table class="table-sm">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Username</th>
                    <th scope="col">Email</th>
                    <th scope="col">Enabled</th>
                    <th scope="col">Role</th>
                    <%--                        <th scope="col">Api Key</th>--%>
                    <%--                        <th scope="col">Validation Key</th>--%>
                    <th scope="col">Account Created</th>
                    <%--                        <th scope="col">Account Expiration</th>--%>
                    <th scope="col">EDIT</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td><a href="/admin/user-details/${user.id}">${user.username}</a></td>
                        <td>${user.email}</td>
                        <td>${user.enabled}</td>
                        <td>${user.role.name}</td>
                            <%--                            <td>${user.apiKey}</td>--%>
                            <%--                            <td>${user.accountKeyValidation}</td>--%>
                        <td>${user.accountKeyCreated}</td>
                            <%--                            <td>${user.accountKeyExpirationDate}</td>--%>
                        <td><a href="/admin/edit-user/${user.id}">edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
