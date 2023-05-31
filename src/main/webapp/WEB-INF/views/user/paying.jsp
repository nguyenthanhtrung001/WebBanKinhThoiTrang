<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Đơn Hàng</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

</head>
<body  >

    <div class="container mt-5" style="font-size: 14px;">
        <div class="row">
            

           <div class="col-md-6">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-12 order-md-2 mb-4">
                            <h4 class="d-flex justify-content-between align-items-center mb-3">
                                <span class="text-muted">Danh sách đơn hàng</span>
                                <span class="badge badge-secondary badge-pill">10</span>
                            </h4>
                            <ul class="list-group mb-3">
                                <!-- Trước vòng lặp forEach, đặt biến totalAmount và gán giá trị ban đầu là 0 -->
                                <c:set var="totalAmount" value="0" />
                                 <c:set var="voucher" value="0" />

                                <c:forEach var="crt" items="${cart}">
                                    <!-- Lấy giá tiền của sản phẩm và số lượng tương ứng -->
                                    <c:set var="productPrice" value="${crt.detailsUpdatePrice.getPrice()}" />
                                    <c:set var="productQuantity" value="${crt.quantity}" />

                                    <!-- Tính tổng tiền cho từng sản phẩm -->
                                    <c:set var="productTotal" value="${productPrice * productQuantity}" />

                                    <!-- Cộng tổng tiền cho từng sản phẩm vào biến totalAmount -->
                                    <c:set var="totalAmount" value="${totalAmount + productTotal}" />

                                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                                        <div>
                                            <h5 class="my-0">${crt.detailsUpdatePrice.product.name} <strong>x${crt.quantity}</strong></h5>
                                            <small class="text-muted">Giá: ${crt.detailsUpdatePrice.getPrice_VND()} </small>
                                        </div>
                                       <span class="text-muted">${crt.detailsUpdatePrice.getPrice() * crt.quantity} VND</span>

                                    </li>
                                </c:forEach>
									
									 <li id="selectedPromotionItem" class="list-group-item d-flex justify-content-between bg-light">
									    <div class="text-success">
									        <h6 class="my-0">Mã giảm giá</h6>
									        <small id="selectedPromotionName"><i>Nhấn vào áp dụng để có các mã giảm giá</i></small>
									    </div>
									    <span class="text-success" id="selectedPromotionAmount">0 VND</span>
									</li>
									
                                <!-- Định dạng tiền tệ Việt Nam -->
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>Tổng (VND)</span>
                                   <input id="voucherInput" name="voucher" value="${totalAmount} VND" readonly style="font-weight: bold; text-align: right;" />

                                   
                                </li>
                               

                                <!-- Mã giảm giá -->
                                <li class="list-group-item d-flex justify-content-between">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#promoModal">
                                        Áp dụng mã giảm giá
                                    </button>
                                </li>
                            </ul>

                            
                        </div>
                    </div>
                </div>
            </div>
            
            
             <!-- thông tin khách h -->
            <div class="col-md-6" style="font-size: 13px;">
                <h4 class="mb-3">Thông tin thanh toán</h4>
                <form method="post" action="paying/success">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">Họ</label>
                            <input type="text" class="form-control" id="firstName" placeholder="" value="TMP" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Tên</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" value="${sessionScope.user.name}" required>
                        </div>
                    </div>


                    <div class="mb-3">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" class="form-control" id="address" value="${sessionScope.user.phoneNumber}"  placeholder="Nhập số điện thoại" required>
                    </div>

                     <div class="row">
                        
                         <div class="col-md-5 mb-3">
                            <label for="country">Quốc gia</label>
                            <select class="custom-select d-block w-100" id="country" required>
                                <option value="Việt Nam">Việt Nam</option>
                                
                            </select>
                        </div>

                           <div class="col-md-7 mb-3">
                          <label for="state">Tỉnh/Thành phố</label>
                          <select class="custom-select d-block w-100" id="state" required>
                            <option value="">...</option>
                            <option value="HCM">Hồ Chí Minh</option>
                            <option value="LA">Long An</option>
                            <option value="DL">Đà Lạt</option>
                            <option value="VT">Vũng Tàu</option>
                            <option value="VL">Vĩnh Long</option>
                            <option value="CT">Cần Thơ</option>
                            <option value="BP">Bình Phước</option>
                            <option value="NT">Nha Trang</option>
                            <option value="TV">Trà Vinh</option>
                            <option value="CM">Cà Mau</option>
                            <option value="HG">Hậu Giang</option>
                          </select>
                        </div>

                       
                        </div>


                    <div class="mb-3">
                        <label for="address">Địa chỉ <span class="text-muted">(Số nhà, xã/phường, quận/huyện)</span></label>
                        <input type="text" class="form-control" id="address" placeholder="Nhập địa chỉ"required>
                    </div>

                   
                    
                    
                <h4 class="mb-3">Thanh toán</h4>

                <div class="d-block my-3">
                    <div class="form-check">
                        <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked required>
                        <label class="form-check-label" for="credit">Momo</label>
                    </div>
                    
                    <div class="form-check">
                        <input id="paypal" name="paymentMethod" type="radio" class="form-check-input" required>
                        <label class="form-check-label" for="paypal">Thanh toán khi nhận hàng</label>
                    </div>
                </div>
               
               
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Đặt hàng</button>
            </form>
        </div>
    </div>
</div>





<!-- Modal mã giảm giá -->
<div class="modal fade " id="promoModal" tabindex="-1" role="dialog" aria-labelledby="promoModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="promoModalLabel">Mã giảm giá</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Nội dung mã giảm giá -->
               <ul class="list-group">
    <c:forEach var="promotion" items="${listPromotion}">
        <li class="list-group-item d-flex justify-content-between bg-light">
            <div class="text-success">
                <h6 class="my-0">Mã giảm giá</h6>
                <small>${promotion.name}</small>
            </div>
            <span class="text-success">${promotion.getPromotionLitmit_VND()}</span>
            <button class="btn btn-primary" class="close" data-dismiss="modal" aria-label="Close" onclick="selectPromotion('${promotion.name}','${promotion.getPromotionLitmit()}','${totalAmount }')">Chọn</button>
        </li>
    </c:forEach>
</ul>

            </div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>


<script>
function selectPromotion(promotionName, promotionVoucher, total) {
    document.getElementById("selectedPromotionName").textContent = promotionName;
    
    document.getElementById("selectedPromotionAmount").textContent = "-" + promotionVoucher.toLocaleString("vi-VN")+" VND";

    var formattedTotal = (total - promotionVoucher).toLocaleString("vi-VN");
    document.getElementById("voucherInput").value = formattedTotal+" VND";
}
</script>

<!-- jQuery và các plugin của Bootstrap 4 -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>




	