<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Checkout - Bootstrap 4</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
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
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0">Mắt kính x2</h6>
                            <small class="text-muted">Miêu tả sản phẩm 1</small>
                        </div>
                        <span class="text-muted">$12</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0">Sản phẩm x3</h6>
                            <small class="text-muted">Miêu tả sản phẩm 2</small>
                        </div>
                        <span class="text-muted">$8</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0">Sản phẩm 3</h6>
                            <small class="text-muted">Miêu tả sản phẩm 3</small>
                        </div>
                        <span class="text-muted">$5</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between bg-light">
                        <div class="text-success">
                            <h6 class="my-0">Mã giảm giá</h6>
                            <small>EXAMPLECODE</small>
                        </div>
                        <span class="text-success">-$5</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Tổng (USD)</span>
                        <strong>$20</strong>
                    </li>
                </ul>

                <form class="card p-2">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Mã giảm giá">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-secondary">Áp dụng</button>
                        </div>
                    </div>
                </form>
            </div>
            </div>
                </div>
                 

            </div>


            <div class="col-md-6">
                <h4 class="mb-3">Thông tin thanh toán</h4>
                <form>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">Họ</label>
                            <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Tên</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
                        </div>
                    </div>


                    <div class="mb-3">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" class="form-control" id="address" placeholder="Nhập số điện thoại" required>
                    </div>

                     <div class="row">
                        
                         <div class="col-md-5 mb-3">
                            <label for="country">Quốc gia</label>
                            <select class="custom-select d-block w-100" id="country" required>
                                <option value="">Việt Nam</option>
                                
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

<!-- jQuery và các plugin của Bootstrap 4 -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>




	