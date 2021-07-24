<%@include file="../taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        ${user.username} Details
    </title>
    <%@include file="../bootstrap.jsp" %>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container">
    <div class=" p-5 bg-light text-dark d-flex flex-column justify-content-center align-items-center">
        <h2>${user.username} Details</h2><br><br>
        <div>
          <p>Id: ${user.id}</p>
          <p>Username: ${user.username}</p>
          <p>Email: ${user.email}</p>
          <p>Role: ${user.role.name}</p>
          <p>Api Key: ${user.apiKey}</p>
          <p>Account Key Validation: ${user.accountKey}</p>
          <p>Account Created: ${user.accountKeyCreated}</p>
          <p>Account Expiration Date: ${user.accountKeyExpirationDate}</p>
        </div>
        <a class="btn btn-warning btn-lg" href="/admin/delete/${user.id}" role="button">Delete User</a><br>
    </div>

</div>
<%@include file="../footer.jsp" %>
</body>
</html>
