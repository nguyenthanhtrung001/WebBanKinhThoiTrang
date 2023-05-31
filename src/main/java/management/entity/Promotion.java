package management.entity;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KHUYEN_MAI")
public class Promotion {
	@Id
	@Column(name = "MAKM", length = 10)
	private String id;

	@Column(name = "TENKM", length = 500)
	private String name;

	@Column(name = "NGAYBD")
	private Date startDate;

	@Column(name = "NGAYKT")
	private Date endDate;

	@Column(name = "MUCKM")
	private Double promotionLitmit;

	@ManyToOne
	@JoinColumn(name = "MANV")
	private Staff staff;
	
	@OneToMany(mappedBy = "promotion",fetch = FetchType.EAGER)
	private Set<DetailsPromotion> detailsPromotions;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getPromotionLitmit() {
		return promotionLitmit;
	}
	public String getPromotionLitmit_VND() {
		NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String formattedPrice = vndFormat.format(promotionLitmit);
		return formattedPrice;
	}

	public void setPromotionLitmit(Double promotionLitmit) {
		this.promotionLitmit = promotionLitmit;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Set<DetailsPromotion> getDetailsPromotions() {
		return detailsPromotions;
	}

	public void setDetailsPromotions(Set<DetailsPromotion> detailsPromotions) {
		this.detailsPromotions = detailsPromotions;
	}

	public Promotion() {
		super();
	} 
	
	

}
