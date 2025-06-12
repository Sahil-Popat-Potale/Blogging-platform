<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sahil.blogplatform.model.User" %>
<%@ page import="sahil.blogplatform.model.Post" %>
<%@ page import="sahil.blogplatform.dao.PostDAO" %>

<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    PostDAO postDAO = new PostDAO();
    List<Post> posts = postDAO.getAllPosts();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel</title>
</head>
<body>

    <h2>Admin Panel - Manage All Posts</h2>

    <table border="1" cellpadding="10">
        <thead>
            <tr>
                <th>Post ID</th>
                <th>Title</th>
                <th>Author ID</th>
                <th>Created At</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <%
            if (posts != null && !posts.isEmpty()) {
                for (Post post : posts) {
        %>
            <tr>
                <td><%= post.getId() %></td>
                <td><%= post.getTitle() %></td>
                <td><%= post.getAuthorId() %></td>
                <td><%= post.getCreatedAt() %></td>
                <td>
                    <form action="post" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="postId" value="<%= post.getId() %>">
                        <button type="submit" onclick="return confirm('Are you sure to delete this post?');">Delete</button>
                    </form>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="5">No posts available.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>

    <p><a href="dashboard.jsp">Back to Dashboard</a></p>

</body>
</html>
