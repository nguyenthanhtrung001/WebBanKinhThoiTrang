<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<div class="col-l l-12">
		<div class="listDonHang">
		<table class="listSanPham">
			<tbody>
				<tr>
					<th colspan="6">
						<h3 style="text-align: center;">Phiếu đặt hàng ngày: ${date}</h3>
					</th>
				</tr>
				<tr>
					<th>STT</th>
					<th>Sản phẩm</th>
					<th>Giá</th>
					<th>Số lượng</th>
					<th>Thành tiền</th>
					
				</tr>
					<c:forEach var="dc" items="${list}" varStatus="loop">
				<tr>
						<td>${loop.index + 1 }</td>
					<td class="noPadding imgHide"><a target="_blank"
						href="chitietsanpham.html?Xiaomi-Redmi-Note-5"
						title="Xem chi tiết"> ${dc.detailsUpdatePrice.product.name} <img
							src="img/products/xiaomi-redmi-note-5-pro-600x600.jpg">
					</a></td>
					<td class="alignRight">
						  <c:if test="${listreduce[loop.index].equals('')}">
											<strong> 
											${dc.getDetailsUpdatePrice().getProduct().getPrice()}
											</strong>
											
										</c:if>
										
										<c:if test="${listreduce[loop.index].equals('') == false}">
											
											<strong> 
												${listreduce[loop.index]}
											</strong>
											
										</c:if>
					</td>
					<td class="soluong">${dc.quantity}</td>
					<td class="alignRight">
						 <c:if test="${listreduce[loop.index].equals('')}">
											 ${dc.getDetailsUpdatePrice().getProduct().converVND(dc.getDetailsUpdatePrice().getProduct().convertToDouble(dc.getDetailsUpdatePrice().getProduct().getPrice()) * dc.getQuantity())} 
											
										</c:if>
										
										<c:if test="${listreduce[loop.index].equals('') == false}">
											
											 ${dc.getDetailsUpdatePrice().getProduct().converVND(dc.getDetailsUpdatePrice().getProduct().convertToDouble(listreduce[loop.index]) * dc.getQuantity())}
											
										</c:if>
					</td>

				</tr>
					</c:forEach>

				<tr style="font-weight: bold; text-align: center; height: 4em;">
					<td colspan="4">TỔNG TIỀN SẢN PHẨM:</td>
					<td class="alignRight" id="totalMoney"></td>
					
				</tr>
			</tbody>
		</table>
		<hr>
	</div>
	</div>
	
	<div class="col-l l-12">
		<div class="body-lienhe">
		<div class="lienhe-info">

			<div class="guithongtin">
				<p style="font-size: 22px; color: gray">Gửi thông tin liên lạc
					cho chúng tôi:</p>
				<hr>
				<form name="formlh" action="/user/order/confirm" method="post">
					<table cellspacing="10px" style="display:flex;justify-content: center;">
						<tbody >
							<tr>
								<td>Họ và tên</td>
								<td><input type="text" name="ht" size="40" maxlength="40" value = "${user.name}"
									placeholder="Họ tên" autocomplete="off" required="" style="width:100%"></td>
							</tr>
							<tr>
								<td>Điện thoại liên hệ</td>
								<td><input type="text" name="sdt" size="40" maxlength="11" value = "${user.phoneNumber}"
									minlength="10" placeholder="Điện thoại" required="" style="width:100%"></td>
							</tr>
							
							<tr>
								<td>Địa chỉ</td>
								<td><textarea name="dc" rows="5" cols="42" maxlength="500"
										wrap="physical" placeholder="Địa chỉ" required="" style="width:100%">${user.address}</textarea></td>
							</tr>
							<tr>
								<td>Ghi chú</td>
								<td><textarea name="nd" rows="5" cols="42" maxlength="500"
										wrap="physical" placeholder="Ghi chú"  style="width:100%"></textarea></td>
							</tr>
							<tr>
								<td></td>
								<td><button type="submit" style="height: 40px;
    	width: 100%;" >Gửi thông tin</button></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>

		</div>
	</div>
	</div>
	
	
	
	
	<script type="text/javascript">
		function convertTotalMoney(){
			let element = document.getElementById("totalMoney");
			
			const amount = ${sumMoney};
			
			let vnd = amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });

			element.innerHTML = vnd;

		}
		
		convertTotalMoney();
	</script>
</body>
</html>