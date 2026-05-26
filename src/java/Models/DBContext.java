package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/QuanLySach_Moi?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = ""; // Điền mật khẩu database của bạn nếu có
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}