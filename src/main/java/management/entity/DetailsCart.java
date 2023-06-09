package management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHITIET_GIOHANG")
public class DetailsCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private DetailsUpdatePrice detailsUpdatePrice; 
	
	@Column(name = "SOLUONG")
	private int quantity;
	
//	@Column(name = "TRANGTHAI")
//	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="MAHD", nullable=true)
	private Bill bill;

	
	public DetailsCart() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DetailsUpdatePrice getDetailsUpdatePrice() {
		return detailsUpdatePrice;
	}

	public void setDetailsUpdatePrice(DetailsUpdatePrice detailsUpdatePrice) {
		this.detailsUpdatePrice = detailsUpdatePrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	
	
}
