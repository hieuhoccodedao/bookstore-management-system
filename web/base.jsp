<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sách Online - Hệ thống quản lý</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/paging.css"> 
</head>
<body>
    <div class="wrapper">
        <header>
            <jsp:include page="includes/header.jsp"/>
        </header>
        
        <div class="container">
            <aside class="sidebar">
                <jsp:include page="includes/sidebar.jsp"/>
            </aside>
            
            <main class="content-area">
                <jsp:include page="includes/homeContent.jsp"/>
            </main>
        </div>
        
        <footer>
            <jsp:include page="includes/footer.jsp"/>
        </footer>
    </div>
</body>
</html>