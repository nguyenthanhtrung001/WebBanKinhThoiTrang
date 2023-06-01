package management.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class DetailsReturnVoucherPk implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String seri;
	
	private String returnVoucher;

	@Override
	public int hashCode() {
		return Objects.hash(returnVoucher, seri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailsReturnVoucherPk other = (DetailsReturnVoucherPk) obj;
		return Objects.equals(returnVoucher, other.returnVoucher) && Objects.equals(seri, other.seri);
	}
	
	
}
