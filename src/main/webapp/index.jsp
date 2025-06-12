<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Simple Blogging Platform</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f6fa;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #2f3640;
            color: white;
            padding: 20px;
            text-align: center;
        }

        nav {
            background-color: #dcdde1;
            display: flex;
            justify-content: center;
            padding: 10px;
        }

        nav a {
            text-decoration: none;
            color: #2f3640;
            margin: 0 15px;
            font-weight: bold;
        }

        nav a:hover {
            color: #40739e;
        }

        .container {
            padding: 40px;
            text-align: center;
        }

        .container h2 {
            color: #2f3640;
        }
    </style>
</head>
<body>

<header>
    <h1>Welcome to the Simple Blogging Platform</h1>
</header>

<nav>
    <a href="login.jsp">Login</a>
    <a href="register.jsp">Register</a>
    <a href="post?action=viewAll">View Posts</a>
</nav>

<div class="container">
    <h2>Share your thoughts with the world!</h2>
    <p>This is a simple platform where users can write blog posts, comment on others' posts, and manage content easily.</p>
</div>

</body>
</html>
