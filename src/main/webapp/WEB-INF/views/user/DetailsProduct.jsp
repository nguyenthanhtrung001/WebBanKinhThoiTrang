<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <title>Document</title>
</head>
<body>
  <div class="container" style="font-size: 13px;">
    <div class="row mt-5">
      <div class="col col-sm-5" style="height: 320px;">
        <a href=""><img style="height: 320px;" class="card-img-top img-fluid img-circle" src="<c:url value='/templates/image/product/${product.getProduct().getId()}.jpg'/>"
								alt="${product.getProduct().getName()}"> </a>
      </div>
      <div class="col col-sm-7">
      <form method="post" action="/WebBanKinh/user/cart/add/id=${product.getProduct().getId()}">
       
       
       <div class="card">
  
  <div class="card-body">
    <h1 style="margin-top: 0;" class="card-title">${product.getProduct().getName()} </h1>
    <p class="card-text"><i>Kết nối phong cách với chất lượng - Thương hiệu mắt kính thời trang đẳng cấp</i></p>
    <ul class="list-group list-group-flush">
      <li class="list-group-item"><strong>Thương Hiệu:</strong> ${product.getProduct().getBranch()}</li>
      <li class="list-group-item"><strong>Giá: </strong>${product.getPrice_VND()}</li>
      <li class="list-group-item"><strong>Màu sắc: </strong>
        <div class="btn-group" role="group" aria-label="Màu sắc">
          <button type="button" class="btn btn-link ">Đen</button>
          <button type="button" class="btn btn-link ">Trắng</button>
          <button type="button" class="btn btn-link">Xanh</button>
        </div>
      </li>
      <li class="list-group-item"><strong>Gọng kính:  </strong>
        <div class="btn-group" role="group" aria-label="Gọng kính">
          <button type="button" class="btn btn-link">Kim loại</button>
          <button type="button" class="btn btn-link">Nhựa</button>
        </div>
      </li>
      <li class="list-group-item"><strong>Số lượng: </strong>
        <div class="input-group ml-3">
           <div class="btn-group" role="group" aria-label="Số lượng">
            <button  class="btn btn-outline-secondary" type="button" onclick="decrement()">-</button>
            <input   type="text"  class="form-control" id="quantity" name="quantity" value="1" style="text-align: center;width:40px;">
            <div class="input-group-append">
              <button class="btn btn-outline-secondary" type="button" onclick="increment()">+</button>
            </div>
          </div>
        </div>
        <p>${soLuong} sản phẩm có sẵn</p>
      </li>
    </ul>
    
    <c:if test="${soLuong eq 0}">
      <div class="alert alert-danger text-center"><strong>Hết hàng</strong></div>
    </c:if>
    
    <c:if test="${soLuong ne 0}">
      <button type="submit" class="btn btn-primary btn-lg btn-block mr-2 p-3"><i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng</button>
    </c:if>

  
  </div>
</div>
</form>
    

      </div>
    
    </div>
     <!-- hết thẻ  row1-->
     
     <!-- khoang cach -->

<br>
<hr>


<!-- row2 -->
<div class="row">
    <div class="col col-sm-12 ">
        <h4>MÔ TẢ SẢN PHẨM</h4>
    </div>
    <div class="col col-sm-12 text-justify">
    	
        <p>${product.getProduct().getDescription()}</p>
        <br>
        <br>
        <br>
        <br>
    </div>
</div>
<!--hết row 2  -->
</div>
  
  
  
  <script>
  function increment() {
	  var quantity = parseInt(document.getElementById("quantity").value);
	  var maxQuantity = parseInt("${soLuong}");

	  if (quantity < maxQuantity) {
	    document.getElementById("quantity").value = quantity + 1;
	  }
	}

	function decrement() {
	  var quantity = parseInt(document.getElementById("quantity").value);

	  if (quantity > 1) {
	    document.getElementById("quantity").value = quantity - 1;
	  }
	}

</script>
</body>
</html>
