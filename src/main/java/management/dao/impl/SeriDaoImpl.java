package management.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import management.dao.ISeriDao;
import management.entity.Account;
import management.entity.Seri;

@Repository
@Transactional
public class SeriDaoImpl implements ISeriDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public long getQuantitySeriOfProduct(String idProdcut) {
		Session s = sessionFactory.openSession();

		String hql = "select count(s) from Seri s where s.product.id = ? and s.status = ?";

		Query query = s.createQuery(hql);

		query.setParameter(0, idProdcut);
		
		query.setParameter(1, false);

		return (long) query.uniqueResult();
	}

	@Override
	public List<Seri> get_List_SeriOfProduct(String idProdcut,Boolean status) {
		Session session = sessionFactory.openSession();
		String hql = "from Seri s where s.product.id = ? and s.status = ?";
		Query query = session.createQuery(hql);

		query.setParameter(0, idProdcut);
		
		query.setParameter(1, status);
		List<Seri> list =  query.list();
		session.close();
		return list;
		
	}

	@Override
	public void update_Seri_TT_DAY(Seri seri) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        session.update(seri); 
	        tx.commit(); 
	       
	       
	    } catch (Exception e) {
	    
	            tx.rollback(); 
	        
	        e.printStackTrace();
	    } finally {
	       
	            session.close(); 		        
	    }
	
	}

	@Override
	public List<String> getTopSeriList(int top) {
	    Session session = sessionFactory.openSession();

	    String hql = "SELECT s.product.id, COUNT(s.id) AS SOSERICount " +
	                 "FROM Seri s " +
	                 "WHERE s.status = true " +
	                 "GROUP BY s.product.id " +
	                 "ORDER BY SOSERICount DESC";

	    Query query = session.createQuery(hql);
	    query.setMaxResults(top);
	    List<Object[]> resultList = query.list();

	    List<String> topSeriList = new ArrayList<>();
	    for (Object[] result : resultList) {
	        String seriId = (String) result[0];
	        topSeriList.add(seriId);
	    }

	    session.close();
	    return topSeriList;
	}

}
