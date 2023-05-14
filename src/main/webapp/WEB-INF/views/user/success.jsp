<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Đặt hàng thành công</title>
  <!-- Link CSS Bootstrap 4 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class="container mt-5">
    <div class="row">
      <div class="col-md-12 text-center">
        <h1 class="text-success">Cảm ơn bạn đã đặt hàng!</h1>
        <p>Đơn hàng của bạn đã được gửi đi thành công. Dưới đây là chi tiết đơn hàng:</p>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="card-header bg-primary text-white">
            <h5 class="card-title mb-0">Chi tiết đơn hàng</h5>
          </div>
          <div class="card-body">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>Sản phẩm</th>
                  <th>Giá</th>
                  <th>Số lượng</th>
                  <th>Tổng cộng</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Áo phông nam</td>
                  <td>300.000đ</td>
                  <td>2</td>
                  <td>600.000đ</td>
                </tr>
                <tr>
                  <td>Quần jean nam</td>
                  <td>500.000đ</td>
                  <td>1</td>
                  <td>500.000đ</td>
                </tr>
                <tr>
                  <td colspan="3" class="text-right">Tổng tiền:</td>
                  <td>1.100.000đ</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-md-12 text-center">
        <a href="/WebBanKinh/user/product/home" class="btn btn-primary">Tiếp tục mua hàng</a>
      </div>
    </div>
  </div>
  <!-- Link JS Bootstrap 4 -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"></script>
</body>
</html>
