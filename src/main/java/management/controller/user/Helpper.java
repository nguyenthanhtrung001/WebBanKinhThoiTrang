package management.controller.user;

import org.springframework.beans.factory.annotation.Autowired;

import management.dao.IProductDao;

public class Helpper {
	 @Autowired
		public static IProductDao productDao;
	 
	public static double get_price_Product(String id) {
		return productDao.get_Price_new(id);
	}
}
