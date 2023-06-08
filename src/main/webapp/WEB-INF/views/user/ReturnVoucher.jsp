<%@ include file="/common/taglib.jsp"%>
<%@ page import="management.bean.TienIch" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Phiểu trả</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">

</head>
<body>

	<form action="/management/user/return_voucher" method="post">
		<input type="hidden" name="id" value="${bill.getId()}">
		<input type="hidden" name="ngayHienTai" value="<fmt:formatDate value="${ngayHienTai}" pattern="yyyy-MM-dd" />">
		<div class="container"
			style="font-size: 30px; height: 100vh;
            width: 110vw;">
			<div class="col-sm-12">
				<div class="row center ">
					<strong><code>Lập phiếu đổi trả sản phẩm</code></strong><br>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-5 offset-1">
						<ul style="font-size: 18px;">
							<li>Mã hóa đơn: ${bill.getId()}</li>
							<li>Ngày tạo hóa đơn:
								${TienIch.dateToString(bill.getApplicableDate())}</li>
							<li>Ngày tạo phiếu đổi: ${TienIch.dateToString(ngayHienTai)}</li>
						</ul>
					</div>
					<div class="col-sm-6">
						<textarea style="font-size: 18px;" rows="2" cols="50" name="liDo"
							placeholder="Nhập lí do đổi trả..."></textarea>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th style="font-size: 20px;">Stt</th>
									<th style="font-size: 20px;">Stt</th>
									<th style="font-size: 20px;">Tên Sản Phẩm</th>
									<th style="font-size: 20px;">Hình ảnh</th>
									<th style="font-size: 20px;">Số lượng</th>
									<th style="font-size: 20px;">Đơn giá</th>
							</thead>
							<tbody>
								<c:forEach var="sp" varStatus="s"
									items="${billDao.getDetailsCartsOfBill(b.getId())}">
									<tr>
										<td style="font-size: 18px;">${s.index + 1}</td>
										<td style="font-size: 18px;">${s.index + 1}</td>
										<td style="font-size: 18px;">${sp.getDetailsUpdatePrice().getProduct().getName()}</td>
										<td style="font-size: 18px;">${s.index + 1}</td>
										<td style="font-size: 18px;"><input name="soLuong"
											id="soLuong" type="number" min="0" max="${sp.getQuantity()}"
											oninput="if (this.value > ${sp.getQuantity()}) this.value = ${sp.getQuantity()};"
											step="1" value="0"> <input type="hidden"
											name="productId"
											value="${sp.getDetailsUpdatePrice().getProduct().getId()}">
										</td>
										<td style="font-size: 18px;">${TienIch.toVND(sp.getDetailsUpdatePrice().getPrice())}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<br>
				<div class="row center">
					<button class="btn btn-success" style="font-size: 19px;"
						name="btnDoiTra">Đổi trả</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
