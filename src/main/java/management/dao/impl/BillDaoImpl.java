package management.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import management.dao.IBillDao;
import management.entity.Bill;
import management.entity.DetailsCart;
import management.entity.DetailsExchangeVoucher;
import management.entity.DetailsExchangeVoucherPk;
import management.entity.ProductExchangeVoucher;
import management.entity.Seri;

@Repository
@Transactional
public class BillDaoImpl implements IBillDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public long getCountBill() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select count(*) from Bill");

		Long l = (Long) query.uniqueResult();
		session.close();
		return l;

	}

	@Override
	public List<Bill> getListBill() {
		Session session = sessionFactory.openSession();

		String hgl = "From Bill";

		Query query = session.createQuery(hgl);

		List<Bill> list = query.list();
		session.close();

		return list;
	}

	@Override
	public List<Bill> getListBillTT(int status) {
		Session session = sessionFactory.openSession();

		String hgl = "FROM Bill B WHERE B.status = " + status;

		Query query = session.createQuery(hgl);

		List<Bill> list = query.list();
		session.close();

		return list;
	}

	@Override
	public Bill getBill(String id) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Bill where id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Bill n = (Bill) query.list().get(0);

		session.close();

		return n;
	}

	
	
	// -----------------------
	@Override
	public List<Bill> getListBillOfCustomer(int id) {

		Session session = sessionFactory.openSession();

		String hql = "FROM Bill B WHERE EXISTS (SELECT 1 FROM B.detailsCarts DC WHERE DC.customer.id = :id)";

		Query query = session.createQuery(hql);
		query.setParameter("id", id + "");
		List<Bill> list = (List<Bill>) query.list();
		session.close();
		System.out.println(list);
		return list;
	}

	@Override
	public List<DetailsCart> getProductForBill(String maHD) {
		Session session = sessionFactory.openSession();

		String hql = "FROM DetailsCart D WHERE D.bill.id =:maHD";

		Query query = session.createQuery(hql);
		query.setParameter("maHD", maHD + "");
		List<DetailsCart> list = (List<DetailsCart>) query.list();
		session.close();
		System.out.println(list);
		return list;
	}
	
	
	@Override
	public List<Seri> get_n_SeriOfBillAndProduct(String maHD, String maSP, int n) {
		Session session = sessionFactory.openSession();
	    String hql = "FROM Seri S WHERE S.bill.id = :maHD AND S.product.id = :maSP";

	    Query query = session.createQuery(hql);
	    query.setParameter("maHD", maHD);
	    query.setParameter("maSP", maSP);
	    query.setMaxResults(n);
	    
	    List<Seri> list = (List<Seri>)query.list();
	    System.out.println("hql: "+ list.get(0));
	    return list;
	}

	@Override
	public void addProductExchangeVoucher(ProductExchangeVoucher pev) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(pev);
			System.out.println("Thêm thành công phiếu đổi: " + pev.getId());
			transaction.commit();
		} catch (Exception e) {
			System.out.println("add fail");
			e.printStackTrace();
			transaction.rollback();
		} finally {

			session.close();
		}
		
	}


	@Override
	public void addDetailsExchangeVoucherPK(DetailsExchangeVoucherPk dev_pk) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(dev_pk);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("add fail");
			e.printStackTrace();
			transaction.rollback();
		} finally {

			session.close();
		}
	}
	
	@Override
	public void addDetailsExchangeVoucher(DetailsExchangeVoucher dev) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(dev);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("add fail");
			e.printStackTrace();
			transaction.rollback();
		} finally {

			session.close();
		}
	}
	
	@Override
	public int soLuongPhieuDoi() {
		Session s = sessionFactory.openSession();
		
		String hql = "SELECT COUNT(*) FROM ProductExchangeVoucher";
		
		Query query = s.createQuery(hql);
		
		Long count = (Long) query.uniqueResult();
		
        return count.intValue();
	}

	@Override
	public Bill getBill(int id) {
		 Session session = sessionFactory.openSession();
		    String hql = "FROM Bill WHERE id = :id";
		    Query query = session.createQuery(hql);
		    query.setParameter("id", id);
		    Bill bill = (Bill) query.uniqueResult();
		    session.close();
		    return bill;
	}

	@Override
	public Bill create_Bill(Bill bill) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    
	    try {
	        tx = session.beginTransaction();
	       
	        session.save(bill); 
	        tx.commit(); 
	        return bill;
	       
	       
	    } catch (Exception e) {
	       
	            tx.rollback(); 
	            
	        e.printStackTrace();
	        return null;
	    } finally {
	        
	            session.close(); 
	       
	    }
	}

	
	@Override
	public void updateStatus(int billId, int newStatus) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
		    
			Bill bill = getBill(billId);
			System.out.println("id sap updatw:"+bill.getId());

			bill.setStatus(newStatus);

			session.update(bill);

			transaction.commit();
			System.out.println("success");
		    
		} catch (Exception e) {
		    System.out.println("update fail");
		    e.printStackTrace();
		    transaction.rollback();
		} finally {
		   
		    session.close();
		}
		

		
	}

	
	
	
}
