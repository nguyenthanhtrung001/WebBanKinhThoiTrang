package management.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CHITIET_SERI_DOI")
public class DetailsExchangeVoucher {
	
	@EmbeddedId
	private DetailsExchangeVoucherPk id;
	
	@ManyToOne
	@MapsId("seri")
	private Seri seri;
	
	@ManyToOne
	@MapsId("exchangeVoucher")
	private ProductExchangeVoucher exchangeVoucher;

	public DetailsExchangeVoucher(DetailsExchangeVoucherPk id, Seri seri, ProductExchangeVoucher exchangeVoucher) {
		super();
		this.id = id;
		this.seri = seri;
		this.exchangeVoucher = exchangeVoucher;
	}

	public DetailsExchangeVoucherPk getId() {
		return id;
	}

	public void setId(DetailsExchangeVoucherPk id) {
		this.id = id;
	}

	public Seri getSeri() {
		return seri;
	}

	public void setSeri(Seri seri) {
		this.seri = seri;
	}

	public ProductExchangeVoucher getExchangeVoucher() {
		return exchangeVoucher;
	}

	public void setExchangeVoucher(ProductExchangeVoucher exchangeVoucher) {
		this.exchangeVoucher = exchangeVoucher;
	}
	
	
}
