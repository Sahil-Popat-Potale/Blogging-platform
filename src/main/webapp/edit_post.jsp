<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sahil.blogplatform.model.User" %>
<%@ page import="sahil.blogplatform.model.Post" %>
<%@ page import="sahil.blogplatform.dao.PostDAO" %>

<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Get postId from URL
    String postIdParam = request.getParameter("postId");
    if (postIdParam == null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }

    int postId = Integer.parseInt(postIdParam);

    PostDAO postDAO = new PostDAO();
    Post post = postDAO.getPostById(postId);

    if (post == null || (post.getAuthorId() != user.getId() && !"admin".equals(user.getRole()))) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Post</title>
</head>
<body>

    <h2>Edit Blog Post</h2>

    <form action="post" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="postId" value="<%= post.getId() %>">

        <label>Title:</label><br>
        <input type="text" name="title" value="<%= post.getTitle() %>" required><br><br>

        <label>Content:</label><br>
        <textarea name="content" rows="10" cols="50" required><%= post.getContent() %></textarea><br><br>

        <button type="submit">Update Post</button>
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
