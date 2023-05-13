package management.dao;


import java.util.Date;
import java.util.List;

import management.entity.DetailsCart;





public interface IDetailsCartDao {
	
	
	public boolean addProductfor_(DetailsCart DetailsCart);
	public boolean deleteProductfor_(Long id);
	public List<DetailsCart> getDetailsCart(); 
	public void updateDetailsCart(int id);
	public void update_HD_DetailsCart(int id);
	public void update_Price_DetailsCart(int id);
	public DetailsCart checkSP_cart(String maSP) ;
	public long getID(String masp);
	public void updateQuantityById(Long id, Integer newQuantity);
	public int getQuantitybyID(long id) ;
	public Date getLatestApplicableDateByProductId(String productId);
	}
