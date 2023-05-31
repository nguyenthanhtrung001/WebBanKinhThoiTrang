<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Cá Nhân</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!--<link rel="stylesheet" href="../trangchu/style.css">
    <link rel="stylesheet" href="../trangchu/bootstrap-5.1.3-dist/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../trangchu/base.css">-->


	<link rel="stylesheet" href="/templates/user/profile/css/bootstrap-5.1.3-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="/templates/user/profile/css/bootstrap/dist/css/bootstrap.min.css">
    <!-- Favicon -->
   
    <link rel="stylesheet" href="/templates/user/profile/css/all.min.css" />
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" integrity="sha512-rqQltXRuHxtPWhktpAZxLHUVJ3Eombn3hvk9PHjV/N5DMUYnzKPC1i3ub0mEXgFzsaZNeJcoE0YHq0j/GFsdGg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Template CSS -->
    
    <link rel="stylesheet" href="<c:url value='/templates/user/profile/img/logo.svg'/>"/>
    <link rel="stylesheet" href="<c:url value='/templates/user/profile/css/main.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/templates/user/profile/css/boostrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/templates/user/profile/css/alertify.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/templates/user/profile/css/account.css'/>"/>
    
    
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/headers/">
    
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	
   
    <style>
    	.bd-placeholder-img {
	        font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        user-select: none;
	      }

    	@media (min-width: 768px) {
	    	.bd-placeholder-img-lg {
	        	font-size: 3.5rem;
	        }
	    }	   
	    
    </style>
    <!-- Custom styles for this template -->
    <link href="<c:url value='/templates/user/profile/css/headers.css'/>" rel="stylesheet"/>

    <style>
        #carousel-simple {
            text-align: center;
        }
         
    	.errors{
    		color: red;
    	}
    	.col-3 .text-center{
    		background: #088178;
    		color: #222222;
    	}
    	.dropdown:hover .dropdown-menu {
			display: block;
			margin-top: 0;
		}
		
    	
    </style>
    <link rel="stylesheet"
	href="<c:url value='/templates/user/profile/css/alertify.min.css'/>" />
</head>

<body>
<div  class="alert-flag "  aType='error' 
		aMessage="${message}"></div>
	
    
    <main class="main bg-light vh-90"
        style="background: #132028 url(https://www.bhdstar.vn/wp-content/themes/bhd/assets/images/bg-cinema-10.png) center top repeat;">
        <section class="pt-50">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="dashboard-menu">
                                    <ul class="nav flex-column" role="tablist" style="background-color: #fff;">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="account-detail-tab" data-bs-toggle="tab"
                                                href="#account-detail" role="tab" aria-controls="account-detail"
                                                aria-selected="true"><i class="fa-regular fa-user mr-10"></i>Thông tin
                                                cá
                                                nhân</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="dashboard-tab" data-bs-toggle="tab"
                                                href="#dashboard" role="tab" aria-controls="dashboard"
                                                aria-selected="false"><i class="fa-solid fa-arrows-rotate mr-10"></i>
                                                Đổi
                                                mật khẩu</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="orders-tab" data-bs-toggle="tab" href="#orders"
                                                role="tab" aria-controls="orders" aria-selected="false">
                                                <i class="fa-light fa-ballot mr-10"></i>Giao dịch</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="/QuanLyRapChieuPhim/login.htm">
                                                 <i class="fa-solid fa-arrow-right-from-bracket mr-10"></i>Đăng
                                                xuất</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                           
                            <div class="col-md-9">
                                <div class="tab-content dashboard-content">
                                   <div class="tab-pane fade" id="dashboard" role="tabpanel" aria-labelledby="dashboard-tab">
    <div class="card shadow">
        <div class="card-header">
            <h5 class="mb-0">Đổi mật khẩu</h5>
        </div>
        <div class="card-body">
            <div class="row">
                <form class="col-4 mx-auto pt-5 card" method="post" action="/QuanLyRapChieuPhim/customer/change/password.htm">
                    <div class="form-group col-md-12">
                        <label for="input-current-password">Mật khẩu hiện tại</label>
                        <input type="password" name="oldPass" class="form-control" id="input-current-password" required/>
                        <%--  ${message_o} --%>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="input-new-password">Mật khẩu mới</label>
                        <input type="password" name="newPass" class="form-control" id="input-new-password" required/>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="input-confirm-new-password">Xác nhận mật khẩu mới</label>
                        <input type="password" name="confirmPass" class="form-control" id="input-confirm-new-password" required/>
                        ${message_n}
                    </div>
                    <div class="form-group col-12">
                        <button type="submit" name="btnpw" class="btn btn-primary col-12">Lưu thay đổi</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

                                   
                                    <div class="tab-pane fade" id="orders" role="tabpanel" aria-labelledby="orders-tab">
                                        <div class="card shadow">
                                            <div class="card-header">
                                                <h5 class="mb-0 d-flex justify-content-between align-center">
                                                    Đơn đặt của bạn
                                                    <form class="row popup">
                                                        <div class="d-flex gap-1">
                                                            <div class="input-group">
                                                                <input type="text" class="form-control"
                                                                    id="order-search" placeholder="Search" />
                                                                <div class="input-group-text bg-brand text-white">
                                                                    <i class="fa-regular fa-magnifying-glass"></i>
                                                                </div>
                                                            </div>
                                                            <div class="popup-btn">
                                                                <button class="btn">
                                                                    <i class="fa-regular fa-filter-list"></i>
                                                                </button>
                                                                <div class="popup-card p-10"></div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </h5>
                                            </div>
                                            <div class="card-body shadow">
                                                <div class="col-12 bg-white p-10 rounded mb-10 bg-brand text-white">
                                                    <div class="row">
                                                        <div class="col-2 text-center">Mã đơn</div>
                                                        <div class="col-3 text-center">Ngày đặt</div>
                                                        <div class="col-2 text-center">Giờ chiếu</div>
                                                        <div class="col-3 text-center">Giá</div>

                                                        <div class="col-2 text-center">Thao tác</div>
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                	<c:forEach var="s" items="${lv}">
                                                    <div
                                                        class="col-12 bg-white p-10 rounded-1 mb-5 product border border-brand">
                                                        <div class="row align-items-center custome-checkbox">
                                                            <div class="col-2 text-center product-name">
                                                                <h5 class="order-id">
                                                                    <a href="shop-product-right.html">${s.maVe }
                                                                    </a>
                                                                </h5>
                                                            </div>
                                                            <div class="col-3 text-center order-date">
                                                                <span>${s.ngayBan } </span>
                                                            </div>
                                                            <div class="col-2 text-center product-state">
                                                                <span class="badge rounded-pill bg-brand">${s.dsLichChieu.gioChieu }:00</span>
                                                            </div>
                                                            <div class="col-3 text-center">${s.loaiVe.gia }</div>
                                                            <div
                                                                class="col-2 d-flex justify-content-center gap-md-3 order-action">
                                                                <!--<a href="#" class="text-muted my-tooltip"
                                                                    aria-label="Xoá" data-bs-toggle="modal"
                                                                    data-bs-target="#confirm-modal"><i
                                                                        class="fa-regular fa-trash-can"></i></a>
                                                                <a href="#" class="text-muted my-tooltip"
                                                                    aria-label="Huỷ đơn" data-bs-toggle="modal"
                                                                    data-bs-target="#confirm-modal"><i
                                                                        class="fa-regular fa-ban"></i></a>-->
                                                                <a href="#" class="text-muted my-tooltip"
                                                                    data-bs-toggle="modal"
                                                                    data-bs-target="#order-details${s.maVe}"
                                                                    aria-label="Chi tiết"><i
                                                                        class="fa-regular fa-circle-info"></i></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
									
                                    <div class="tab-pane fade active show" id="account-detail" role="tabpanel"
                                        aria-labelledby="account-detail-tab">
                                        <div class="card shadow">
                                            <div class="card-header">
                                                <h5>Thông tin cá nhân</h5>
                                                <%-- <div style="color: red;">${message}</div> --%>
                                            </div>
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="avatar-wrapper">
                                                            <img class="profile-pic" src="" />
                                                            <div class="upload-button">
                                                                <i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
                                                            </div>
                                                            <input class="file-upload" type="file" accept="image/*" />
                                                        </div>
                                                        <div class="col-12 text-center text-brand-dark">
                                                            <span class="fs-5">${us.name}</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-8">
                                                        <form action="/QuanLyRapChieuPhim/customer/update/${us.maKH }.htm" method="POST"
                                                            class="needs-validation">
                                                            <div class="row">
                                                                <div class="form-group col-md-12">
                                                                    <label>Họ và tên
                                                                        <span class="required">*</span></label>
                                                                        <div class="errors">${errors_tenKH}</div>
                                                                    <input class="form-control" name="tenKH" value="${us.tenKH }" type="text"
                                                                        required />
                                                                </div>

                                                                <fieldset class="col-md-12">
                                                                    <legend class="col-form-label col-sm-2 pt-0">
                                                                        Giới tính
                                                                    </legend>
                                                                    <div class="col-sm-12 d-flex gap-4 ml-10">
                                                                        <div class="form-check custom-radio">
                                                                            <input class="form-check-input" type="radio"
                                                                                name="gioiTinh" id="female" value="false"
                                                                                checked />
                                                                            <label class="form-check-label"
                                                                                for="female">
                                                                                Nữ
                                                                            </label>
                                                                        </div>
                                                                        <div class="form-check custom-radio">
                                                                            <input class="form-check-input" type="radio"
                                                                                name="gioiTinh" id="male" value="true" />
                                                                            <label class="form-check-label" for="male">
                                                                                Nam
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </fieldset>
                                                                <div class="form-group col-md-12">
                                                                    <label>Ngày sinh
                                                                        <span class="required">*</span></label>
                                                                    <div class="errors">${errors_ngaySinh }</div>
                                                                    <input class="form-control" name="ngaySinh"
                                                                        type="date" value="${us.ngaySinh }" required/>
                                                                </div>
                                                                <div class="form-group col-md-12">
                                                                    <label>Địa chỉ
                                                                        <span class="required">*</span></label>
                                                                    <div class="errors">${errors_diaChi }</div>
                                                                    <input  class="form-control"
                                                                        name="diaChi" type="text" value="${us.diaChi }" required/>
                                                                </div>
                                                                <div class="form-group col-md-12">
                                                                    <label>SDT
                                                                        <span class="required">*</span></label>
                                                                    <div class="errors">${errors_soDT }</div>
                                                                    <input class="form-control" name="soDT"
                                                                        type="tel" value="${us.soDT }" required/>
                                                                </div>

                                                                <div class="col-md-12">
                                                                    <button type="submit" class="btn" name="submit"
                                                                        value="Submit">
                                                                        Xác nhận
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                              
                                </div>
                            </div>
                      
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
   
    <!-- confirm modal -->
    <div class="modal fade" id="confirm-modal" tabindex="-1" aria-labelledby="confirm-modal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-sm">
            <div class="modal-content">
                <div class="modal-body">
                    Bạn có chắn chắn muốn xoá sản phẩm đang chọn
                </div>
                <div class="text-end p-10">
                    <button type="button" class="btn">Xác nhận</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        huỷ
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!-- order details modal -->
    <c:forEach var="s" items="${lv}">
    <div class="modal fade" id="order-details${s.maVe }" tabindex="-1" aria-labelledby="confirm-modal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="row">
                    <div class="col-md-12 mx-auto">
                        <div class="order_review border-0">
                            <div class="mb-20 d-flex justify-content-between">
                                <h4>Mã Vé:<span class="text-brand">${s.maVe}</span></h4>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="table-responsive order_table text-center">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th colspan="2">PHIM</th>
                                            <th>GIÁ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="image product-thumbnail">
                                                <img src="${s.dsLichChieu.dsPhim.link }"
                                                    alt="#" />
                                            </td>
                                            <td>
                                                <h5>
                                                    <a href="/QuanLyRapChieuPhim/film/detail/ma=${s.dsLichChieu.dsPhim.maPhim }.htm">${s.dsLichChieu.dsPhim.tenPhim }</a>
                                                </h5>
                                                <!--<span class="product-qty align-middle"><i
                                                        class="fa-regular fa-xmark"></i> 2</span>-->
                                            </td>
                                            <td>${s.loaiVe.gia }</td>
                                        </tr>                             
                                        <tr>
                                            <th>Phòng</th>
                                            <td class="product-subtotal" colspan="2">${s.dsLichChieu.dsPhong.maPhong}</td>
                                        </tr>
                                         <tr>
                                            <th>Mã ghế</th>
                                            <td class="product-subtotal" colspan="2">${s.soGhe}</td>
                                        </tr>
                                        <tr>
                                            <th>Suất Chiếu</th>
                                            <td colspan="2"><em>${s.dsLichChieu.gioChieu}:00 (${s.dsLichChieu.ngayChieu })</em></td>
                                        </tr>
                                        <tr>
                                            <th>Tổng Thanh Toán</th>
                                            <td colspan="2" class="product-subtotal">
                                                <span class="font-xl text-brand fw-900">${s.loaiVe.gia }</span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="bt-1 border-color-1 mt-30 mb-30"></div>
                            <div class="payment_method">
                                <div class="mb-25">
                                    <h5>
                                        Địa Chỉ Rạp Chiếu:
                                        <span class="text-brand">PTITHCM</span>
                                    </h5>
                                </div>
                            </div>
                            <div class="bt-1 border-color-1 mt-30 mb-30"></div>

                            <div class="payment_method">
                                <div class="mb-25">
                                    <h5>
                                        Phương Thức Thanh Toán:
                                        <span class="text-brand">Thanh Toán Online</span>
                                    </h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
    <!--  -->
    
    
    <script src=""></script>
    <script src="/templates/user/profile/js/vendor/modernizr-3.6.0.min.js"></script>
    <script src="/templates/user/profile/js/vendor/jquery-3.6.0.min.js"></script>
    <script src="/templates/user/profile/js/vendor/jquery-migrate-3.3.0.min.js"></script>
    <script src="/templates/user/profile/js/vendor/bootstrap.bundle.min.js"></script>
    <script src="/templates/user/profile/js/plugins/slick.js"></script>
    <script src="/templates/user/profile/js/plugins/jquery.syotimer.min.js"></script>
    <script src="/templates/user/profile/js/plugins/wow.js"></script>
    <script src="/templates/user/profile/js/plugins/jquery-ui.js"></script>
    <script src="/templates/user/profile/js/plugins/perfect-scrollbar.js"></script>
    <script src="/templates/user/profile/js/plugins/magnific-popup.js"></script>
    <script src="/templates/user/profile/js/plugins/select2.min.js"></script>
    <script src="/templates/user/profile/js/plugins/waypoints.js"></script>
    <script src="/templates/user/profile/js/plugins/counterup.js"></script>
    <script src="/templates/user/profile/js/plugins/jquery.countdown.min.js"></script>
    <script src="/templates/user/profile/js/plugins/images-loaded.js"></script>
    <script src="/templates/user/profile/js/plugins/isotope.js"></script>
    <script src="/templates/user/profile/js/plugins/scrollup.js"></script>
    <script src="/templates/user/profile/js/plugins/jquery.vticker-min.js"></script>
    <script src="/templates/user/profile/js/plugins/jquery.theia.sticky.js"></script>
    <script src="/templates/user/profile/js/plugins/jquery.elevatezoom.js"></script>
    <!-- Template  JS -->
    <script src="/templates/user/profile/js/account.js'/>"></script>


    <script src="/templates/user/profile/js/shop.js"></script>
    <script src="/templates/user/profile/js/inputNumber.js"></script>
    <script src="/templates/user/profile/js/quickView.js"></script>
    <script src="/templates/user/profile/js/alertify.min.js'/>"></script>
    <script src="/templates/user/profile/js/showMessage.js'/>"></script>
	<script src=" <c:url value='/resources/js/alertify.min.js'/>"></script>
	
	<script type="text/javascript">
		if ($(".alert-flag").attr("aType")) {
			console.log(1)
			alertify.notify($(".alert-flag").attr("aMessage"), $(".alert-flag").attr("aType"), 5, function() { console.log('dismissed'); });
			alertify.set('notifier', 'position', 'top-right');
			
		}
	</script>
</body>

</html>