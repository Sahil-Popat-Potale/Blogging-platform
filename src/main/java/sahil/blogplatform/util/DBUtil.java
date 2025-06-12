package sahil.blogplatform.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DBURL = "jdbc:mysql://localhost:3306/blog_platform";
    private static final String DBUNAME = "root"; // replace with your DB username
    private static final String DBPIN = "root";     // replace with your DB password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the MySQL JDBC driver is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBURL, DBUNAME, DBPIN);
    }
}
