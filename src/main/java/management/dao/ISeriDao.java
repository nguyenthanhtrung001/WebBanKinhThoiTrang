package management.dao;

import java.util.List;


import management.entity.Seri;

public interface ISeriDao {
	long getQuantitySeriOfProduct(String idProdcut);
	List<Seri> get_List_SeriOfProduct(String idProdcut, Boolean status);
	public void update_Seri_TT_DAY(Seri seri);
	List<String> getTopSeriList(int top);
}
