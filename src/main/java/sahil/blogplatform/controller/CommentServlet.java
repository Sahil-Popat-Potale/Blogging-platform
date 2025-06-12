package sahil.blogplatform.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sahil.blogplatform.dao.CommentDAO;
import sahil.blogplatform.model.Comment;
import sahil.blogplatform.model.User;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CommentDAO commentDAO;

    @Override
	public void init() {
        commentDAO = new CommentDAO();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add":
                addComment(request, response);
                break;
            default:
                response.sendRedirect("posts.jsp");
                break;
        }
    }

    private void addComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int postId = Integer.parseInt(request.getParameter("postId"));
        String content = request.getParameter("content");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            Comment comment = new Comment();
            comment.setPostId(postId);
            comment.setUserId(user.getId());
            comment.setContent(content);

            boolean result = commentDAO.createComment(comment);

            if (result) {
                response.sendRedirect("postDetails.jsp?postId=" + postId);
            } else {
                request.setAttribute("error", "Failed to add comment!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("postDetails.jsp?postId=" + postId);
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
