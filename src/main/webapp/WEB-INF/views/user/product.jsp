<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Danh Sách sản phẩm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>



</head>
<body>
		<div class="container">
	  <div class="row">
	    <div class="col-md-12">
	      <div class="d-flex justify-content-between">
	        <h2 class="movie-title">Mắt Kính thời trang</h2>
	        <a class="link" href="">Mắt Kính Nam</a>
	      </div>
	      <hr>
	      <div class="row">
	        <c:forEach var="s" items="${products}">
	          <div class="col-md-3">
	            <div class="card mb-4 box-shadow ">
	              <a href="/WebBanKinh/user/product/detail/ma=${s.id}">
	                <img class="card-img-top" src="https://via.placeholder.com/300x200.png?text=${s.name}" alt="${s.name}">
	              </a>
	              <div class="card-body">
	                <a href="/WebBanKinh/user/product/detail/ma=${s.id}">
	                  <h4 class="card-title">${s.name}</h4>
	                </a>
	                <div class="movie-info-wrap">
	                  <p class="movie-info">Thương Hiệu: <span class="movie-info-detail">${s.branch}</span></p>
	                </div>
	                <div class="movie-info-wrap">
	                  <p class="movie-info">Màu Sắc:<span class="movie-info-detail">${s.color} </p></span>
	                </div>
					<div class="d-flex justify-content-between align-items-center">
						<div class="btn-wrap">
							<a href="#" class="btn btn-buy-ticket d-inline-block"> <i class="fa-solid fa-ticket"></i> Giá
							</a>  </div>
						</div>
					</div>
	            </div>
	          </div>
	        </c:forEach>
	      </div>
	    </div>
	  </div>
	</div>
		


    
</body>
</html>