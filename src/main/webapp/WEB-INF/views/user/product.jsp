<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh Sách sản phẩm</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>




<style>
    .card-img-top {
      transition: transform 0.3s;
    }
    
    .card:hover .card-img-top {
      transform: scale(1.1);
    }
    
    
  </style>




</head>
<body>
		<div class="container">
	  <div class="row">
	    <div class="col-md-12">
	      <div class="d-flex justify-content-between">
	        <h1 class="movie-title">Mắt Kính Thời Trang</h1>
	        
	      </div>
	      <div class="d-flex">
	      <a id="allLink" style="margin-right: 12px; font-size: 14px;color: #188f34;" class="link" href="/WebBanKinh/user/product/home">Tất cả</a>
  <a id="namLink" style="margin-right: 12px; font-size: 14px;color: #188f33;" class="link" href="/WebBanKinh/user/product/home/MKNM">Mắt Kính Nam</a>
  <a id="nuLink" style="margin-right: 12px; font-size: 14px;color: #188f33;" class="link" href="/WebBanKinh/user/product/home/MKN">Mắt Kính Nữ</a>
  <a id="babyLink" style="margin-right: 8px; font-size: 14px;color: #188f33;" class="link" href="/WebBanKinh/user/product/home/MKBB">Mắt Kính Baby</a>
</div>
	      <hr>
	      <div class="row">
	        <c:forEach var="pp" items="${products}">
	          <div class="col-md-3">
	            <div class="card mb-4 box-shadow " style="height: 350px;"  >
	              <a href="/WebBanKinh/user/product/detail/ma=${pp.getProduct().getId()}">
	              
					
				   
				   
						<img style="height: 180px;" class="card-img-top img-fluid img-circle product__img img-thumbnail" src="<c:url value='/templates/image/user/${pp.getProduct().getImage()}'/>"
								alt="${pp.getProduct().getName()}"> 
	              </a>
	              <div class="card-body">
	                <a href="/WebBanKinh/user/product/detail/ma=${pp.getProduct().getId()}">
	                  <h4 class="card-title">${pp.getProduct().getName()}</h4>
	                </a>
	                <div class="movie-info-wrap">
	                  <p class="movie-info"><strong>Thương Hiệu: </strong> <span class="movie-info-detail"><em>${pp.getProduct().getBranch()}</em> </span></p>
	                </div>
	                <div class="movie-info-wrap">
	                  <p class="movie-info"><strong>Thời Gian Bảo Hành:</strong><span class="movie-info-detail"> ${pp.getProduct().getWarrantyPeriod()} Tháng   </p></span>
	                </div>
					<div class="d-flex justify-content-between align-items-center">
						<div class="btn-wrap">
							<a href="#" class="btn btn-buy-ticket d-inline-block alert alert-success" role="alert"> <i class="fa-solid fa-ticket"></i> <strong>${ pp.getPrice_VND()}</strong></a>  
						</div>
						</div>
					</div>
	            </div>
	          </div>
	        </c:forEach>
	      </div>
	      
	      <nav>
  <ul class="pagination justify-content-center">
    <c:if test="${currentPage > 1}">
      <li class="page-item">
        <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
          <span class="sr-only">Previous</span>
        </a>
      </li>
    </c:if>
    
    <c:forEach var="i" begin="1" end="${totalPages}">
      <li class="page-item <c:if test='${currentPage eq i}'>active</c:if>">
        <a class="page-link" href="?page=${i}">${i}</a>
      </li>
    </c:forEach>
    
    <c:if test="${currentPage < totalPages}">
      <li class="page-item">
        <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
          <span class="sr-only">Next</span>
        </a>
      </li>
    </c:if>
  </ul>
</nav>
	    </div>
	  </div>
	</div>
		


    
</body>
</html>