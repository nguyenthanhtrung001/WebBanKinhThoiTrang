package management.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import management.dao.IStaffDao;
import management.entity.Account;
import management.entity.Staff;

@Repository
@Transactional
public class StaffDaoImpl implements IStaffDao  {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Staff> getListStaff(int status) {
		Session session=sessionFactory.openSession();
		
		String hgl= "FROM Staff B WHERE B.account.status = "+status;
		Query query=session.createQuery(hgl);
		List<Staff> list=query.list();
		return list;
	
		
	}
	
	

	@Override
	public Staff addStaff(Staff staff, Account account) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    
	    try {
	        tx = session.beginTransaction();
	        session.save(account); 
	        session.save(staff); 
	        tx.commit(); 
	        return staff;
	       
	    } catch (Exception e) {
	       
	            tx.rollback(); 
	            e.printStackTrace();
	            return null;
	        
	        
	        
	    } finally {
	        
	            session.close(); 
	       
	    }
		
	}



	@Override
	public void updateStaff(Staff staff, Account account) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    
	    try {
	        tx = session.beginTransaction();
	        session.update(account); 
	        session.update(staff); 
	        tx.commit(); 
	       
	    } catch (Exception e) {
	       
	            tx.rollback(); 
	        
	        
	        e.printStackTrace();
	    } finally {
	        
	            session.close(); 
	       
	    }
		
	}



	
	@Override
	public Staff getStaff(String email) {
	email = email.trim();
	Session session = sessionFactory.openSession();
	String hql = "FROM Staff WHERE account.email = :email";
	Query query = session.createQuery(hql);
	query.setParameter("email", email);
	Staff staff = (Staff) query.uniqueResult();
	session.close();
	return staff;
	}
	

}
