<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Invalidate the current session
    session.invalidate();

    // Redirect to login page
    response.sendRedirect("login.jsp");
%>
