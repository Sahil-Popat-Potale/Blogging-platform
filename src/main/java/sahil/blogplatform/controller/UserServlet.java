package sahil.blogplatform.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sahil.blogplatform.dao.UserDAO;
import sahil.blogplatform.model.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
	public void init() { userDAO = new UserDAO(); }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) { action = ""; }

        switch (action) {
            case "register":
                registerUser(request, response);
                break;
            case "login":
                loginUser(request, response);
                break;
            case "logout":
                logoutUser(request, response);
                break;
            default:
                response.sendRedirect("dashboard.jsp");
                break;
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User(username, password, email);
        boolean result = userDAO.registerUser(user);

        if (result) {
            request.setAttribute("message", "Registration Successful! Please Login.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Registration Failed! Try Again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.loginUser(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false); // false = don't create new session
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}
