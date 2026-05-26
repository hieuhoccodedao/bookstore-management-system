<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${book.tenSach} - Sách Online</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
</head>
<body>
    <div class="wrapper">
        <jsp:include page="includes/header.jsp"/>
        
        <div class="container detail-page-container">
            
            <div class="detail-left">
                <div class="image-box">
                    <img src="${empty book.hinhAnh ? 'https://via.placeholder.com/300x400?text=No+Image' : pageContext.request.contextPath}/image/${book.hinhAnh}" alt="${book.tenSach}">
                </div>
            </div>
            
            <div class="detail-right">
                <h1 class="book-main-title">${book.tenSach}</h1>
                
                <div class="shopee-price-card">
                    <span class="price-label">Giá bán:</span>
                    <span class="price-value">
                        <fmt:formatNumber value="${book.gia}" type="currency" currencySymbol="đ"/>
                    </span>
                </div>
                
                <div class="shipping-policy">
                    <div class="policy-item">
                        <span class="policy-label">Vận chuyển:</span>
                        <span class="policy-text"><i class="fa fa-truck text-blue"></i> Miễn phí vận chuyển toàn quốc cho đơn hàng từ 300.000đ</span>
                    </div>
                    <div class="policy-item" style="margin-top: 15px;">
                        <span class="policy-label">Chính sách:</span>
                        <span class="policy-text"><i class="fa fa-rotate-left text-green"></i> Trả hàng miễn phí trong vòng 7 ngày nếu lỗi sản xuất</span>
                    </div>
                </div>
                
                <div class="quantity-selector">
                    <span class="qty-label">Số lượng:</span>
                    <div class="qty-control-box">
                        <button type="button" class="btn-qty-adjust" onclick="changeQty(-1)">-</button>
                        <input type="text" id="buy-quantity" value="1" readonly>
                        <button type="button" class="btn-qty-adjust" onclick="changeQty(1)">+</button>
                    </div>
                </div>
                
                <div class="action-buttons-group">
                    <a href="AddToCart?id=${book.id}&quantity=1" id="add-to-cart-link" class="btn-add-to-cart">
                        <i class="fa fa-cart-plus"></i> Thêm Vào Giỏ Hàng
                    </a>
                    <a href="#" class="btn-buy-now" onclick="showQR(event)">Mua Ngay</a> 
                </div>
                
                <div class="back-link-box">
                    <a href="Home"><i class="fa fa-arrow-left"></i> Quay lại danh sách sách</a>
                </div>
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
        // --- XỬ LÝ HỘP THOẠI QR ---
        var modal = document.getElementById("qrModal");
        var span = document.getElementsByClassName("close-qr-btn")[0];

        function showQR(event) {
            event.preventDefault(); 
            modal.style.display = "flex";
        }

        span.onclick = function() {
            modal.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        // --- XỬ LÝ TĂNG GIẢM SỐ LƯỢNG ---
        function changeQty(amount) {
            let qtyInput = document.getElementById('buy-quantity');
            let currentQty = parseInt(qtyInput.value);
            let newQty = currentQty + amount;
            
            if (newQty >= 1) { // Đảm bảo số lượng không nhỏ hơn 1
                qtyInput.value = newQty;
                
                // Cập nhật lại số lượng vào link của nút "Thêm vào giỏ hàng"
                let addToCartLink = document.getElementById('add-to-cart-link');
                addToCartLink.href = "AddToCart?id=${book.id}&quantity=" + newQty;
            }
        }
    </script>
</body>
</html>