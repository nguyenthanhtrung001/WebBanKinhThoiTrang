package management.dao;

import management.entity.DetailsPromotion;

public interface DetailsPromotionDao {
	DetailsPromotion getDetailsPromotionDaoOfProductAndStatus(String productId,boolean staus);
}
