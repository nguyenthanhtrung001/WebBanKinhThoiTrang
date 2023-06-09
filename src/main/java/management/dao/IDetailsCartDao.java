package management.dao;

import java.util.Date;
import java.util.List;

import management.entity.Bill;
import management.entity.Customer;
import management.entity.DetailsCart;

public interface IDetailsCartDao {

	public boolean addProductfor_(DetailsCart DetailsCart);

	public boolean deleteProductfor_(Long id);

	public List<DetailsCart> getDetailsCart(int Kh);

	public DetailsCart get_One_P_Cart_Pay(Long id);

	public void updateDetailsCart(int id);

	public void update_HD_DetailsCart(Long id, Bill bill);

	public void update_Price_DetailsCart(int id);

	public DetailsCart checkSP_cart(String maSP);

	public long getID(String masp);

	public void updateQuantityById(Long id, Integer newQuantity);

	public int getQuantitybyID(long id);

	public Date getLatestApplicableDateByProductId(String productId);

	public String get_name_customer_by_IDHD(int id);

	public Customer get_customer_by_IDHD(int id);

	public double getTotalAmountOfBillItems();
	
	List<DetailsCart> getDetailsCartsOfCustomerYetBuy(int idCustomer);
	
	List<DetailsCart> getDetailsCartsOfCustomerWasBought(int idCustomer);
	
	void save(DetailsCart cart);
	
	DetailsCart getDetailsCartById(Long id);
	
	void deteleDetailsCart(DetailsCart detailsCart);
	
	DetailsCart updateDetailsCart(DetailsCart detailsCart);
}
