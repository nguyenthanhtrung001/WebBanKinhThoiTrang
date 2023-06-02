package management.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHIEU_DOI")
public class ProductExchangeVoucher {
	@Id
	@Column(name = "MAPD",length = 10)
	private String id;
	
	@Column(name = "NGAYDOI")
	private Date exchangeDate;
	
	@OneToOne(mappedBy = "productExchangeVoucher")
	private ProductReturnVoucher productReturnVoucher;
	
	@ManyToOne
	@JoinColumn(name = "MAKH")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "MANV")
	private Staff staff;
	
	@OneToMany(mappedBy = "exchangeVoucher")
	private Set<DetailsExchangeVoucher> detailsExchangeVouchers;

	public ProductExchangeVoucher() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public ProductReturnVoucher getProductReturnVoucher() {
		return productReturnVoucher;
	}

	public void setProductReturnVoucher(ProductReturnVoucher productReturnVoucher) {
		this.productReturnVoucher = productReturnVoucher;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Set<DetailsExchangeVoucher> getDetailsExchangeVouchers() {
		return detailsExchangeVouchers;
	}

	public void setDetailsExchangeVouchers(Set<DetailsExchangeVoucher> detailsExchangeVouchers) {
		this.detailsExchangeVouchers = detailsExchangeVouchers;
	} 
	
	
	
}
