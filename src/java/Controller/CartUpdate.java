package Controller;

import entity.Item;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartUpdate", urlPatterns = {"/CartUpdate"})
public class CartUpdate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Lấy hành động (delete, increase, decrease) và ID của sách từ thẻ <a> bên cart.jsp gửi sang
        String action = request.getParameter("action");
        String idRaw = request.getParameter("id");
        
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        
        if (cart != null && idRaw != null) {
            try {
                int id = Integer.parseInt(idRaw);
                Item targetItem = null;
                
                // 1. Tìm cuốn sách cần xử lý trong giỏ hàng hiện tại
                for (Item item : cart) {
                    if (item.getSach() != null && item.getSach().getId() == id) {
                        targetItem = item;
                        break;
                    }
                }
                
                // 2. Tiến hành xử lý theo từng hành động cụ thể
                if (targetItem != null) {
                    if ("delete".equals(action)) {
                        cart.remove(targetItem); // Xóa hẳn cuốn sách khỏi giỏ hàng
                    } else if ("increase".equals(action)) {
                        targetItem.setQuantity(targetItem.getQuantity() + 1); // Tăng số lượng lên 1
                    } else if ("decrease".equals(action)) {
                        if (targetItem.getQuantity() > 1) {
                            targetItem.setQuantity(targetItem.getQuantity() - 1); // Giảm số lượng đi 1
                        } else {
                            cart.remove(targetItem); // Nếu giảm về 0 thì xóa luôn sách khỏi giỏ
                        }
                    }
                }
                
                // 3. Tính toán lại tổng số lượng sách hiển thị lên Icon xe đẩy màu đỏ trên thanh Header
                int totalQuantity = 0;
                for (Item item : cart) {
                    totalQuantity += item.getQuantity();
                }
                
                // Cập nhật lại giỏ hàng và số lượng mới vào Session
                session.setAttribute("cart", cart);
                session.setAttribute("size", totalQuantity);
                
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        // Xử lý xong, chuyển hướng điều hướng quay trở lại ngay chính trang cart.jsp để cập nhật giao diện
        response.sendRedirect("cart.jsp");
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