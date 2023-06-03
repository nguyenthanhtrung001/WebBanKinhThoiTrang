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
  <div class="container" style="font-size: 12px;">
    <div class="row mt-5">
      <div class="col col-sm-5" style="height: 320px;">
        <a href=""><img style="height: 320px;" class="card-img-top img-fluid img-circle" src="<c:url value='/templates/image/product/${product.getProduct().getId()}'/>"
								alt="${product.getProduct().getName()}"> </a>
      </div>
      <div class="col col-sm-7">
      <form method="post" action="/WebBanKinh/user/cart/add/id=${product.getProduct().getId()}">
       
       
       <div class="card">
  
  <div class="card-body">
    <h1 style="margin-top: 0;" class="card-title">${product.getProduct().getName()} </h1>
    <p class="card-text">Một chiếc đồng hồ thời trang sẽ làm tôn lên phong cách của bạn khi kết hợp với mắt kính thời trang.</p>
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
    	
        <p>GIỚI THIỆU SẢN PHẨM MẮT KÍNH
           Một chiếc đồng hồ thời trang là một sản phẩm kết hợp giữa thời trang và chức năng đo thời gian. Nó thường được thiết kế với phong cách đẹp mắt, sang trọng và thể hiện cái nhìn riêng về thẩm mỹ của người sử dụng.

Về hình dạng, đồng hồ thời trang có nhiều loại khác nhau, từ đồng hồ tròn truyền thống đến các dạng hình vuông, chữ nhật, ovan, hay ngược lại, thậm chí có thể có các hình dạng độc đáo và sáng tạo. Vỏ đồng hồ có thể được làm từ các vật liệu như thép không gỉ, titan, vàng hoặc bạc. Mặt đồng hồ thường được bảo vệ bằng kính sapphire hoặc kính khoáng chất chống trầy xước.

Đồng hồ thời trang thường có các dây đeo đa dạng, bao gồm dây da, dây kim loại, dây cao su hoặc các dạng dây đeo đặc biệt khác như dây đeo bằng vải, da cá sấu hay dây đeo được trang trí bằng các hạt pha lê hay đá quý. Màu sắc của dây đeo có thể linh hoạt và phối hợp với tông màu chủ đạo của thiết kế tổng thể.

Mặt số của đồng hồ thời trang thường được thiết kế với sự tinh tế và chi tiết, có thể bao gồm các vạch chỉ giờ, con số La Mã, hoặc các đường cắt đặc biệt. Kim chỉ giờ và kim phút được thiết kế tinh xảo và thường được làm từ kim loại quý như vàng hoặc bạc. Đồng hồ cũng có thể có các tính năng bổ sung như đồng hồ chronograph (đồng hồ đo thời gian đoạn), lịch ngày, và chức năng chống nước.
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

