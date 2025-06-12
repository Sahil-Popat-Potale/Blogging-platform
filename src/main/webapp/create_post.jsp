<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sahil.blogplatform.model.User" %>

<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Create New Post</title>
</head>
<body>

    <h2>Create a New Blog Post</h2>

    <form action="post" method="post">
        <input type="hidden" name="action" value="create">

        <label>Title:</label><br>
        <input type="text" name="title" required><br><br>

        <label>Content:</label><br>
        <textarea name="content" rows="10" cols="50" required></textarea><br><br>

        <button type="submit">Publish Post</button>
    </form>

    <p><a href="dashboard.jsp">Back to Dashboard</a></p>

    <%-- Show error if any --%>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color:red;"><%= error %></p>
    <% } %>

</body>
</html>
