<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Eye Glasses Shop</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />


</head>
<body>

  <nav style="padding: 1em 2em;font-size: 16px;" class="navbar navbar-expand-lg navbar-dark bg-dark">
  
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
    <form class="form-inline ml-auto" >
      <input  style="width:350px;" class="form-control mr-sm-2" type="search" placeholder="Mắt kính thời trang" aria-label="Search">
      <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Tìm Kiếm</button>
    </form>
    <ul class="navbar-nav ml-auto">
    <li class="nav-item">
        <a class="nav-link" href="/WebBanKinh/user/cart"><i class="fas fa-shopping-cart"></i> Cart</a>
      </li>
     <% 
    Boolean loginValue = (Boolean) session.getAttribute("login");
    boolean isLoggedin = loginValue != null && loginValue.booleanValue();
    
    if (!isLoggedin) {
%>
    <li class="nav-item">
        <a class="nav-link" href="/WebBanKinh/login"><i class="fas fa-user"></i> Login</a>
    </li>
<% } else { %>
    
    <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <i class="fas fa-user"></i><em> ${sessionScope.user.name}</em>
    </a>
    <div class="dropdown-menu" style="font-size:18px;" aria-labelledby="userDropdown">	
        <!-- Các mục trong danh sách -->
        <a class="dropdown-item bg-light" href="/WebBanKinh/user/profile">Thông tin cá nhân</a>
        <a class="dropdown-item bg-light" href="#">Xem danh sách phiếu đổi</a>
        <a class="dropdown-item bg-light" href="/WebBanKinh/user/history">Xem lịch sử đơn đặt hàng</a>
        <a class="dropdown-item bg-light" href="#">Đổi mật khẩu</a>
        <a class="dropdown-item bg-light" href="/WebBanKinh/user/logout">Đăng xuất</a>

        <!-- ...Thêm mục khác nếu cần -->
    </div>
<% } %>
       
    </ul>
  </div>
</nav>



  <div class="jumbotron text-center">
   <div class="container">
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="https://theme.hstatic.net/1000269337/1000981122/14/slideShow_f1_1.png?v=215" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="https://theme.hstatic.net/1000269337/1000981122/14/slideShow_f1_3.png?v=215" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="https://theme.hstatic.net/1000269337/1000981122/14/slideShow_f1_2.png?v=215" class="d-block w-100" alt="...">
      </div>
    </div>

    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
    
  </div>
<div class="jumbotron text-center">
    <h1>Welcome to Eye Glasses Shop</h1>
    <p>Shop for the best eye glasses and sunglasses online</p>
    <a href="#" class="btn btn-primary btn-lg">Shop Now</a>
  </div>
  
</div>

  </div>

  <div class="container">
    <div class="row">
      <div class="col-sm-4 " >
        <img src="https://images.pexels.com/photos/2811088/pexels-photo-2811088.jpeg?auto=compress&cs=tinysrgb&w=1600" alt="Eye Glass 1" class="img-thumbnail ">
        <h4 >Ray-Ban Wayfarer Classic</h4>
        <p>Kính được làm từ vật liệu acetate cao cấp, Kính có khả năng chống tia UV và có sẵn trong nhiều màu sắc khác nhau.</p>
        <a href="#" class="btn btn-primary">Add to Cart</a>
      </div>
      <div class="col-sm-4">
        <img src="https://images.pexels.com/photos/4054374/pexels-photo-4054374.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Eye Glass 2" class="img-thumbnail">
        <h4>Prada Linea Rossa PS53PS</h4>
        <p>Một mẫu kính thể thao với thiết kế đa dạng, chất liệu kim loại bền và khả năng chống tia UV. </p>
        <a href="#" class="btn btn-primary">Add to Cart</a>
      </div>
       <div class="col-sm-4">
    <img src="https://images.pexels.com/photos/2887718/pexels-photo-2887718.jpeg?auto=compress&cs=tinysrgb&w=1600" alt="Eye Glass 3" class="img-thumbnail">
    <h4>Dior Homme Blacktie227S</h4>
    <p>Một mẫu kính mắt cổ điển với khung mạ vàng hoặc bạc,Kính có khả năng chống tia UV, thiết kế đơn giản nhưng sang trọng, phù hợp cho các buổi tiệc hoặc sự kiện thời trang.</p>
    <a href="#" class="btn btn-primary">Add to Cart</a>

  </div>
</div>
</div>
<br>

  <div class="container-fluid bg-dark text-white py-3">
    <div class="row">
      <div class="col-sm-4" style="margin-left: ;: 10px">
        <h3>About Us</h3>
        <p>Đa Dạng - Thời Thượng - Đẳng Cấp</p>
      </div>
      <div class="col-sm-6">
        <h3>Contact Us</h3>
        <p>Man Thiện, Quận 9, TP.Thủ Đức, TP.HCM<br>Phone: +1 234 5678 910<br>Email: info@eyeglassshop.com</p>
      </div>
      <div class="col-sm-2">
        <h3>Follow Us</h3>
        <a href="#" class="btn btn-outline-light"><i class="fab fa-facebook-f"></i></a>
        <a href="#" class="btn btn-outline-light"><i class="fab fa-twitter"></i></a>
        <a href="#" class="btn btn-outline-light"><i class="fab fa-instagram"></i></a>
      </div>
    </div>
  </div>
</body>
</html>