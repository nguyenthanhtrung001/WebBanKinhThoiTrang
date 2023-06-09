<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chi tiết sản phẩm</title>
</head>
<body>
	<div class="col-l l-12">
	<h1>${product.name}</h1>
	
	</div>
	<div class="col-l l-4">
		<div class="dp_image" style="width: 100%;">
			<img
				src="/templates/image/product/${product.id}.jpg">
		</div>
	</div>
	
	<div class="col-l l-8">
		<div class="info_product">
			<h2>Thông tin sản phẩm</h2>
			<ul class="info">
				<li>
					<p>Hãng</p>
					<div>${product.branch}</div>
				</li>
				<li>
					<p>Chất liệu</p>
					<div>${product.material}</div>
				</li>
				<li>
					<p>Kích thức</p>
					<div>${product.size}</div>
				</li>
				<li>
					<p>Số lượng còn</p>
					<div>${quantity}</div>
				</li>
				<li>
					${product.description}
				</li>
			</ul>
			
		</div>
		<div class="info_product" style="margin-top:20px">
			<div class="dp_area_order">
				<a href="<c:url value='/user/cart/${product.id}'/>" class="dp_buy_now"> <b><i class="fa fa-cart-plus"></i>
						Thêm vào giỏ hàng</b>
					
				</a>
			</div>
		</div>
	</div>
</body>
</html>