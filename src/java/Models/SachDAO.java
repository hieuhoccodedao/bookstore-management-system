package Models;

import entity.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    
    // 1. Hàm đếm tổng số lượng sách (ĐÃ NÂNG CẤP: Tích hợp tìm kiếm txtSearch)
    public int getTotalBooks(String priceOption, String txtSearch) {
        String query = "SELECT COUNT(*) FROM Sach WHERE 1=1";
        
        if (priceOption != null) {
            if (priceOption.equals("under100")) query += " AND gia < 100000";
            else if (priceOption.equals("100to300")) query += " AND gia >= 100000 AND gia <= 300000";
            else if (priceOption.equals("above300")) query += " AND gia > 300000";
        }
        
        // Thêm điều kiện tìm kiếm theo tên sách
        if (txtSearch != null && !txtSearch.trim().isEmpty()) {
            query += " AND tenSach LIKE ?";
        }
        
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            
            // Set tham số cho từ khóa tìm kiếm nếu có
            if (txtSearch != null && !txtSearch.trim().isEmpty()) {
                ps.setString(1, "%" + txtSearch + "%");
            }
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 2. Hàm lấy danh sách sách phân trang (ĐÃ NÂNG CẤP: Tích hợp tìm kiếm txtSearch)
    public List<Sach> getBooksPaging(String priceOption, String txtSearch, int pageIndex) {
        List<Sach> list = new ArrayList<>();
        String query = "SELECT * FROM Sach WHERE 1=1";
        
        // Kết hợp bộ lọc giá cũ
        if (priceOption != null) {
            if (priceOption.equals("under100")) query += " AND gia < 100000";
            else if (priceOption.equals("100to300")) query += " AND gia >= 100000 AND gia <= 300000";
            else if (priceOption.equals("above300")) query += " AND gia > 300000";
        }
        
        // Thêm điều kiện tìm kiếm theo tên sách
        if (txtSearch != null && !txtSearch.trim().isEmpty()) {
            query += " AND tenSach LIKE ?";
        }
        
        // Thêm câu lệnh giới hạn phân trang của MySQL
        query += " LIMIT 8 OFFSET ?";
        
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            
            // Xử lý động vị trí của các dấu chấm hỏi (?) trong câu query
            int paramIndex = 1;
            if (txtSearch != null && !txtSearch.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + txtSearch + "%");
            }
            
            // Tính số bản ghi cần bỏ qua cho phân trang
            ps.setInt(paramIndex, (pageIndex - 1) * 8); 
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Sach(
                    rs.getInt("id"),
                    rs.getString("tenSach"),
                    rs.getDouble("gia"),
                    rs.getString("hinhAnh")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 3. Hàm lấy danh sách sách và hỗ trợ lọc giá trực tiếp (GIỮ NGUYÊN)
    public List<Sach> getBooksByPrice(String priceOption) {
        List<Sach> list = new ArrayList<>();
        String query = "SELECT * FROM Sach WHERE 1=1";
        
        if (priceOption != null) {
            if (priceOption.equals("under100")) {
                query += " AND gia < 100000";
            } else if (priceOption.equals("100to300")) {
                query += " AND gia >= 100000 AND gia <= 300000";
            } else if (priceOption.equals("above300")) {
                query += " AND gia > 300000";
            }
        }
        
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Sach(
                    rs.getInt("id"),
                    rs.getString("tenSach"),
                    rs.getDouble("gia"),
                    rs.getString("hinhAnh")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 4. Hàm tìm kiếm sách theo ID phục vụ cho chức năng thêm vào giỏ hàng (GIỮ NGUYÊN)
    public Sach getSachByID(int id) {
        String query = "SELECT * FROM Sach WHERE id = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Sach(
                    rs.getInt("id"),
                    rs.getString("tenSach"),
                    rs.getDouble("gia"),
                    rs.getString("hinhAnh")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // Hàm lấy toàn bộ danh sách sách (Dùng riêng cho Admin Dashboard)
    // Hàm lấy toàn bộ danh sách sách (Dùng riêng cho Admin Dashboard)
    public List<Sach> getAllSach() {
        List<Sach> list = new ArrayList<>();
        String query = "SELECT * FROM Sach ORDER BY id DESC"; // Lấy sách mới nhất lên đầu
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng sách và thêm vào danh sách
                list.add(new Sach(
                        rs.getInt("id"),
                        rs.getString("tenSach"),
                        rs.getDouble("gia"),      // Giá nằm trước
                        rs.getString("hinhAnh")
                ));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Hàm thêm sách mới dành cho Admin
    public void insertSach(String tenSach, String hinhAnh, double gia) {
        // Lệnh SQL để chèn dữ liệu. Cột ID tự tăng nên không cần chèn.
        String query = "INSERT INTO Sach (tenSach, hinhAnh, gia) VALUES (?, ?, ?)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenSach);
            ps.setString(2, hinhAnh);
            ps.setDouble(3, gia);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateSach(int id, String tenSach, String hinhAnh, double gia) {
        String query = "UPDATE Sach SET tenSach = ?, hinhAnh = ?, gia = ? WHERE id = ?";
        try {
            java.sql.Connection conn = new DBContext().getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenSach);
            ps.setString(2, hinhAnh);
            ps.setDouble(3, gia);
            ps.setInt(4, id); // ID nằm ở dấu hỏi số 4
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}