package Controller;

import Models.SachDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminAddBook", urlPatterns = {"/AdminAddBook"})
public class AdminAddBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Kiểm tra bảo mật: Phải là admin mới được vào form này
        HttpSession session = request.getSession();
        if (session.getAttribute("adminAccount") == null) {
            response.sendRedirect("AdminLogin");
            return;
        }
        
        // Điều hướng tới trang giao diện điền thông tin
        request.getRequestDispatcher("admin-add-book.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Tránh lỗi font tiếng Việt
        request.setCharacterEncoding("UTF-8");
        
        // Lấy dữ liệu từ form
        String ten = request.getParameter("txtTenSach");
        String anh = request.getParameter("txtHinhAnh");
        String giaString = request.getParameter("txtGia");
        
        double gia = 0;
        try {
            gia = Double.parseDouble(giaString);
        } catch (Exception e) {
            System.out.println("Lỗi chuyển đổi giá: " + e.getMessage());
        }
        
        // Gọi DAO lưu vào Database
        SachDAO dao = new SachDAO();
        dao.insertSach(ten, anh, gia);
        
        // Lưu xong thì tự động quay về trang danh sách để xem thành quả
        response.sendRedirect("AdminDashboard");
    }
}