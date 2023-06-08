<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Ký</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="<c:url value='/templates/login/register.css'/>"/>

<style>

.page {
	background-image: url("https://plus.unsplash.com/premium_photo-1683309565422-77818a287060?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
	background-size: cover;
	background-repeat: no-repeat;
}

.dropdown:hover .dropdown-menu {
	display: block;
	margin-top: 0;
}
.icon{
	color: #fff;
}
</style>

</head>

<body class="page">
		 <nav style="padding: 8px 2em;font-size: 16px;" class="navbar navbar-expand-lg navbar-dark bg-dark">
  
  <a class="navbar-brand" href="#">Eye Glasses Shop</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/WebBanKinh"><i class="fas fa-home"></i> Trang Chủ</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/WebBanKinh/user/product/home"><i class="fas fa-glasses"></i> Sản Phẩm</a>
      </li>
     
    </ul>
    
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="/WebBanKinh/login"><i class="fas fa-user"></i> Login</a>
      </li>
   
      <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-shopping-cart"></i> Cart</a>
      </li>
    </ul>
  </div>
</nav>
	
	<div class="wrap">
		<div class="login"  style = "margin-top: 20%;">
			<!--<i class="fa-solid fa-xmark iconclose1"></i>-->
			<h2 class="login-header">Đăng kí</h2>
			<div style="color: #ff3366; font-size: 12px; margin-top: 5px; margin-bottom: 0px;">${message }</div>			
			<form:form class="login-form" method="POST" action="/WebBanKinh/register/insert" modelAttribute="KhachHang">
				<label class="form-label" for="">Tên<span style="color: red;">*</span></label>
				<div class="input-container">
					<i class="fa-solid fa-user icon"></i> 
					<form:input type="text" pattern="[^\d]+" class="input-content" placeholder="Họ và Tên" path="name" />
<p
						style="color: #ff3366; font-size: 12px; margin-top: 5px; margin-bottom: 0px;">
						<form:errors path="name" cssClass="errors" />
					</p>
				</div>
				
				<div class="input-container">
					<i class="fa-solid fa-globe icon"></i> 
					<form:select path="gender" id="cars" class="select input-content" placeholder="Giới tính">
						<option value="true">Nam</option>
						<option value="false">Nữ</option>
					</form:select>
				</div>
				
				<div class="input-container">
					<i class="fa-solid fa-phone icon"></i>
				 	<form:input type="text" class="input-content" placeholder="Số điện thoại" path="phoneNumber"/>
				 	<p
						style="color: #ff3366; font-size: 12px; margin-top: 5px; margin-bottom: 0px;">
						<form:errors path="phoneNumber" cssClass="errors" />
					</p>
				</div>
				
				<div class="input-container">
					<i class="fa-solid fa-envelope icon"></i> 
					<form:input type="email" class="input-content" placeholder="Email" path="account.email"/>
					<p
						style="color: #ff3366; font-size: 12px; margin-top: 5px; margin-bottom: 0px;">
						<form:errors path="account.email" cssClass="errors" />
					</p>
				</div>
				
				<div class="input-container">
					<i class="fa-solid fa-lock icon"></i>
					<form:input type="password" class="input-content" placeholder="Mật khẩu" path="account.password"/>
					<p
						style="color: #ff3366; font-size: 12px; margin-top: 5px; margin-bottom: 0px;">
						<form:errors path="account.password" cssClass="errors" />
					</p>
				</div>
				
				<div class="input-container">
					<i class="fa-solid fa-globe icon"></i> 
					<form:select path="address" id="cars" class="select">
						<option class="opacity-value" value>Area</option>
						<option value="Hồ Chí Minh">Hồ Chí Minh</option>
						<option value="Hà Nội">Hà Nội</option>
						<option value="Vùng Tàu">Vũng Tàu</option>
						<option value="Đồng Nai">Đồng Nai</option>
						<option value="Nam Định">Nam Định</option>
						<option value="Đăk Lăk">Đăk Lăk</option>
						<option value="Hải Phòng">Hải Phòng</option>
						<option value="Thái Bình">Thái Bình</option>
					</form:select>
				</div>
				
				<div>
				
				
					<label class="form-label" for="">Ngày sinh</label> 
						<select name="day" id="day" class="select-date" required>
							<option value>Ngày</option>
							<option value="01">1</option>
							<option value="02">2</option>
							<option value="03">3</option>
							<option value="04">4</option>
							<option value="05">5</option>
							<option value="06">6</option>
							<option value="07">7</option>
							<option value="08">8</option>
							<option value="09">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
						</select>

						<select name="month" id="month" class="select-date" required>
							<option value>Tháng</option>
							<option value="01">1</option>
							<option value="02">2</option>
							<option value="03">3</option>
							<option value="04">4</option>
							<option value="05">5</option>
							<option value="06">6</option>
							<option value="07">7</option>
							<option value="08">8</option>
							<option value="09">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>

						<select name="year" id="year" class="select-date" required>
							<option value>Năm</option>
							
							<option value="2008">2008</option>
							<option value="2007">2007</option>
							<option value="2006">2006</option>
							<option value="2005">2005</option>
							<option value="2004">2004</option>
							<option value="2003">2003</option>
							<option value="2002">2002</option>
							<option value="2001">2001</option>
							<option value="2000">2000</option>
							<option value="1991">1999</option>
							<option value="1991">1998</option>
							<option value="1991">1997</option>
							<option value="1991">1996</option>
							<option value="1991">1995</option>
							<option value="1991">1994</option>
							<option value="1991">1993</option>
							<option value="1991">1992</option>
							<option value="1991">1991</option>
							<option value="1990">1990</option>
							<option value="1989">1989</option>
							
						</select>
				</div>
				<div class="submit-row">
					<button type="submit" class="btn-submit">Đăng kí</button>
					<!--<div class="term-policy-container"> By signing up, you agree to our&nbsp; <a href="#" class="link">
							Terms of Use </a> &nbsp;and&nbsp; <a href="#" class="link">
							Privacy Policy </a>
					</div>-->
				</div>
			</form:form>
			<div class="login-footer">Bạn đã có tài khoản? 
			<a href="/WebBanKinh/login" class="sign-up-link">Đăng nhập</a>
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    
</body>
</html>