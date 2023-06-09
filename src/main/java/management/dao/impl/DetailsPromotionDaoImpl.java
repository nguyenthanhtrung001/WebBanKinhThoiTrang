package management.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import management.dao.DetailsPromotionDao;
import management.entity.DetailsPromotion;

@Repository  
@Transactional
public class DetailsPromotionDaoImpl implements DetailsPromotionDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public DetailsPromotion getDetailsPromotionDaoOfProductAndStatus(String productId, boolean staus) {
		Session session = sessionFactory.openSession();
		String hql = "FROM DetailsPromotion as dp where dp.product.id = ? and dp.status = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, productId);
		query.setParameter(1, staus);
		
		if (query.list().size() == 0) {
			session.close();
			return null;
		}
		
		DetailsPromotion n = (DetailsPromotion) query.list().get(0);

		session.close();

		return n;
	}

}
