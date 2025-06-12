package sahil.blogplatform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sahil.blogplatform.model.Post;
import sahil.blogplatform.util.DBUtil;

public class PostDAO {

    // Create new post
    public boolean createPost(Post post) {
        boolean result = false;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "INSERT INTO posts (title, content, author_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setInt(3, post.getAuthorId());

            int rows = stmt.executeUpdate();
            if (rows > 0) { result = true; }
            stmt.close();
            conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return result;
    }

    // Get all posts
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "SELECT * FROM posts ORDER BY created_at DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setAuthorId(rs.getInt("author_id"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                post.setUpdatedAt(rs.getTimestamp("updated_at"));

                posts.add(post);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return posts;
    }

    // Get post by id
    public Post getPostById(int id) {
        Post post = null;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "SELECT * FROM posts WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setAuthorId(rs.getInt("author_id"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                post.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return post;
    }

    // Update a post
    public boolean updatePost(Post post) {
        boolean result = false;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setInt(3, post.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) { result = true; }
            stmt.close();
            conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return result;
    }

    // Delete a post
    public boolean deletePost(int id) {
        boolean result = false;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "DELETE FROM posts WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) { result = true; }
            stmt.close();
            conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return result;
    }
}
