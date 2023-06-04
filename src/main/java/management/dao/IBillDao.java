package management.dao;

import java.util.List;

import management.entity.Bill;
import management.entity.DetailsCart;
import management.entity.DetailsExchangeVoucher;
import management.entity.DetailsExchangeVoucherPk;
import management.entity.ProductExchangeVoucher;
import management.entity.Seri;

public interface IBillDao {

	long getCountBill() ;
	public List<Bill> getListBill();
//	trạng thái đang chờ xử lý
	List<Bill> getListBillTT(int status);
	public Bill getBill(int id);
	
	public Bill create_Bill(Bill bill);
	Bill getBill(String id);
	public void updateStatus(int idBill, int statusNew);
	
	//--------------------
	List<Bill> getListBillOfCustomer(int id);
	List<DetailsCart> getProductForBill(String maHD);	
	List<Seri> get_n_SeriOfBillAndProduct(String maHD, String maSP, int n);
	void addProductExchangeVoucher(ProductExchangeVoucher pev);
	void addDetailsExchangeVoucherPK(DetailsExchangeVoucherPk dev_pk);
	void addDetailsExchangeVoucher(DetailsExchangeVoucher dev);
	int soLuongPhieuDoi();
	
}