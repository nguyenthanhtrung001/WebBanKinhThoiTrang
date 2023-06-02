package management.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CHITIET_SERI_TRA")
public class DetailsReturnVoucher {
	
	@EmbeddedId
	private DetailsReturnVoucherPk id;
	
	@ManyToOne
	@MapsId("seri")
	private Seri seri;
	
	@ManyToOne
	@MapsId("returnVoucher")
	private ProductReturnVoucher returnVoucher;

	public DetailsReturnVoucher() {
		super();
	}

	public DetailsReturnVoucherPk getId() {
		return id;
	}

	public void setId(DetailsReturnVoucherPk id) {
		this.id = id;
	}

	public Seri getSeri() {
		return seri;
	}

	public void setSeri(Seri seri) {
		this.seri = seri;
	}

	public ProductReturnVoucher getReturnVoucher() {
		return returnVoucher;
	}

	public void setReturnVoucher(ProductReturnVoucher returnVoucher) {
		this.returnVoucher = returnVoucher;
	}
	
	
}
