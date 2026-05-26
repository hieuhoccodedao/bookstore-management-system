<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header-container">
    <div class="logo">
        <a href="Home" style="color: white; text-decoration: none;">
            <i class="fa fa-book"></i> Sách Online
        </a>
    </div>
    <div class="search-bar">
        <form action="Home" method="GET" style="display: flex; width: 100%;">
            <input type="text" name="txtSearch" value="${txtSearch}" placeholder="Nhập tiêu đề sách...">
            
            <button type="submit" style="display: flex; align-items: center; justify-content: center;">
                <svg viewBox="0 0 24 24" width="18" height="18" stroke="white" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round">
                    <circle cx="11" cy="11" r="8"></circle>
                    <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                </svg>
            </button>
        </form>
    </div>
    
    <div class="user-actions">
        <a href="AdminLogin" style="color: #f39c12; font-weight: bold;"><i class="fa fa-user-shield"></i> Quản trị</a>

        <c:choose>
            <c:when test="${not empty sessionScope.account}">
                <a href="#"><i class="fa fa-user"></i> Chào, ${sessionScope.account.fullname}</a>
                <a href="Logout"><i class="fa fa-sign-out-alt"></i> Đăng xuất</a>
            </c:when>
            <c:otherwise>
                <a href="Login"><i class="fa fa-user"></i> Đăng nhập</a>
                <a href="Register"><i class="fa fa-user-plus"></i> Đăng ký</a>
            </c:otherwise>
        </c:choose>
        <a href="cart.jsp" class="cart-link">
            <div class="cart-icon">
                <svg class="cart-svg-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <circle cx="9" cy="21" r="1"></circle>
                    <circle cx="20" cy="21" r="1"></circle>
                    <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
                </svg>
                <span class="cart-badge">${not empty size ? size : 0}</span>
            </div>
        </a>
    </div>
</div>