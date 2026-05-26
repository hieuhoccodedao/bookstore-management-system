package Controller;

import Models.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Trả về giao diện trang đăng ký
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String fullname = request.getParameter("txtFullname");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        
        UserDAO dao = new UserDAO();
        
        // Kiểm tra xem tên tài khoản đã có người dùng chưa
        if (dao.checkAccountExist(username)) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // Nếu chưa ai dùng -> Lưu vào Database
            dao.signup(username, password, fullname);
            request.setAttribute("success", "Đăng ký thành công! Hãy bấm Đăng nhập ở góc trên.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}