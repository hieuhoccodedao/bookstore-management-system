package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminDashboard", urlPatterns = {"/AdminDashboard"})
public class AdminDashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        // Bảo mật: Nếu chưa đăng nhập admin thì đá về trang login
        if (session.getAttribute("adminAccount") == null) {
            response.sendRedirect("AdminLogin");
            return;
        }

        // Lấy danh sách sách từ database để hiển thị lên bảng quản lý
        Models.SachDAO dao = new Models.SachDAO();
        java.util.List<entity.Sach> list = dao.getAllSach();
        
        // Đẩy dữ liệu sang trang JSP
        request.setAttribute("listS", list);
        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }
}