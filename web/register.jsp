<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng ký - Sách Online</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="wrapper">
        <jsp:include page="includes/header.jsp"/>
        
        <div class="login-wrapper">
            <div class="login-box">
                <h2>Tạo Tài Khoản Mới</h2>
                
                <c:if test="${not empty error}">
                    <div class="error-msg"><i class="fa fa-exclamation-circle"></i> ${error}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="success-msg"><i class="fa fa-check-circle"></i> ${success}</div>
                </c:if>
                
                <form action="Register" method="POST">
                    <div class="form-group">
                        <label>Họ và Tên</label>
                        <input type="text" name="txtFullname" class="form-control" required placeholder="Nhập họ và tên...">
                    </div>
                    <div class="form-group">
                        <label>Tên đăng nhập</label>
                        <input type="text" name="txtUsername" class="form-control" required placeholder="Nhập tài khoản muốn tạo...">
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" name="txtPassword" class="form-control" required placeholder="Nhập mật khẩu...">
                    </div>
                    <button type="submit" class="btn-submit btn-register">Đăng Ký Ngay</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>