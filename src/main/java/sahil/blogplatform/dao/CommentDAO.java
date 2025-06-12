package sahil.blogplatform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sahil.blogplatform.model.Comment;
import sahil.blogplatform.util.DBUtil;

public class CommentDAO {

    // Create new comment
    public boolean createComment(Comment comment) {
        boolean result = false;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "INSERT INTO comments (post_id, user_id, content) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, comment.getPostId());
            stmt.setInt(2, comment.getUserId());
            stmt.setString(3, comment.getContent());

            int rows = stmt.executeUpdate();
            if (rows > 0) { result = true; }
            stmt.close();
            conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return result;
    }

    // Get all comments for a post
    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "SELECT * FROM comments WHERE post_id = ? ORDER BY created_at ASC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setPostId(rs.getInt("post_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setContent(rs.getString("content"));
                comment.setCreatedAt(rs.getTimestamp("created_at"));

                comments.add(comment);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return comments;
    }

    // Delete a comment
    public boolean deleteComment(int id) {
        boolean result = false;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "DELETE FROM comments WHERE id = ?";
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
