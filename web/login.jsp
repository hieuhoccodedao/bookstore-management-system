<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập - Sách Online</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="wrapper">
        <jsp:include page="includes/header.jsp"/>
        
        <div class="login-wrapper">
            <div class="login-box">
                <h2>Đăng Nhập Hệ Thống</h2>
                
                <c:if test="${not empty error}">
                    <div class="error-msg"><i class="fa fa-exclamation-circle"></i> ${error}</div>
                </c:if>
                
                <form action="Login" method="POST">
                    <div class="form-group">
                        <label>Tên đăng nhập</label>
                        <input type="text" name="txtUsername" class="form-control" required placeholder="Nhập tài khoản...">
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" name="txtPassword" class="form-control" required placeholder="Nhập mật khẩu...">
                    </div>
                    <button type="submit" class="btn-submit">Đăng Nhập</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>