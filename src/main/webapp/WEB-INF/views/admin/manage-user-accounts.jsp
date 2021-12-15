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
        <div class="d-flex flex-column justify-content-center align-items-center">
            <table class="table-sm">
                <thead>
                <tr>
                    <th scope="col"></th>
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
                <c:forEach items="${userList}" var="user" varStatus="loop">
                    <tr>
                        <th scope="row"> <c:out value="${(loop.index + 1) == 10 ? pageNumber * 10 : pageNumber * 10 - 10 + loop.index + 1}"/></th>
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
            <nav aria-label="Page navigation example">
                <ul class="pagination mt-5">
<%--                    ikonka wstecz--%>
                    <li class="page-item <c:out value="${pageNumber > 1 ? 'None' : 'disabled'}"/>">
                        <a class="page-link" href="http://localhost:8080/admin/manage-user-accounts/${pageNumber - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
<%--                    numerek wstecz--%>
                    <li class="page-item <c:out value="${pageNumber > 1 ? 'None' : 'd-none'}"/>"><a class="page-link" href="http://localhost:8080/admin/manage-user-accounts/${pageNumber - 1}">
                        ${pageNumber - 1}
                    </a></li>
<%--                    biezacy numerek--%>
    <li class="page-item active"><a class="page-link" href="#">${pageNumber}</a></li>
<%--                    numerek dalej--%>
                    <li class="page-item <c:out value="${numberOfUsers - (pageNumber * 10) > 0 ? 'None' : 'd-none'}"/>"><a class="page-link" href="http://localhost:8080/admin/manage-user-accounts/${pageNumber + 1}">
                        ${pageNumber + 1}
                    </a></li>
<%--                    ikonka dalej--%>
                    <li class="page-item <c:out value="${numberOfUsers - (pageNumber * 10) > 0 ? 'None' : 'disabled'}"/>">
                        <a class="page-link />" href="http://localhost:8080/admin/manage-user-accounts/${pageNumber + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
