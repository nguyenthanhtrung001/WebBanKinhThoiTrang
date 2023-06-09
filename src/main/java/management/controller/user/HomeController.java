package management.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import management.dao.DetailsPromotionDao;
import management.dao.ICategoryDao;
import management.dao.IProductDao;
import management.entity.Category;
import management.entity.DetailsPromotion;
import management.entity.Paging;
import management.entity.Product;

@Controller
@RequestMapping("/user/")
public class HomeController {

	@Autowired
	private ICategoryDao categoryDao;

	@Autowired
	private IProductDao productDao;

	@Autowired
	private DetailsPromotionDao detailsPromotionDao;

	@GetMapping(path = { "home", "home/{cateogryId}" })
	public ModelAndView showHome(@PathVariable(name = "cateogryId", required = false) String caterogyId,
			@RequestParam(name = "search", defaultValue = "") String search,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "4") int limit) {

		ModelAndView mav = new ModelAndView("user/Home");

		String checkCategoryId = null;

		String checkSearch = null;

		Paging paging = new Paging();

		paging.setPage(page);

		paging.setMaxPageItem(limit);

		List<Category> listOfCategory = categoryDao.getCategoriesIsAcctive(true);

		mav.addObject("listOfCategory", listOfCategory);

		List<Product> listOfProduct = new ArrayList<Product>();

		if (caterogyId != null) {

			listOfProduct = productDao.getProductsOfCategory(caterogyId, page - 1, limit);

			paging.setTotalItem(productDao.getQuantityProductsOfCategory(caterogyId));

			checkCategoryId = caterogyId;

		} else if (search != "") {
			paging.setSearch(search);
			listOfProduct = productDao.getProductsContainingString(search, page - 1, limit);
			paging.setTotalItem(productDao.getQuantityProductsContainingString(search));
			checkSearch = search;
		} else {
			listOfProduct = productDao.getProductsIsAcctive(true, page - 1, limit);
			paging.setTotalItem(productDao.getQuantityProductsIsAcctive(true));
		}
		List<String> listreduce = new ArrayList<String>()
				
				;
		for (Product product : listOfProduct) {
			DetailsPromotion detailsPromotion = detailsPromotionDao
					.getDetailsPromotionDaoOfProductAndStatus(product.getId(), true);
			if (detailsPromotion != null) {
				double promotionLimit = detailsPromotion.getPromotion().getPromotionLitmit();
				double price = product.getDetailsUpdatePrices().get(product.getDetailsUpdatePrices().size() - 1)
						.getPrice();
				double reducePrice = price * promotionLimit;
				listreduce.add(product.handlePromotion(reducePrice));
			} else {
				listreduce.add("");
			}
		}
		paging.setTotalPage((int) Math.ceil((double) paging.getTotalItem() / paging.getMaxPageItem()));
		mav.addObject("listreduce", listreduce);
		mav.addObject("listOfProduct", listOfProduct);
		mav.addObject("checkCategoryId", checkCategoryId);
		mav.addObject("checkSearch", checkSearch);
		mav.addObject("paging", paging);
		return mav;
	}

}