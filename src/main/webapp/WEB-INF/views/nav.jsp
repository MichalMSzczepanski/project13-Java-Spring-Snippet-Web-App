<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom border-dark">
    <a class="navbar-brand" href="/">SnippetApp</a>
<%--    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"--%>
<%--            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto text-danger">

            <%--            not authenticated user--%>
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item active">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#"> | </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/create-account">Create Account</a>
                </li>
            </sec:authorize>

            <%--    authenticated ADMIN--%>
            <sec:authorize access="hasRole('ADMIN')">
                <li class="nav-item active">
                    <a class="nav-link" href="/admin/dashboard"><span class="text-danger">Dashboard</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#"> | </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/create-account"><span class="text-danger">Create Account</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#"> | </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/manage-user-accounts"><span class="text-danger">Manage User Accounts</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#"> | </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/update-admin-details"><span class="text-danger">Update Account</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#"> | </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/update-admin-password"><span class="text-danger">Update Password</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#"> | </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/logout"><span class="text-danger"><b>Logout</b></span></a>
                        <%--                    <form action="<c:url value="/logout"/>" method="post">--%>
                        <%--                        <input type="submit" value="Logout">--%>
                        <%--                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                        <%--                    </form>--%>
                </li>
            </sec:authorize>

                <%--    authenticated USER--%>
                <sec:authorize access="hasRole('USER')">
                    <li class="nav-item active">
                        <a class="nav-link" href="/user/dashboard"><span class="text-primary">Dashboard</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#"> | </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/update-user-details"><span class="text-primary">Update Account Details</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#"> | </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/update-user-password"><span class="text-primary">Update Password</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#"> | </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/add-snippet"><span class="text-primary">Add Snippet</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#"> | </a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/logout"><span class="text-primary"><b>Logout</b></span></a>
                            <%--                    <form action="<c:url value="/logout"/>" method="post">--%>
                            <%--                        <input type="submit" value="Logout">--%>
                            <%--                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                            <%--                    </form>--%>
                    </li>
                </sec:authorize>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search Snippets</button>
        </form>
    </div>
</nav>