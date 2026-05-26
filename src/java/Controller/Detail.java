package Controller;

import Models.SachDAO;
import entity.Sach;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Detail", urlPatterns = {"/Detail"})
public class Detail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Nhận ID sách từ đường dẫn khi người dùng click vào ảnh/tên sách
        String idRaw = request.getParameter("id");
        if (idRaw != null) {
            try {
                int id = Integer.parseInt(idRaw);
                SachDAO dao = new SachDAO();
                Sach s = dao.getSachByID(id); // Gọi Model lấy thông tin sách
                
                if (s != null) {
                    // Đẩy dữ liệu sách sang trang detail.jsp
                    request.setAttribute("book", s);
                    request.getRequestDispatcher("detail.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        // Nếu không tìm thấy sách hoặc ID lỗi, quay về trang chủ
        response.sendRedirect("Home");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}