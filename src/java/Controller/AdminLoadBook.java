package Controller;

import Models.SachDAO;
import entity.Sach;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminLoadBook", urlPatterns = {"/AdminLoadBook"})
public class AdminLoadBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Bảo mật Admin
        HttpSession session = request.getSession();
        if (session.getAttribute("adminAccount") == null) {
            response.sendRedirect("AdminLogin");
            return;
        }
        
        // Lấy ID sách người dùng bấm
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        
        // Gọi DAO tìm sách đó và đẩy lên JSP
        SachDAO dao = new SachDAO();
        Sach s = dao.getSachByID(id);
        
        request.setAttribute("book", s);
        request.getRequestDispatcher("admin-edit-book.jsp").forward(request, response);
    }
}