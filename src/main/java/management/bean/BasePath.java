package management.bean;

public class BasePath {
	static String pathImgCustomer;
	static String pathImgStaff;
	static String pathImgProduct;
	
	
	
	public BasePath() {
		//Đường dẫn link lưu ảnh trong máy 
		pathImgCustomer = "C:\\Users\\xuan\\"
				+ "git\\DoAnWeb\\src\\main\\webapp\\templates\\user\\assets\\image\\";
		pathImgProduct = "D:\\GitHub\\WebBanKinhThoiTrang\\src\\main\\webapp\\templates\\admin\\dist\\img\\";
	}
	
	public String getPathImgCustomer() {
		return pathImgCustomer;
	}

	public String getPathImgStaff() {
		return pathImgStaff;
	}

	public String getPathImgProduct() {
		return pathImgProduct;
	}

	
	
}
