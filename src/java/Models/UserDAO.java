package Models;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    
    // Hàm kiểm tra Đăng nhập
    public User checkLogin(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            // Nếu tìm thấy tài khoản khớp user/pass trong DB
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("fullname"),
                    rs.getInt("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu sai tài khoản hoặc mật khẩu
    }
    // Hàm kiểm tra xem tên đăng nhập đã có ai dùng chưa
    public boolean checkAccountExist(String username) {
        String query = "SELECT * FROM Users WHERE username = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; // Đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Chưa tồn tại, có thể đăng ký
    }

    // Hàm lưu tài khoản mới vào Database
    public void signup(String username, String password, String fullname) {
        String query = "INSERT INTO Users (username, password, fullname, role) VALUES (?, ?, ?, 0)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}