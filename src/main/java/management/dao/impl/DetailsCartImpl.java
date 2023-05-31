package management.dao.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ViewMethodReturnValueHandler;

import management.dao.IAccountDao;
import management.dao.ICustomerDao;
import management.dao.IDetailsCartDao;
import management.dao.IRoleDao;
import management.entity.Account;
import management.entity.Bill;
import management.entity.DetailsCart;
import management.entity.Role;
import management.entity.Staff;

@Repository  
@Transactional
public class DetailsCartImpl implements IDetailsCartDao{
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	


	@Override
	public boolean deleteProductfor_(Long id) {
		 Session session = sessionFactory.openSession();
		    Transaction tx = null;
		    
		    try {
		        tx = session.beginTransaction();
		        DetailsCart detailsCart = (DetailsCart) session.get(DetailsCart.class, id);
		        
		        if (detailsCart != null && detailsCart.getBill() == null) {
		            session.delete(detailsCart);
		            tx.commit();
		            return true;
		        } else {
		            return false;
		        }
		    } catch (Exception e) {
		        if (tx != null) tx.rollback();
		        e.printStackTrace();
		        return false;
		    } finally {
		        session.close();
		    }
	}

	

	@Override
	public void updateDetailsCart(int id) {
		
	}



	@Override
	public boolean addProductfor_(DetailsCart DetailsCart) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    
	    try {
	        tx = session.beginTransaction();
	        session.save(DetailsCart); 
	       
	        tx.commit(); 
	        return true;
	       
	       
	    } catch (Exception e) {
	       
	            tx.rollback(); 
	           
	        
	        
	        e.printStackTrace();
	        return false;
	    } finally {
	        
	            session.close(); 
	       
	    }
		
		
	}



	@Override
	public List<DetailsCart> getDetailsCart(int kh) {
	    Session session = sessionFactory.openSession();

	    String hql = "FROM DetailsCart B WHERE B.bill = NULL AND B.customer.id = :kh";

	    Query query = session.createQuery(hql);
	    query.setParameter("kh", kh);

	    List<DetailsCart> list = query.list();
	    session.close();

	    return list;
	}




	@Override
	public DetailsCart checkSP_cart(String maSP) {
		Session session = sessionFactory.openSession();
	    String hql = "FROM DetailsCart dc WHERE dc.detailsUpdatePrice.id.productId = :maSP and dc.bill=null";
	    Query query = session.createQuery(hql);
	    query.setParameter("maSP", maSP);
	    List<DetailsCart> n = query.list();
	    session.close();
	    System.out.println("Số lượng sản phẩm trong giỏ hàng: " + n.size());

	    if (n.size() > 0) return n.get(0);
	    else return null;
	}



	@Override
	public long getID(String masp) {
		 Session session = sessionFactory.openSession();
		    String hql = "SELECT dc.id FROM DetailsCart dc WHERE dc.detailsUpdatePrice.id.productId = :maSP AND dc.bill IS NULL";
		    Query query = session.createQuery(hql);
		    query.setParameter("maSP", masp);
		    List<Long> resultList = query.list();
		    session.close();
		    System.out.println("Số lượng sản phẩm trong giỏ hàng: " + resultList.size());

		    if (!resultList.isEmpty()) {
		        return resultList.get(0);
		    } else {
		        return 0;
		    }
	}



	@Override
	public void updateQuantityById(Long id, Integer newQuantity) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        String hql = "UPDATE DetailsCart dc SET dc.quantity = :newQuantity WHERE dc.id = :id";
	        Query query = session.createQuery(hql);
	        query.setParameter("newQuantity", newQuantity);
	        query.setParameter("id", id);
	        int result = query.executeUpdate();
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
		
	}



	@Override
	public int getQuantitybyID(long id) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT dc.quantity FROM DetailsCart dc WHERE dc.id = :id and dc.bill=null";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		try {
			Integer quantity = (Integer) query.uniqueResult();
			
			return quantity;
		} catch (Exception e) {
			return 0;

		}finally {
			session.close();
		}
		
		

		
	}



	@Override
	public Date getLatestApplicableDateByProductId(String productId) {
		Session session = sessionFactory.openSession();
	    String hql = "SELECT MAX(dup.id.applicableDate) FROM DetailsUpdatePrice dup JOIN dup.product p WHERE p.id = :productId";
	    Query query = session.createQuery(hql);
	    query.setParameter("productId", productId);
	    Timestamp latestApplicableDate = (Timestamp) query.uniqueResult();
	    session.close();
	    return new Date(latestApplicableDate.getTime());
	}



	@Override
	public void update_HD_DetailsCart(Long id,Bill bill) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        String hql = "UPDATE DetailsCart dc SET dc.bill = :bill WHERE dc.id = :id";
	        Query query = session.createQuery(hql);
	        query.setParameter("bill", bill);
	        query.setParameter("id", id);
	        int result = query.executeUpdate();
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
		
		
	}



	@Override
	public void update_Price_DetailsCart(int id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public DetailsCart get_One_P_Cart_Pay(Long id) {
		Session session = sessionFactory.openSession();
		String hql = "FROM DetailsCart WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		DetailsCart p = (DetailsCart) query.uniqueResult();
		session.close();

		return p;
	}



	@Override
	public String get_name_customer_by_IDHD(int id) {
	    Session session = sessionFactory.openSession();
	    String hql = "SELECT dc.customer.name FROM DetailsCart dc WHERE dc.bill.id = :id";
	    Query query = session.createQuery(hql);
	    query.setParameter("id", id);
	    String name = (String)  query.list().get(0);
		
	    session.close();
	    return name;
	}



	@Override
	public double getTotalAmountOfBillItems() {
		Session session = sessionFactory.openSession();

		String hql = "SELECT SUM(dup.price * dc.quantity) " +
                "FROM DetailsCart dc " +
                "JOIN dc.detailsUpdatePrice dup " +
                "WHERE dc.bill IS NOT NULL";

	    Query query = session.createQuery(hql);

	    Double totalAmount = (Double) query.uniqueResult();

	    session.close();

	    return totalAmount != null ? totalAmount : 0.0;
	}




	
	
	



	

	
}
	
