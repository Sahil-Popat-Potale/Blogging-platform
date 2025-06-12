<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sahil.blogplatform.model.Post" %>
<%@ page import="sahil.blogplatform.dao.PostDAO" %>
<%@ page import="sahil.blogplatform.model.User" %>

<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    PostDAO postDAO = new PostDAO();
    List<Post> posts = postDAO.getAllPosts();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

    <h2>Welcome, <%= user.getUsername() %>!</h2>

    <p><a href="create_post.jsp">Create New Post</a> | <a href="logout.jsp">Logout</a></p>

    <h3>All Blog Posts:</h3>
    
    <% if (user != null && "admin".equals(user.getRole())) { %>
	   <a href="admin_panel.jsp">Admin Panel</a><br><br>
	<% } %>
    

    <%
        if (posts != null && !posts.isEmpty()) {
            for (Post post : posts) {
    %>
                <div style="border: 1px solid black; margin-bottom: 10px; padding: 10px;">
                    <h4><a href="view_post.jsp?postId=<%= post.getId() %>"><%= post.getTitle() %></a></h4>
                    <p><%= post.getContent().length() > 100 ? post.getContent().substring(0, 100) + "..." : post.getContent() %></p>
                    
                    <%-- Only show Edit/Delete if user is the author --%>
                    <%
                        if (user.getId() == post.getAuthorId() || "admin".equals(user.getRole())) {
                    %>
                        <p>
                            <a href="edit_post.jsp?postId=<%= post.getId() %>">Edit</a> |
                            <a href="post?action=delete&postId=<%= post.getId() %>">Delete</a>
                        </p>
                    <% } %>
                </div>
    <%
            }
        } else {
    %>
        <p>No posts available.</p>
    <%
        }
    %>

</body>
</html>
