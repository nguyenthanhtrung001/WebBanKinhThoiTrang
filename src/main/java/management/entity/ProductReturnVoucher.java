package management.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHIEU_TRA")
public class ProductReturnVoucher {
	@Id
	@Column(name = "MAPT",length = 10)
	private String id;
	
	@Column(name = "NGAYTRA")
	private Date productReturnDate;
	
	@Column(name = "TRANGTHAI")
	private int status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MAPD",referencedColumnName = "MAPD")
	private ProductExchangeVoucher productExchangeVoucher;
	
	@ManyToOne
	@JoinColumn(name = "MASHIP")
	private Ship ship;
	
	@OneToMany(mappedBy = "returnVoucher")
	private Set<DetailsReturnVoucher> detailsReturnVouchers;

	public ProductReturnVoucher() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getProductReturnDate() {
		return productReturnDate;
	}

	public void setProductReturnDate(Date productReturnDate) {
		this.productReturnDate = productReturnDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ProductExchangeVoucher getProductExchangeVoucher() {
		return productExchangeVoucher;
	}

	public void setProductExchangeVoucher(ProductExchangeVoucher productExchangeVoucher) {
		this.productExchangeVoucher = productExchangeVoucher;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Set<DetailsReturnVoucher> getDetailsReturnVouchers() {
		return detailsReturnVouchers;
	}

	public void setDetailsReturnVouchers(Set<DetailsReturnVoucher> detailsReturnVouchers) {
		this.detailsReturnVouchers = detailsReturnVouchers;
	} 
	
	
}
