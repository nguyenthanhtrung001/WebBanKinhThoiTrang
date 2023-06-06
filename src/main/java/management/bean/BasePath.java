package management.bean;

public class BasePath {
	static String pathImgCustomer;
	static String pathImgStaff;
	static String pathImgProduct;
	
	
	
	public BasePath() {
		//Đường dẫn link lưu ảnh trong máy 
		pathImgCustomer = "C:\\Users\\xuan\\"
				+ "git\\WebBanKinhThoiTrang\\src\\main\\webapp\\templates\\image\\customer\\";
		pathImgStaff = "C:\\Users\\xuan\\"
				+ "git\\WebBanKinhThoiTrang\\src\\main\\webapp\\templates\\image\\staff\\";
		pathImgProduct = "G:\\SCHOOLS\\PTIT\\HK6\\Thực Tập Cơ Sở\\WebDOAN\\WebBanKinhThoiTrang\\src\\main\\webapp\\templates\\image\\product\\";
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
