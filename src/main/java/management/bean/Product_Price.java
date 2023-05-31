package management.bean;

import java.text.NumberFormat;
import java.util.Locale;

import management.entity.Product;

public class Product_Price {

	Product product;
	double Price;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public Product_Price() {
		super();
	}
	public String getPrice_VND() {
		NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String formattedPrice = vndFormat.format(Price);
		return formattedPrice;
	}
	
	
}
