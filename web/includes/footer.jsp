<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="footer-content">
    <h2 class="student-name-egg" onclick="showProfile()">
        Đức Hiếu Đẹp Trai TIN17A2HN
    </h2>
</div>

<div id="profileModal" class="profile-modal">
    <div class="profile-modal-content">
        <span class="close-profile-btn">&times;</span>
        <h3>Thông Tin Sinh Viên</h3>
        
        <img src="${pageContext.request.contextPath}/image/sinhvien.jpg" alt="Đức Hiếu TIN17A2HN">
    </div>
</div>

<script>
    var profileModal = document.getElementById("profileModal");
    var closeProfile = document.getElementsByClassName("close-profile-btn")[0];

    function showProfile() {
        profileModal.style.display = "flex";
    }

    closeProfile.onclick = function() {
        profileModal.style.display = "none";
    }

    window.addEventListener('click', function(event) {
        if (event.target == profileModal) {
            profileModal.style.display = "none";
        }
    });
</script>