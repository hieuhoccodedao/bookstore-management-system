<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Danh sách sách mới nhất</h2>

<div class="products-grid">
    <c:choose>
        <c:when test="${not empty listBooks}">
            <c:forEach items="${listBooks}" var="b">
                <div class="product-card">
                    
                    <a href="Detail?id=${b.id}">
                        <img src="${empty b.hinhAnh ? 'https://via.placeholder.com/150x200?text=No+Image' : pageContext.request.contextPath}/image/${b.hinhAnh}" alt="${b.tenSach}">  
                    </a>
                    
                    <div class="product-title">
                        <a href="Detail?id=${b.id}" style="color: inherit; text-decoration: none;">
                            ${b.tenSach}
                        </a>
                    </div>
                    
                    <div class="product-price">
                        <fmt:formatNumber value="${b.gia}" type="currency" currencySymbol="đ"/>
                    </div>
                    
                    <a href="AddToCart?id=${b.id}" class="btn-buy">Thêm vào giỏ</a>
                </div>
            </c:forEach>
        </c:when>
        
        <c:otherwise>
            <p class="empty-message">Không tìm thấy cuốn sách nào!</p>
        </c:otherwise>
    </c:choose>
</div>

<c:if test="${endP > 1}">
    <div class="pagination">
        <c:forEach begin="1" end="${endP}" var="i">
            <a href="Home?priceOption=${selectedPrice}&txtSearch=${txtSearch}&page=${i}" class="${tag == i ? 'active' : ''}">${i}</a>
        </c:forEach>
    </div>
</c:if>