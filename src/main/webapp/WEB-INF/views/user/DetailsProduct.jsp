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
  <div class="container">
    <div class="row mt-5">
      <div class="col col-sm-5">
        <a href=""><img src="https://via.placeholder.com/300x400.png" class="img-fluid" alt=""></a>
      </div>
      <div class="col col-sm-7">
      <form method="post" action="/WebBanKinh/user/cart/add/id=${product.id}">
       
       
       <div class="card">
  
  <div class="card-body">
    <h1 style="margin-top: 0;" class="card-title">${product.name} </h1>
    <p class="card-text">Một chiếc đồng hồ thời trang sẽ làm tôn lên phong cách của bạn khi kết hợp với mắt kính thời trang.</p>
    <ul class="list-group list-group-flush">
      <li class="list-group-item"><strong>Thương Hiệu:</strong> ${product.branch }</li>
      <li class="list-group-item"><strong>Giá: </strong> 1.000.000 VNĐ</li>
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
         
          <input   type="text"  class="form-control" id="quantity" name=quantity value="1" style="text-align: center;width:40px;">
       
          
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" onclick="increment() ">+</button>
          </div>
          
        </div>
         </div>
         <p>123 sản phẩm có sẵn</p>
      </li>
     
    </ul>
    
    
    <button type="submit" class="btn btn-primary btn-lg mr-2"><i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng</button>

	<a href="#" class="btn btn-primary btn-lg "><span class="fas fa-shopping-bag"></span> Mua ngay</a>

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
    <div class="col col-sm-12">
        <h4>MÔ TẢ SẢN PHẨM</h4>
    </div>
    <div class="col col-sm-12">
        <p>GIỚI THIỆU SẢN PHẨM VÍ NAM ĐẸP
            VÍ NAM ĐẸP DÁNG NGANG THỜI TRANG HÀNG HIỆU CAO CẤP
            ĐẶC ĐIỂM NỔI BẬT
            Chất liệu da cao cấp
            - Sản phẩm ví da được làm từ da cao cấp mềm mại, sang trọng và có thời gian sử dụng lâu dài
            - Màu sắc đen nâu nam tính, dễ phối đồ
            - Thiết kế ví tinh tế, nam tính
            - Ví có kiểu dáng ngang đơn giản và nam tính nhưng tiện lợi trong việc lưu giữ tiền bạc, giấy tờ xe, thẻ
            ATM, hình ảnh lưu niệm..
            Kích thước
           </p>
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
    document.getElementById("quantity").value = quantity + 1;
  }
  function decrement() {
    var quantity = parseInt(document.getElementById("quantity").value);
    if(quantity > 1){
        document.getElementById("quantity").value = quantity - 1;
    }
  }
</script>
</body>

</html>

