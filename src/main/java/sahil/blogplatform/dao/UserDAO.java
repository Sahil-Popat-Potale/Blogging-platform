package sahil.blogplatform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sahil.blogplatform.model.User;
import sahil.blogplatform.util.DBUtil;

public class UserDAO {

    // Register new user
    public boolean registerUser(User user) {
        boolean result = false;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                result = true;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Authenticate user login
    public User loginUser(String username, String password) {
        User user = null;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
