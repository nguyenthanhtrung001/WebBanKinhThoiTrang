<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đổi mật khẩu</title>
</head>
<body>
	<div class="container_changePass">
		<div class="row">
			<div class="col-sm-12">
				<div class="rơw">
					<h2>Đổi mật khẩu</h2>
				</div>
				<div class=row>
					<form id="changePasswordForm" method="post"
						action="/WebBanKinh/user/change_password">
						<input placeholder="Nhập mật khẩu cũ..."
							type="password" id="currentPassword" name ="currentPassword"><br>
						<br>  <input placeholder="Nhập mật khẩu mới..." minlength=" 4"
						title="Mật khẩu mới phải chứa ít nhất 4 kí tự" required
							type="password" id="newPassword" name="newPassword"><br>
						<br><input placeholder="Xác nhận mật khẩu mới..." type="password" id="confirmPassword"
							minlength="4" required
							name="confirmPassword"><br> <br> <input
							type="submit" value="Thay đổi mật khẩu">
					</form>
				</div>
			</div>


		</div>
	</div>
	
	<script>
		$(document).ready(function() {
			$("#changePasswordForm").submit(function(event) {
				var newPassword = $("#newPassword").val();
				var confirmPassword = $("#confirmPassword").val();

				if (newPassword !== confirmPassword) {
					alert("Mật khẩu mới và xác nhận mật khẩu không trùng khớp!");
					event.preventDefault(); // Ngăn chặn việc gửi yêu cầu
				}
			});
		});
	</script>
</body>
</html>