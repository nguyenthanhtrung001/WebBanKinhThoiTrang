package management.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "KHACH_HANG")
public class Customer {
	@Id
	@Column(name = "MAKH")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	@Column(name = "HOTEN", columnDefinition = "nvarchar(100)")
	private String name;
	
	@Column(name = "SDT", length = 10)
	private String phoneNumber;
	
	@Column(name = "GIOITINH")
	private boolean gender;
	
	@Column(name = "DIACHI",  columnDefinition = "nvarchar(1000)")
	private String address;
	

	@Column(name = "NGAYSINH")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date dateOfBirth;
	
	@OneToOne
	@JoinColumn(name = "EMAIL")
	private Account account;
	
	@OneToMany(mappedBy = "customer")
	private List<ProductExchangeVoucher> exchangeVouchers = new ArrayList<ProductExchangeVoucher>();
	
	@OneToMany(mappedBy = "customer")
	private List<DetailsCart> detailsCarts = new ArrayList<DetailsCart>();

	public Customer() {
		super();
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<ProductExchangeVoucher> getExchangeVouchers() {
		return exchangeVouchers;
	}

	public void setExchangeVouchers(List<ProductExchangeVoucher> exchangeVouchers) {
		this.exchangeVouchers = exchangeVouchers;
	}

	public List<DetailsCart> getDetailsCarts() {
		return detailsCarts;
	}

	public void setDetailsCarts(List<DetailsCart> detailsCarts) {
		this.detailsCarts = detailsCarts;
	}


	
	
	
}
