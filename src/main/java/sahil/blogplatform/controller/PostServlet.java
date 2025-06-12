package sahil.blogplatform.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sahil.blogplatform.dao.PostDAO;
import sahil.blogplatform.model.Post;
import sahil.blogplatform.model.User;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PostDAO postDAO;

    @Override
	public void init() {
        postDAO = new PostDAO();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createPost(request, response);
                break;
            case "edit":
                editPost(request, response);
                break;
            case "delete":
                deletePost(request, response);
                break;
            default:
                response.sendRedirect("dashboard.jsp");
                break;
        }
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listPosts(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setAuthorId(user.getId());

            boolean result = postDAO.createPost(post);

            if (result) {
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("error", "Failed to create post!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void editPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Post post = postDAO.getPostById(postId);
        post.setTitle(title);
        post.setContent(content);

        boolean result = postDAO.updatePost(post);

        if (result) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Failed to update post!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editPost.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int postId = Integer.parseInt(request.getParameter("postId"));

        postDAO.deletePost(postId);

        response.sendRedirect("dashboard.jsp");
    }

    private void listPosts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Post> posts = postDAO.getAllPosts();
        request.setAttribute("posts", posts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
        dispatcher.forward(request, response);
    }
}
