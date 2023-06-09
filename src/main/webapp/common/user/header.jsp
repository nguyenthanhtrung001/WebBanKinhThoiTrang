<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <nav style="padding: 1em 1.5em;font-size: 16px;" class="navbar navbar-expand-lg navbar-dark bg-dark">
  
  <a class="navbar-brand" href="#">Eye Glasses Shop</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/"><i class="fas fa-home"></i> Trang Chủ</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/user/product/home"><i class="fas fa-glasses"></i> Sản Phẩm</a>
      </li>
     
    </ul>
    <form class="form-inline ml-auto" >
      <input  value="${paging.search}" name="search" style="width:350px; height:32px" class="form-control mr-sm-2" type="search" placeholder="Nhập từ khóa tìm kiếm..." aria-label="Search">
      <button style=" height:32px" class="btn btn-outline-light my-2 my-sm-0" type="submit">Tìm Kiếm</button>
    </form>
    <ul class="navbar-nav ml-auto">
     <li class="nav-item">
        <a class="nav-link" href="/user/cart"><i class="fas fa-shopping-cart"></i> Giỏ Hàng</a>
      </li>
      <% 
    Boolean loginValue = (Boolean) session.getAttribute("login");
    boolean isLoggedin = loginValue != null && loginValue.booleanValue();
    
    if (!isLoggedin) {
%>
    <li class="nav-item">
        <a class="nav-link" href="/login"><i class="fas fa-user"></i> Login</a>
    </li>
<% } else { %>
   <%--  <li class="nav-item">
        <a class="nav-link" href="/WebBanKinh/user/profile"><i class="fas fa-user"></i><em> ${sessionScope.user.name}</em></a>
    </li> --%>
    
    <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <i class="fas fa-user"></i><em> ${sessionScope.user.name}</em>
    </a>
    <div class="dropdown-menu" style="font-size:18px;" aria-labelledby="userDropdown">	
        <!-- Các mục trong danh sách -->
        <a class="dropdown-item bg-light" href="/user/profile">Thông tin cá nhân</a>
        <a class="dropdown-item bg-light" href="#">Xem danh sách phiếu đổi</a>
        <a class="dropdown-item bg-light" href="/user/history">Xem lịch sử đơn đặt hàng</a>
        <a class="dropdown-item bg-light" href="#">Đổi mật khẩu</a>
        <a class="dropdown-item bg-light" href="/user/logout">Đăng xuất</a>

        <!-- ...Thêm mục khác nếu cần -->
    </div>
</li>

    
<% } %>
      
     
     
    </ul>
   
  </div>
</nav>
