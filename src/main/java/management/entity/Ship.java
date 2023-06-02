package management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SHIP")
public class Ship {
	@Id
	@Column(name = "MASHIP",length = 10)
	private String id;
	
	@Column(name = "TEN", columnDefinition = "nvarchar(100)")
	private String name;
	
	@Column(name = "GIA")
	private Double price;
	
	@OneToMany(mappedBy = "ship")
	private List<Bill> bills = new ArrayList<Bill>();
	
	@OneToMany(mappedBy = "ship")
	private List<ProductReturnVoucher> productReturnVouchers = new ArrayList<ProductReturnVoucher>();

	public Ship() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public List<ProductReturnVoucher> getProductReturnVouchers() {
		return productReturnVouchers;
	}

	public void setProductReturnVouchers(List<ProductReturnVoucher> productReturnVouchers) {
		this.productReturnVouchers = productReturnVouchers;
	}
	
	
}
