
# 📑 Table of Contents

- [Project Features](#project-features)
- [Technologies Used](#technologies-used)
- [Database Schema](#database-schema)
- [Project Structure](#project-structure)
- [How the Project Works](#how-the-project-works)
- [Setup and Instructions](#setup-instructions)
- [Future Improvements](#future-improvements)

---

# 📚 Simple Blogging Platform (JSP + Servlet + MySQL)

Welcome to the Simple Blogging Platform — a web-based application developed using Java EE technologies!  
This project is designed to help beginners understand **full-stack web development** with **JSP**, **Servlets**, **JDBC**, and **MySQL**.

---

## 📋 Project Features

- **User Authentication**
  - User Registration, Login, and Logout
  - Passwords securely stored
- **Post Management**
  - Create, Read, Update, and Delete (CRUD) Posts
  - Post Timestamps (Created and Updated)
- **Comment System**
  - Add comments on posts
  - View comments from other users
- **Admin Panel**
  - Manage all posts (approve or delete)
  - View user activities
- **Session Management**
  - User sessions with timeout configuration
- **Error Handling**
  - Custom 404 and 500 error pages
- **Simple and Clean UI**
  - JSP + CSS based front-end without external frameworks
- **Database Integration**
  - MySQL backend using JDBC connectivity

---

## 🛠️ Technologies Used

| Layer        | Technologies            |
|--------------|--------------------------|
| Frontend     | HTML5, CSS3, JSP          |
| Backend      | Java (Servlets, JSP)      |
| Database     | MySQL (or MariaDB)        |
| Server       | Apache Tomcat 9           |
| Java Library | JDBC (Java Database Connectivity) |

---

## 🗄️ Database Schema

``` sql
CREATE DATABASE blog_platform;

USE blog_platform;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'user'
);

CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    author_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES users(id)
);

CREATE TABLE comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    user_id INT,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

## 🗂️ Project Structure

```
src/main/java/
└── com/
    └── blogplatform/
        ├── controller/    --> Servlets (UserServlet, PostServlet, CommentServlet)
        ├── dao/           --> Database Access Classes (UserDAO, PostDAO, CommentDAO)
        ├── model/         --> Entity Classes (User, Post, Comment)
        └── util/          --> DB Connection Utility (DBUtil)
        
Webapp/
├── jsp/                   --> JSP pages (index.jsp, login.jsp, register.jsp, etc.)
├── css/                   --> Stylesheets
├── error404.jsp            --> 404 Error Page (not added)
├── error500.jsp            --> 500 Error Page (not added)
├── META-INF/
└── WEB-INF/
    ├── web.xml             --> Deployment Descriptor
```

---

## ⚙️ How the Project Works

1. **User Registration & Login**
   - New users register with a username, password, and email.
   - Registered users can log in and create posts.

2. **Post Management**
   - After logging in, users can create a new blog post.
   - Posts are listed with creation dates.
   - Users can edit or delete their own posts.

3. **Commenting**
   - Any logged-in user can comment on posts.
   - Comments are shown under each post.

4. **Admin Controls**
   - Admins have special access to view and manage all posts across the platform.

5. **Session Handling**
   - If inactive for a certain period (e.g., 30 minutes), the session expires automatically.

6. **Error Handling**
   - Friendly 404 and 500 error pages are displayed for missing pages or server errors.

---

## 🛠️ Setup Instructions

1. Install **Apache Tomcat 9** and **MySQL Server**.
2. Import the project into **Eclipse IDE** as a Dynamic Web Project.
3. Create the database and tables using the provided SQL script.
4. Configure database credentials in the `DBUtil.java` file.
5. Build and Deploy the project to Tomcat.
6. Access via `http://localhost:8080/[your_project_name]/`

---

## 📈 Future Improvements

- Password encryption with BCrypt
- Rich text editor for post content
- Admin dashboard with analytics
- Pagination for posts and comments
- Email notifications for new comments

---

# ✨ Thanks for checking out this project! Happy Coding! 🚀

---
