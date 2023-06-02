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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HOA_DON")
public class Bill {
	@Id
	@Column(name = "MAHD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "TRANGTHAI")
	private int status;
	
	@Column(name = "DIACHI_NHAN", columnDefinition = "nvarchar(1000)")
	private String address;
	
	@Column(name = "SDT_NHAN", columnDefinition = "nvarchar(10)")
	private String telephone;
	
	@Column(name = "HOTEN_NHAN", columnDefinition = "nvarchar(100)")
	private String fullName;
	
	@Column(name = "GHICHU", columnDefinition = "nvarchar(1000)")
	private String note;
	
//	@Column(name = "NGAYAD")
//	private Date createDate;
	
	@Column(name = "NGAYTAOHOADON")
	private Date applicableDate;
	
	@Column(name = "TONGTIEN")
	private int totalPrice;
	
	@Column(name = "TIENKM")
	private int promotionlPrice;
	
	@ManyToOne
	@JoinColumn(name = "MASHIP")
	private Ship ship;
	
	@ManyToOne
	@JoinColumn(name = "MANV")
	private Staff staff;
	
	@OneToMany(mappedBy = "bill")
	private List<DetailsCart> detailsCarts;
	
	@OneToMany(mappedBy = "bill")
	private List<Seri> series = new ArrayList<Seri>();
	
	public List<Seri> getSeries() {
		return series;
	}

	public void setSeries(List<Seri> series) {
		this.series = series;
	}

	public Bill() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getApplicableDate() {
		return applicableDate;
	}

	public void setApplicableDate(Date applicableDate) {
		this.applicableDate = applicableDate;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<DetailsCart> getDetailsCarts() {
		return detailsCarts;
	}

	public void setDetailsCarts(List<DetailsCart> detailsCarts) {
		this.detailsCarts = detailsCarts;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPromotionlPrice() {
		return promotionlPrice;
	}

	public void setPromotionlPrice(int promotionlPrice) {
		this.promotionlPrice = promotionlPrice;
	}
	
	
}
