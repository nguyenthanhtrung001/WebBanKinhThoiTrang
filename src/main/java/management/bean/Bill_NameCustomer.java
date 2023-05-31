package management.bean;




import management.entity.Bill;


public class Bill_NameCustomer {

	Bill bill;
	String name;
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public String getName() {
		return name.toUpperCase();
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bill_NameCustomer() {
		super();
	}
	
	
	
	
}
