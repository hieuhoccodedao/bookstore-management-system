<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập Quản trị</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="admin-body">
    <div class="admin-login-box">
        <h2>PORTAL MANAGEMENT</h2>
        <div class="admin-subtitle">Hệ thống điều hành hệ thống</div>
        
        <c:if test="${not empty error}">
            <div style="color:red; margin-bottom:10px; text-align:center;">${error}</div>
        </c:if>
        
        <form action="AdminLogin" method="POST">
            <div class="admin-form-group">
                <label>Tài khoản</label>
                <i class="fa fa-user-shield"></i>
                <input type="text" name="adminUser" class="admin-input" required>
            </div>
            <div class="admin-form-group">
                <label>Mật mã</label>
                <i class="fa fa-lock"></i>
                <input type="password" name="adminPass" class="admin-input" required>
            </div>
            <button type="submit" class="btn-admin-login">Xác thực truy cập</button>
        </form>
    </div>
</body>
</html>