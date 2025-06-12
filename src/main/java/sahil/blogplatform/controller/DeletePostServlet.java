package sahil.blogplatform.controller;

import sahil.blogplatform.dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deletePost")
public class DeletePostServlet extends HttpServlet {

    private PostDAO postDAO;

    @Override
    public void init() {
        postDAO = new PostDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int postId = Integer.parseInt(idParam);
                boolean deleted = postDAO.deletePost(postId);

                if (deleted) {
                    response.sendRedirect("post?action=viewAll");  // Redirect to post list
                } else {
                    response.getWriter().println("Post not found or could not be deleted.");
                }

            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid post ID.");
            }

        } else {
            response.getWriter().println("No post ID provided.");
        }
    }
}
