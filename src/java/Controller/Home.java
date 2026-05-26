package Controller;

import Models.SachDAO;
import entity.Sach;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); // Hỗ trợ gõ tìm kiếm tiếng Việt có dấu
        
        // 1. Đọc bộ lọc giá
        String priceOption = request.getParameter("priceOption");
        if (priceOption == null) priceOption = "all";
        
        // 2. Đọc từ khóa tìm kiếm
        String txtSearch = request.getParameter("txtSearch");
        
        // 3. Đọc vị trí trang hiện tại
        String pageRaw = request.getParameter("page");
        int pageIndex = 1; 
        if (pageRaw != null) {
            try { pageIndex = Integer.parseInt(pageRaw); } 
            catch (NumberFormatException e) { pageIndex = 1; }
        }
        
        SachDAO dao = new SachDAO();
        
        // 4. Tính toán tổng số trang theo điều kiện (Giá + Tìm kiếm)
        int totalBooks = dao.getTotalBooks(priceOption, txtSearch);
        int endPage = totalBooks / 8;
        if (totalBooks % 8 != 0) endPage++; 
        
        // 5. Lấy dữ liệu sách
        List<Sach> listS = dao.getBooksPaging(priceOption, txtSearch, pageIndex);
        
        // 6. Gửi dữ liệu sang JSP
        request.setAttribute("listBooks", listS);
        request.setAttribute("selectedPrice", priceOption);
        request.setAttribute("txtSearch", txtSearch); // Giữ lại từ khóa để hiển thị
        request.setAttribute("endP", endPage);       
        request.setAttribute("tag", pageIndex);     
        
        request.getRequestDispatcher("base.jsp").forward(request, response);
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