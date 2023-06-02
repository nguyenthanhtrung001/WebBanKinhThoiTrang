package management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHIEU_BAO_HANH")
public class Warranty {
	@Id
	@Column(name = "MAPBH", length = 10)
	private String id;
	
	@Column(name = "DIACHI", columnDefinition = "nvarchar(500)")
	private String address;
	
	@Column(name = "SDTKH", length = 10)
	private String phoneNumber;
	
	@Column(name = "NGAYGUIBH", length = 10)
	private Date warrantyDate;
	
	@ManyToOne
    @JoinColumn(name = "SERI")
    private Seri seri;
	
	@ManyToOne
	@JoinColumn(name = "MANV")
	private Staff staff;

	public Warranty() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getWarrantyDate() {
		return warrantyDate;
	}

	public void setWarrantyDate(Date warrantyDate) {
		this.warrantyDate = warrantyDate;
	}

	public Seri getSeri() {
		return seri;
	}

	public void setSeri(Seri seri) {
		this.seri = seri;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	
	
}
