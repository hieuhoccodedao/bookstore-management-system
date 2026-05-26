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

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Khi người dùng gõ /Login lên URL, nó sẽ chuyển hướng tới trang JSP
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Đảm bảo không bị lỗi font tiếng Việt
        request.setCharacterEncoding("UTF-8");
        
        // 1. Lấy dữ liệu từ form HTML gửi lên
        String user = request.getParameter("txtUsername");
        String pass = request.getParameter("txtPassword");
        
        // 2. Gọi DAO để kiểm tra với Database
        UserDAO dao = new UserDAO();
        User account = dao.checkLogin(user, pass);
        
        if (account == null) {
            // Trường hợp sai: Đẩy thông báo lỗi về lại trang login
            request.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Trường hợp đúng: Lưu tài khoản vào phiên làm việc (Session)
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            
            // Chuyển hướng người dùng về trang chủ
            response.sendRedirect("Home");
        }
    }
}