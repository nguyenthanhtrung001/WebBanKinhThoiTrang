package management.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class DetailsExchangeVoucherPk implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String seri;
	
	private String exchangeVoucher;

	
	
	public DetailsExchangeVoucherPk(String seri, String exchangeVoucher) {
		super();
		this.seri = seri;
		this.exchangeVoucher = exchangeVoucher;
	}

	public DetailsExchangeVoucherPk() {
		super();
	}

	public String getSeri() {
		return seri;
	}

	public void setSeri(String seri) {
		this.seri = seri;
	}

	public String getExchangeVoucher() {
		return exchangeVoucher;
	}

	public void setExchangeVoucher(String exchangeVoucher) {
		this.exchangeVoucher = exchangeVoucher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(exchangeVoucher, seri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailsExchangeVoucherPk other = (DetailsExchangeVoucherPk) obj;
		return Objects.equals(exchangeVoucher, other.exchangeVoucher) && Objects.equals(seri, other.seri);
	}
}
