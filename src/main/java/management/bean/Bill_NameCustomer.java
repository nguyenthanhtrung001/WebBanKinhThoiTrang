package management.bean;




import management.entity.Bill;
import management.entity.Customer;


public class Bill_NameCustomer {

	Bill bill;
	Customer customer;
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	public Bill_NameCustomer() {
		super();
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
}
