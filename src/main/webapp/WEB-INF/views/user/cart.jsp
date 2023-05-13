<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
    <h1 class="my-4">Giỏ hàng</h1>
    <form method="post" action="cart/del_products">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col"><input type="checkbox" id="select-all-checkbox"> Chọn tất cả</th>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Giá tiền</th>
                    <th scope="col">Thành tiền</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="crt" items="${cart}">
                    <tr>
                        <td>
                            <input type="checkbox" class="item-checkbox" name="productId" value="${crt.id}">

                        </td>
                        <td>${crt.detailsUpdatePrice.product.name}</td>
                        <td>
                            <div class="input-group">
                                <input type="number" class="form-control" value="${crt.quantity}">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary update-quantity" type="button">Cập nhật</button>
                                </div>
                            </div>
                        </td>
                        <td>${crt.detailsUpdatePrice.price}</td>
                        <td>5,000,000 VNĐ</td>
                       <td>
						  <a href="cart/del_product/id=${crt.id}">
						    <button type="button" class="btn btn-danger remove-item">Xóa</button>
						  </a>
						</td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-right">
            <a href="/WebBanKinh/user/product/home" type="button" class="btn btn-primary">Tiếp tục mua hàng</a>
            <button type="button" class="btn btn-success">Thanh toán</button>
           <button  id="remove-selected-items" type="submit"class="btn btn-danger" style="display:none;">Xóa các mục đã chọn</button>
        </div>
    </form>
</div>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script>
		$(document).ready(function() {
		// Xử lý sự kiện khi nút "Xóa" được click
		$('.remove-item').click(function() {
		$(this).closest('tr').remove();
		});
        // Xử lý sự kiện khi nút "Cập nhật số lượng" được click
        $('.update-quantity').click(function() {
            var quantity = $(this).closest('td').find('input').val();
            var price = $(this).closest('tr').find('td:eq(2)').text().trim().replace(',', '').replace(' VNĐ', '');
            var total = parseInt(quantity) * parseInt(price);
            $(this).closest('tr').find('td:eq(3)').text(total.toLocaleString('vi-VN') + ' VNĐ');
        });
        
     // Xử lý sự kiện khi checkbox "select all checkbox" được chọn
        $('#select-all-checkbox').change(function() {
            var checked = $(this).prop('checked');
            $('input[type="checkbox"]').prop('checked', checked);
        });
     
     // Xử lý sự kiện khi nút "Xóa các mục đã chọn" được click
        $('#remove-selected-items').click(function() {
            $('.item-checkbox:checked').closest('tr').remove();
        });
     // Xử lý sự kiện khi checkbox "select all checkbox" được chọn
        $('#select-all-checkbox').change(function() {
            var checked = $(this).prop('checked');
            $('input[type="checkbox"]').prop('checked', checked);
            if (checked) {
                $('#remove-selected-items').show();
            } else {
                $('#remove-selected-items').hide();
            }
        });

        // Xử lý sự kiện khi checkbox các mục được chọn
        $('.item-checkbox').change(function() {
            if ($('.item-checkbox:checked').length > 0) {
                $('#remove-selected-items').show();
            } else {
                $('#remove-selected-items').hide();
            }
        });

        // Xử lý sự kiện khi nút "Xóa các mục đã chọn" được click
        $('#remove-selected-items').click(function() {
            $('.item-checkbox:checked').closest('tr').remove();
            $('#select-all-checkbox').prop('checked', false);
            $(this).hide();
        });

     
    });
</script>
</body>
</html>