package management.controller.user;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import management.dao.IProductDao;

public class Helpper {
	 @Autowired
		public static IProductDao productDao;
	 
	public static double get_price_Product(String id) {
		return productDao.get_Price_new(id);
	}
	 public static String layTenTinhTuDiaChi(String diaChi) {
		 
		   List<String> danhSachTinh = Arrays.asList("Hồ Chí Minh", "Long An", "Đà Lạt", "Vũng Tàu", "Vĩnh Long",
	                "Cần Thơ", "Bình Phước", "Nha Trang", "Trà Vinh", "Cà Mau", "Hậu Giang");

	        diaChi = diaChi.toLowerCase(); // Chuyển đổi địa chỉ sang chữ thường để đồng nhất

	        for (String tinh : danhSachTinh) {
	            if (diaChi.contains(tinh.toLowerCase())) {
	                return tinh;
	            }
	        }

	        return ""; // Trường hợp không tìm thấy tên tỉnh trong danh sách
	    }
	 public static String loaiBoTenTinh(String diaChi) {
		   List<String> danhSachTinh = Arrays.asList("Hồ Chí Minh", "Long An", "Đà Lạt", "Vũng Tàu", "Vĩnh Long",
	                "Cần Thơ", "Bình Phước", "Nha Trang", "Trà Vinh", "Cà Mau", "Hậu Giang");

	        for (String tinh : danhSachTinh) {
	            diaChi = diaChi.replace(tinh, ""); // Loại bỏ tên tỉnh
	        }

	        diaChi = diaChi.trim(); // Xóa khoảng trắng thừa ở đầu và cuối chuỗi
	        diaChi = diaChi.replaceAll("\\s+", " "); // Thay thế các khoảng trắng liên tiếp bằng một khoảng trắng duy nhất

	        if (diaChi.endsWith(",")) {
	            diaChi = diaChi.substring(0, diaChi.length() - 1); // Loại bỏ dấu phẩy cuối cùng
	        }

	        return diaChi;
	    }
	 public static int generateRandomNumber() {
	        Random random = new Random();
	        int min = 10000; // Giá trị nhỏ nhất có 5 chữ số
	        int max = 99999; // Giá trị lớn nhất có 5 chữ số

	        int randomNumber = random.nextInt(max - min + 1) + min;
	        return randomNumber;
	    }
}
