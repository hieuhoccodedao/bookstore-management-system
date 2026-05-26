package Controller;

import Models.SachDAO;
import entity.Item;
import entity.Sach;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Lấy ID sách
        String bookIdRaw = request.getParameter("id");
        
        // 2. Lấy số lượng từ URL (gửi từ trang chi tiết)
        String qtyRaw = request.getParameter("quantity");
        
        // Mặc định mua 1 cuốn (Dùng khi bấm thêm vào giỏ từ trang chủ)
        int buyQuantity = 1; 
        if (qtyRaw != null) {
            try {
                buyQuantity = Integer.parseInt(qtyRaw);
                if(buyQuantity < 1) buyQuantity = 1; // Ngăn chặn số lượng âm
            } catch (NumberFormatException e) {
                buyQuantity = 1;
            }
        }
        
        if (bookIdRaw != null) {
            try {
                int id = Integer.parseInt(bookIdRaw); 
                
                SachDAO dao = new SachDAO();
                Sach s = dao.getSachByID(id); 

                if (s != null) {
                    HttpSession session = request.getSession();
                    List<Item> cart = (List<Item>) session.getAttribute("cart");

                    if (cart == null) {
                        cart = new ArrayList<>();
                    }

                    // Kiểm tra xem sách đã có trong giỏ chưa
                    boolean isExist = false;
                    for (Item item : cart) {
                        if (item.getSach() != null && item.getSach().getId() == s.getId()) {
                            // Nếu đã có, cộng thêm số lượng người dùng vừa chọn (thay vì luôn là + 1)
                            item.setQuantity(item.getQuantity() + buyQuantity); 
                            isExist = true;
                            break;
                        }
                    }

                    // Nếu chưa có, thêm mới với số lượng vừa chọn
                    if (!isExist) {
                        cart.add(new Item(s, buyQuantity)); 
                    }

                    // Cập nhật lại Session
                    session.setAttribute("cart", cart);
                    
                    // Tính lại tổng số lượng Badge đỏ
                    int totalQuantity = 0;
                    for (Item item : cart) {
                        totalQuantity += item.getQuantity();
                    }
                    session.setAttribute("size", totalQuantity);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        // Điều hướng lại trang cũ thay vì luôn về Home
        // Giúp trải nghiệm mượt hơn khi bấm thêm từ trang chi tiết
        String referer = request.getHeader("Referer");
        if(referer != null && !referer.isEmpty()) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("Home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}