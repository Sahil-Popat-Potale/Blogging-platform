<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sahil.blogplatform.model.Post" %>
<%@ page import="sahil.blogplatform.model.Comment" %>
<%@ page import="sahil.blogplatform.dao.PostDAO" %>
<%@ page import="sahil.blogplatform.dao.CommentDAO" %>
<%@ page import="sahil.blogplatform.model.User" %>

<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");

    // Get postId from URL
    String postIdParam = request.getParameter("postId");
    if (postIdParam == null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }

    int postId = Integer.parseInt(postIdParam);

    PostDAO postDAO = new PostDAO();
    Post post = postDAO.getPostById(postId);

    if (post == null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }

    CommentDAO commentDAO = new CommentDAO();
    List<Comment> comments = commentDAO.getCommentsByPostId(postId);
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= post.getTitle() %></title>
</head>
<body>

    <h2><%= post.getTitle() %></h2>
    <p><%= post.getContent() %></p>

    <hr> <h3>Comments:</h3>

    <% if (comments != null && !comments.isEmpty()) {
            for (Comment comment : comments) {
    %>
                <div style="border: 1px solid #ccc; margin-bottom: 10px; padding: 10px;">
                    <p><%= comment.getContent() %></p>
                    <small>By User ID: <%= comment.getUserId() %> at <%= comment.getCreatedAt() %></small>
                </div>
    <%
            }
        } else {
    %>
        <p>No comments yet. Be the first one to comment!</p>
    <%  }
    %>

    <hr>

    <%
        if (user != null) {
    %>
        <h3>Add a Comment:</h3>

        <form action="comment" method="post">
            <input type="hidden" name="action" value="create">
            <input type="hidden" name="postId" value="<%= post.getId() %>">

            <textarea name="content" rows="5" cols="50" required></textarea><br><br>

            <button type="submit">Submit Comment</button>
        </form>
    <%
        } else {
    %>
        <p><a href="login.jsp">Login</a> to comment on this post.</p>
    <%
        }
    %>

    <p><a href="dashboard.jsp">Back to Dashboard</a></p>

</body>
</html>
