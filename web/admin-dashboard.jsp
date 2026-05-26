<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Dashboard - Quản lý sách</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="dashboard-wrapper">
        <div class="admin-sidebar">
            <h3><i class="fa fa-cogs"></i> Admin Panel</h3>
            <p>Chào, ${adminAccount.fullname}</p>
            <nav style="margin-top: 30px;">
                <a href="#" class="admin-nav-item"><i class="fa fa-book"></i> Quản lý sách</a>
                <a href="#" class="admin-nav-item"><i class="fa fa-shopping-cart"></i> Đơn hàng</a>
                <a href="Home" class="admin-nav-item"><i class="fa fa-home"></i> Xem trang chủ</a>
                <a href="Logout" class="admin-nav-item" style="color: #e74c3c;"><i class="fa fa-sign-out-alt"></i> Đăng xuất</a>
            </nav>
        </div>

        <div class="admin-main-content">
            <div class="admin-card">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                   <h2>Danh sách sản phẩm</h2>
                    <a href="AdminAddBook" style="text-decoration: none;">
                    <button class="btn-admin-login" style="width: auto; padding: 10px 20px; background: #27ae60; border: none; cursor: pointer; color: white; border-radius: 4px;">
                    <i class="fa fa-plus"></i> Thêm sách mới
        </button>
    </a>
</div>

                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Ảnh</th>
                            <th>Tên sách</th>
                            <th>Giá bán</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listS}" var="s">
                            <tr>
                                <td>${s.id}</td>
                                <td><img src="image/${s.hinhAnh}" width="40" height="50" style="object-fit: cover;"></td>
                                <td>${s.tenSach}</td>
                                <td>${s.gia} đ</td>
                                <td>
                                    <a href="AdminLoadBook?id=${s.id}" class="btn-edit"><i class="fa fa-edit"></i> Sửa</a>
                                    <a href="#" class="btn-delete"><i class="fa fa-trash"></i> Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>