package management.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import management.dao.IPromotionDao;

import management.entity.Promotion;


@Repository  
@Transactional
public class PromotionDaoImpl implements IPromotionDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	

	@Override
	public List<Promotion> getListPromotion() {
		 Session session = sessionFactory.openSession();
		    String hql = "FROM Promotion WHERE startDate <= :currentDate and endDate>=:currentDate";
		    Query query = session.createQuery(hql);
		    query.setParameter("currentDate", new Date());
		    
		    List<Promotion> promotions =query.list();
		   
		    session.close();
		    return promotions;
	}

}
	
