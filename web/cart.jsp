<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Giỏ hàng của bạn - Sách Online</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
</head>
<body>
    <div class="wrapper">
        <jsp:include page="includes/header.jsp"/>
        
        <div class="container cart-page-container">
            <h2 class="page-title">Giỏ hàng của bạn</h2>
            
            <c:choose>
                <%-- Trường hợp trong Session đã tồn tại giỏ hàng và có ít nhất 1 sản phẩm --%>
                <c:when test="${not empty sessionScope.cart}">
                    <table class="cart-table">
                        <thead>
                            <tr>
                                <th>Hình ảnh</th>
                                <th>Tên sách</th>
                                <th>Giá tiền</th>
                                <th>Số lượng</th>
                                <th>Thành tiền</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- Khởi tạo biến tích lũy tổng số tiền hóa đơn ban đầu bằng 0 --%>
                            <c:set var="totalMoney" value="0"/>
                            
                            <%-- Vòng lặp duyệt qua danh sách các thực thể Item trong giỏ hàng --%>
                            <c:forEach items="${sessionScope.cart}" var="item">
                                <%-- Tính tổng tiền của riêng dòng sản phẩm hiện tại: Thành tiền = Đơn giá * Số lượng --%>
                                <c:set var="itemTotal" value="${item.sach.gia * item.quantity}"/>
                                <%-- Cộng dồn số tiền dòng hiện tại vào tổng hóa đơn chung --%>
                                <c:set var="totalMoney" value="${totalMoney + itemTotal}"/>
                                
                                <tr>
                                    <td>
                                        <img class="cart-img" src="${empty item.sach.hinhAnh ? 'https://via.placeholder.com/150x200?text=No+Image' : pageContext.request.contextPath}/image/${item.sach.hinhAnh}" alt="${item.sach.tenSach}">  
                                    </td>
                                    
                                    <td class="cart-book-title">${item.sach.tenSach}</td>
                                    
                                    <td><fmt:formatNumber value="${item.sach.gia}" type="currency" currencySymbol="đ"/></td>
                                    
                                    <td>
                                        <div class="quantity-control">
                                            <a href="CartUpdate?action=decrease&id=${item.sach.id}" class="btn-qty">-</a>
                                            <span class="qty-number">${item.quantity}</span>
                                            <a href="CartUpdate?action=increase&id=${item.sach.id}" class="btn-qty">+</a>
                                        </div>
                                    </td>
                                    
                                    <td class="item-total-price">
                                        <fmt:formatNumber value="${itemTotal}" type="currency" currencySymbol="đ"/>
                                    </td>
                                    
                                    <td>
                                        <a href="CartUpdate?action=delete&id=${item.sach.id}" class="btn-delete" onclick="return confirm('Bạn chắc chắn muốn xóa cuốn sách này khỏi giỏ hàng?')">
                                            <i class="fa fa-trash"></i> Xóa
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                    <div class="total-container">
                        <p>Tổng cộng hóa đơn: <span class="grand-total"><fmt:formatNumber value="${totalMoney}" type="currency" currencySymbol="đ"/></span></p>
                        <a href="#" class="btn-checkout" onclick="showQR(event)">Tiến hành đặt hàng</a>
                    </div>
                </c:when>
                
                <%-- Trường hợp giỏ hàng chưa được khởi tạo hoặc trống không có sản phẩm nào --%>
                <c:otherwise>
                    <p class="empty-cart-msg">Giỏ hàng trống rỗng. Hãy quay lại trang chủ mua sách nhé!</p>
                </c:otherwise>
            </c:choose>
            
            <div class="back-home-box">
                <a href="Home" class="link-back"><i class="fa fa-arrow-left"></i> Tiếp tục mua hàng</a>
            </div>
        </div>
    </div>
        <div id="qrModal" class="qr-modal">
        <div class="qr-modal-content">
            <span class="close-qr-btn">&times;</span>
            <h3><i class="fa fa-check-circle"></i> Tiến hành thanh toán</h3>
            <p>Vui lòng quét mã QR dưới đây bằng ứng dụng ngân hàng hoặc Momo để hoàn tất đơn hàng.</p>
            
            <img src="${pageContext.request.contextPath}/image/my_qr.jpg" alt="Mã QR Thanh Toán">
        </div>
    </div>

    <script>
        var modal = document.getElementById("qrModal");
        var span = document.getElementsByClassName("close-qr-btn")[0];

        // Hàm mở hộp thoại QR khi bấm nút
        function showQR(event) {
            event.preventDefault(); // Ngăn trình duyệt load lại trang
            modal.style.display = "flex";
        }

        // Tắt khi bấm dấu X
        span.onclick = function() {
            modal.style.display = "none";
        }

        // Tắt khi click ra vùng đen bên ngoài hộp thoại
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</body>
</html>