<!-- Nội dung HTML của view của bạn -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Modal thông báo thành công -->
<div id="successModal" class="modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thông báo thành công</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Nội dung thông báo thành công -->
                Cập nhật thông tin thành công 
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<!-- Trong view của bạn -->
<script>
    var thongBao = "${thongBao}"; // Lấy giá trị biến thongBao từ model

    if (thongBao === "success") {
        // Hiển thị modal thông báo thành công
        var successModal = document.getElementById("successModal");
        successModal.classList.add("show");
        successModal.style.display = "block";
        successModal.setAttribute("aria-modal", "true");

        // Ẩn modal khi nhấp vào nút Đóng
        var closeModalBtn = successModal.querySelector(".close");
        closeModalBtn.addEventListener("click", function() {
            successModal.classList.remove("show");
            successModal.style.display = "none";
            successModal.removeAttribute("aria-modal");
        });

        // Ẩn modal khi nhấp vào bên ngoài modal
        window.addEventListener("click", function(event) {
            if (event.target === successModal) {
                successModal.classList.remove("show");
                successModal.style.display = "none";
                successModal.removeAttribute("aria-modal");
            }
        });
    }
</script>
