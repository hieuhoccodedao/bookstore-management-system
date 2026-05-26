package Controller;

import Models.SachDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminEditBook", urlPatterns = {"/AdminEditBook"})
public class AdminEditBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        // Lấy thông tin từ form sửa
        int id = Integer.parseInt(request.getParameter("txtId")); // Lấy ID từ thẻ input ẩn
        String ten = request.getParameter("txtTenSach");
        String anh = request.getParameter("txtHinhAnh");
        double gia = Double.parseDouble(request.getParameter("txtGia"));
        
        // Cập nhật xuống CSDL
        SachDAO dao = new SachDAO();
        dao.updateSach(id, ten, anh, gia);
        
        // Quay về danh sách để xem kết quả
        response.sendRedirect("AdminDashboard");
    }
}