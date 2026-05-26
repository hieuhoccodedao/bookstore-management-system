package Controller;

import Models.UserDAO;
import entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminLogin", urlPatterns = {"/AdminLogin"})
public class AdminLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị trang đăng nhập admin riêng biệt
        request.getRequestDispatcher("admin-login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String user = request.getParameter("adminUser");
        String pass = request.getParameter("adminPass");
        
        UserDAO dao = new UserDAO();
        User account = dao.checkLogin(user, pass);
        
        // KIỂM TRA ĐIỀU KIỆN: Phải tồn tại tài khoản và thuộc tính role phải bằng 1 (Admin)
        if (account != null && account.getRole() == 1) {
            HttpSession session = request.getSession();
            // Lưu vào một biến session riêng biệt để bảo mật luồng admin
            session.setAttribute("adminAccount", account);
            
            // Đăng nhập đúng thông tin quản trị -> Chuyển hướng vào trang quản lý Dashboard
            response.sendRedirect("AdminDashboard");
        } else {
            // Nếu sai tài khoản hoặc đăng nhập bằng tài khoản thường (role = 0)
            request.setAttribute("error", "Quyền truy cập bị từ chối. Không có đặc quyền quản trị!");
            request.getRequestDispatcher("admin-login.jsp").forward(request, response);
        }
    }
}