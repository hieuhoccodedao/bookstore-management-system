<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thêm Sách Mới - Admin Panel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="dashboard-wrapper">
        <div class="admin-sidebar">
            <h3><i class="fa fa-cogs"></i> Admin Panel</h3>
            <p>Chào, ${adminAccount.fullname}</p>
            <nav style="margin-top: 30px;">
                <a href="AdminDashboard" class="admin-nav-item"><i class="fa fa-book"></i> Quản lý sách</a>
                <a href="#" class="admin-nav-item"><i class="fa fa-shopping-cart"></i> Đơn hàng</a>
                <a href="Home" class="admin-nav-item"><i class="fa fa-home"></i> Xem trang chủ</a>
                <a href="Logout" class="admin-nav-item" class="btn-delete"><i class="fa fa-sign-out-alt"></i> Đăng xuất</a>
            </nav>
        </div>

        <div class="admin-main-content">
            <div class="admin-card admin-form-container">
                <h2 class="admin-form-title"><i class="fa fa-plus-circle"></i> Thêm Sách Mới</h2>
                
                <form action="AdminAddBook" method="POST">
                    <div class="admin-form-group">
                        <label>Tên cuốn sách</label>
                        <input type="text" name="txtTenSach" class="admin-input admin-input-normal" required placeholder="Nhập tên sách...">
                    </div>
                    
                    <div class="admin-form-group">
                        <label>Tên file ảnh (VD: sach-java.jpg)</label>
                        <input type="text" name="txtHinhAnh" class="admin-input admin-input-normal" required placeholder="Nhập tên file ảnh...">
                        <small class="admin-form-note">* Hãy nhớ copy file ảnh tương ứng vào thư mục /image của project</small>
                    </div>
                    
                    <div class="admin-form-group">
                        <label>Giá bán (VNĐ)</label>
                        <input type="number" name="txtGia" class="admin-input admin-input-normal" required placeholder="Ví dụ: 150000">
                    </div>
                    
                    <div class="admin-btn-group">
                        <button type="submit" class="btn-admin-login btn-save">Lưu vào Database</button>
                        <a href="AdminDashboard" class="btn-admin-login btn-cancel">Hủy bỏ</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>