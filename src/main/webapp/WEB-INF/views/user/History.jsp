<%@ include file="/common/taglib.jsp"%>
<%@ page import="management.bean.TienIch" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách lịch sử mua hàng</title>

</head>
<body>
	<div class="container-fluid">
		<div class="card">
			<div class="card-header">
				<div class="row align-items-center">
					<div class="col-6">
						<h3 class="card-title" style="font-size: 4rem;">Lịch sử mua
							hàng</h3>
					</div>
					<div class="col-6">
						<form class="form-inline float-right">
							<div class="input-group input-group-sm">
								<input type="text" name="search" id="search"
									value="${paging.search}" class="form-control"
									placeholder="Tìm kiếm...">
								<div class="input-group-append">
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-search" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th style="font-size: 20px;">Mã đơn hàng</th>
								<th style="font-size: 20px;">Ngày mua</th>
								<th style="font-size: 20px;">Tổng tiền</th>
								<th style="font-size: 20px;">Trạng thái</th>
								<th style="font-size: 20px;">Chi tiết</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:forEach var="b" items="${list_Bills}">
								<tr>
									<td style="font-size: 18px;">${b.getId()}</td>
									<td style="font-size: 18px;">${TienIch.dateToString(b.getApplicableDate())}</td>
									<td style="font-size: 18px;">${TienIch.toVND(b.getTongTien())}</td>
									<td style="font-size: 18px;">${b.isStatus()==1 ? "Hoàn thành" : "Chưa hoàn thành"}</td>
									<td style="font-size: 18px;"><a
										class="btn btn-primary btn-sm btn-info-details"
										data-toggle="modal" data-target="#modal-${b.getId()}"
										data-bill-id="${b.getId()}"> <i style="font-size: 18px;"
											class="fa fa-info-circle" aria-hidden="true"></i>
									</a>
										<div class="modal fade" id="modal-${b.getId()}">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header"
														style="background: #2c83e0; color: white;">
														<h4 class="modal-title">Thông Tin Chi Tiết Của Hóa
															Đơn</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<div class="card card-primary card-outline">
															<div class="card-body box-profile">
																<div class="container">

																	<div class="row">
																		<div class="col-md-6">
																			<ul class="invoice-details">
																				<li><b>Mã hóa đơn:</b> <span
																					class="float-right text-primary">${b.getId()}</span>
																				</li>
																				<li><b>Đơn vị ship:</b> <span
																					class="float-right text-primary">${b.getShip().getName()}</span>
																				</li>
																				<li><b>Ngày mua:</b> <span
																					class="float-right text-primary">${TienIch.dateToString(b.getApplicableDate())}</span>
																				</li>
																				<li><b>Trạng thái hóa đơn:</b> <span
																					class="float-right text-primary">${b.isStatus() == 1 ? "Hoàn thành" : "Chưa hoàn thành"}</span>
																				</li>
																			</ul>
																		</div>
																	</div>
																	<br>

																	<div class="row">
																		<div class="table-responsive">
																			<table class="table table-striped table-hover">
																				<thead>
																					<tr>
																						<th style="font-size: 20px;">Stt</th>
																						<th style="font-size: 20px;">Tên Sản Phẩm</th>
																						<th style="font-size: 20px;">Hình ảnh</th>
																						<th style="font-size: 20px;">Đơn giá</th>
																						<th style="font-size: 20px;">Số lượng</th>
																						<th style="font-size: 20px;">Thành tiền</th>
																						<th style="font-size: 20px;">Đổi trả</th>
																					</tr>
																				</thead>
																				<tbody>
																					<c:forEach var="sp" varStatus="s"
																						items="${b.getDetailsCarts()}">
																						<tr>
																							<td style="font-size: 18px;">${s.index + 1}</td>
																							<td style="font-size: 18px;">${sp.getDetailsUpdatePrice().getProduct().getName()}</td>
																							<td style="font-size: 18px;">${billDao.getBill(1).getApplicableDate()}</td>
																							<td style="font-size: 18px;">${TienIch.toVND(sp.getDetailsUpdatePrice().getPrice())}</td>
																							<td style="font-size: 18px;">${sp.getQuantity()}</td>
																							<td style="font-size: 18px;">${TienIch.toVND(sp.getQuantity()*sp.getDetailsUpdatePrice().getPrice())}</td>
																							<td style="font-size: 18px;">
																									<a href ="created_return_voucher/${b.getId()}">
																										<i style="font-size: 18px;"class="fa fa-reply" aria-hidden="true"></i>
																									</a>
																							</td>
																							
																						</tr>
																					</c:forEach>
																				</tbody>



																			</table>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-sm-3">
																			<c:if test="${TienIch.isExpired_day(b.getApplicableDate(),7)}">
																				<a href="thinh"><button >Hoàn đơn</button></a>
																			</c:if>
																			<c:if test="${!TienIch.isExpired_day(b.getApplicableDate(),7)}">
																				<button type="button" disabled>Hoàn đơn</button>
																			</c:if>
																		</div>
																		<div class="col-sm-4 offset-4">
																			<ul class="invoice-details">
																				<li><b>Tiền ship:</b> <span
																					class="float-right text-primary">${TienIch.toVND(b.getShip().getPrice())}</span>
																				</li>
																				<li style="color: red;"><h2>
																						Tổng tiền:<span class="float-right text-primary">${TienIch.toVND(b.getTongTien())}</span>
																					</h2></li>
																			</ul>
																		</div>

																	</div>

																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div></td>

								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>



	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>


	<script>
		$(document)
				.ready(
						function() {
							$("#search")
									.on(
											"keyup",
											function() {
												var value = $(this).val()
														.toLowerCase();
												$("#myTable tr")
														.filter(
																function() {
																	$(this)
																			.toggle(
																					$(
																							this)
																							.text()
																							.toLowerCase()
																							.indexOf(
																									value) > -1)
																});
											});
						});
	</script>
</body>
</html>
