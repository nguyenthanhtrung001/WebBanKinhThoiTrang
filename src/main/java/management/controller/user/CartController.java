package management.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import management.dao.DetailsPromotionDao;
import management.dao.IBillDao;
import management.dao.ICustomerDao;
import management.dao.IDetailsCartDao;
import management.dao.IProductDao;
import management.dao.ISeriDao;
import management.dao.IShipDao;
import management.entity.Bill;
import management.entity.Customer;
import management.entity.DetailsCart;
import management.entity.DetailsPromotion;
import management.entity.Product;
import management.entity.Ship;

@Controller
@RequestMapping("/user/")
public class CartController {

	@Autowired
	private IProductDao productDao;

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private IDetailsCartDao detailsCartDao;

	@Autowired
	private ISeriDao seriDao;

	@Autowired
	private DetailsPromotionDao detailsPromotionDao;

	@Autowired
	private IShipDao shipDao;

	@Autowired
	private IBillDao billDao;

	@GetMapping("cart")
	public ModelAndView showPageDetailsProduct(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("user/cart");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		List<DetailsCart> cart = detailsCartDao.getDetailsCartsOfCustomerYetBuy(customer.getId());
		List<String> listreduce = new ArrayList<String>();
		for (DetailsCart detailsCart : cart) {
			Product product = productDao.getProductById(detailsCart.getDetailsUpdatePrice().getProduct().getId());
			detailsCart.setDetailsUpdatePrice(
					product.getDetailsUpdatePrices().get(product.getDetailsUpdatePrices().size() - 1));
			detailsCartDao.updateDetailsCart(detailsCart);
			DetailsPromotion detailsPromotion = detailsPromotionDao
					.getDetailsPromotionDaoOfProductAndStatus(product.getId(), true);
			double reducePrice = 0;
			if (detailsPromotion != null) {
				double promotionLimit = detailsPromotion.getPromotion().getPromotionLitmit();
				double price = product.getDetailsUpdatePrices().get(product.getDetailsUpdatePrices().size() - 1)
						.getPrice();
				reducePrice = price * promotionLimit;
				listreduce.add(product.handlePromotion(reducePrice));
			} else {
				listreduce.add("");
			}
		}
		List<DetailsCart> newCart = detailsCartDao.getDetailsCartsOfCustomerYetBuy(1);
		if (newCart.size() != 0) {
			mav.addObject("listreduce", listreduce);
			mav.addObject("cart", newCart);
			mav.addObject("NoDetailsCart", false);
		} else {
			mav.addObject("NoDetailsCart", true);
		}
		return mav;
	}

	@GetMapping("confirm")
	public ModelAndView confirmBuyProducts(HttpServletRequest request,
			@RequestParam(value = "checkboxValues", required = false) String[] checkboxValues) {
		ModelAndView mav = new ModelAndView("user/OrderSlip");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		double sumMoney = 0;
		List<DetailsCart> list = new ArrayList<DetailsCart>();
		List<String> listreduce = new ArrayList<String>();
		for (String idCart : checkboxValues) {
			DetailsCart detailsCart = detailsCartDao.getDetailsCartById(Long.parseLong(idCart));
			detailsCartDao.updateDetailsCart(detailsCart);
			Product product = detailsCart.getDetailsUpdatePrice().getProduct();
			DetailsPromotion detailsPromotion = detailsPromotionDao
					.getDetailsPromotionDaoOfProductAndStatus(product.getId(), true);
			double reducePrice = 0;
			double price = product.getDetailsUpdatePrices().get(product.getDetailsUpdatePrices().size() - 1).getPrice();
			if (detailsPromotion != null) {
				double promotionLimit = detailsPromotion.getPromotion().getPromotionLitmit();
				reducePrice = price * promotionLimit;
				listreduce.add(product.handlePromotion(reducePrice));
			} else {
				listreduce.add("");
				reducePrice = price;
			}
			list.add(detailsCart);
			sumMoney += reducePrice * detailsCart.getQuantity();
		}
		session.setAttribute("listProductId", checkboxValues);
		mav.addObject("listreduce", listreduce);
		mav.addObject("user", customer);
		mav.addObject("sumMoney", sumMoney);
		mav.addObject("date", java.time.LocalDate.now());
		mav.addObject("list", list);
		return mav;
	}

	@PostMapping("order/confirm")
	public ModelAndView confirmOrder(HttpServletRequest request, @RequestParam("ht") String t,
			@RequestParam("sdt") String std, @RequestParam("dc") String dc, @RequestParam("nd") String note) {
		ModelAndView mav = new ModelAndView("user/ThankYou");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		String address = customer.getAddress();
		Ship ship = new Ship();
		if (address.contains("TP Hồ Chí Minh")) {
			ship = shipDao.getShipId("HCM");
		} else if (address.contains("TP Ho Chi Minh")) {
			ship = shipDao.getShipId("HCM");
		} else if (address.contains("TP ho chi minh")) {
			ship = shipDao.getShipId("HCM");
		} else {
			ship = shipDao.getShipId("NHCM");
		}
		Bill bill = new Bill();
		bill.setAddress(address);
		bill.setFullName(t);
		bill.setTelephone(std);
		bill.setNote(note);
		bill.setShip(ship);
		bill.setStatus(0);
		bill.setApplicableDate(new Date());
		bill.setNote(note);
		List<DetailsCart> cart = new ArrayList<DetailsCart>();
		String[] checkboxValues = (String[]) session.getAttribute("listProductId");
		double sumPrice = 0;
		double sumPromotionPrice = 0;
		for (String string : checkboxValues) {
			DetailsCart detailsCart = detailsCartDao.get_One_P_Cart_Pay(Long.parseLong(string));
			cart.add(detailsCart);
			Product product = detailsCart.getDetailsUpdatePrice().getProduct();
			DetailsPromotion detailsPromotion = detailsPromotionDao
					.getDetailsPromotionDaoOfProductAndStatus(product.getId(), true);
			double price = product.getDetailsUpdatePrices().get(product.getDetailsUpdatePrices().size() - 1).getPrice();
			sumPrice += price * detailsCart.getQuantity();
			;
			if (detailsPromotion != null) {
				double promotionLimit = detailsPromotion.getPromotion().getPromotionLitmit();
				double reducePrice = price * promotionLimit;
				sumPromotionPrice = reducePrice * detailsCart.getQuantity();
			}
		}
		sumPrice += ship.getPrice();
		bill.setTotalPrice(sumPrice);
		bill.setPromotionlPrice(sumPromotionPrice);
		int primaryKey = billDao.createBill(bill);
		bill = billDao.getBillById(primaryKey);
		for (DetailsCart detailsCart : cart) {
			detailsCart.setBill(bill);
			detailsCartDao.updateDetailsCart(detailsCart);
		}
		return mav;
	}

	@GetMapping("cart/{idProduct}")
	public ModelAndView addDetailsProduct(HttpServletRequest request,
			@PathVariable(name = "idProduct", required = false) String idProduct) {
		ModelAndView mav = new ModelAndView("user/cart");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		Product product = productDao.getProductById(idProduct);
		DetailsCart detailsCart = new DetailsCart();
		detailsCart.setQuantity(1);
		detailsCart.setDetailsUpdatePrice(
				product.getDetailsUpdatePrices().get(product.getDetailsUpdatePrices().size() - 1));
		detailsCart.setCustomer(customer);
		detailsCartDao.save(detailsCart);
		customer.getDetailsCarts().add(detailsCart);
		customerDao.update(customer);
		List<DetailsCart> cart = detailsCartDao.getDetailsCartsOfCustomerYetBuy(customer.getId());
		mav.addObject("cart", cart);
		mav.addObject("NoDetailsCart", false);
		return mav;
	}

	@GetMapping("/cart/remove/{idRemove}")
	public ModelAndView removeDetailsProduct(HttpServletRequest request,
			@PathVariable(name = "idRemove", required = false) Long idRemove) {
		ModelAndView mav = new ModelAndView("user/cart");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		DetailsCart detailsCart = detailsCartDao.getDetailsCartById(idRemove);
		detailsCartDao.deteleDetailsCart(detailsCart);
		List<DetailsCart> cart = detailsCartDao.getDetailsCartsOfCustomerYetBuy(customer.getId());
		if (cart != null) {
			mav.addObject("cart", cart);
			mav.addObject("NoDetailsCart", false);
		} else {
			mav.addObject("NoDetailsCart", true);
		}
		return mav;
	}

	@GetMapping("/cart/remove/all")
	public ModelAndView removeAllDetailsProduct(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("user/cart");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		List<DetailsCart> cart = detailsCartDao.getDetailsCartsOfCustomerYetBuy(customer.getId());
		for (DetailsCart detailsCart : cart) {
			detailsCartDao.deteleDetailsCart(detailsCart);
		}
		mav.addObject("NoDetailsCart", true);
		return mav;
	}

	@GetMapping(path = { "/cart/{idCart}/except", "/cart/{idCart}/add" })
	public ModelAndView changeQuantityOfProductInDetailsProduct(HttpServletRequest request,
			@PathVariable(name = "idCart", required = false) Long idCart) {
		ModelAndView mav = new ModelAndView("user/cart");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		String url = request.getRequestURL().toString();
		DetailsCart detailsCart = detailsCartDao.getDetailsCartById(idCart);
		List<String> listreduce = new ArrayList<String>();
		if (url.contains("add")) {
			long quantityOfSeri = seriDao
					.getQuantitySeriOfProduct(detailsCart.getDetailsUpdatePrice().getProduct().getId());
			detailsCart.getDetailsUpdatePrice().getPrice();
			if (detailsCart.getQuantity() < quantityOfSeri) {
				detailsCart.setQuantity(detailsCart.getQuantity() + 1);
			}
		} else {
			if (detailsCart.getQuantity() > 0) {
				detailsCart.setQuantity(detailsCart.getQuantity() - 1);
			}
		}
		detailsCartDao.updateDetailsCart(detailsCart);
		List<DetailsCart> cart = detailsCartDao.getDetailsCartsOfCustomerYetBuy(customer.getId());
		for (DetailsCart detailsCart1 : cart) {
			Product product = productDao.getProductById(detailsCart1.getDetailsUpdatePrice().getProduct().getId());
			detailsCart1.setDetailsUpdatePrice(
					product.getDetailsUpdatePrices().get(product.getDetailsUpdatePrices().size() - 1));
			detailsCartDao.updateDetailsCart(detailsCart1);
			DetailsPromotion detailsPromotion = detailsPromotionDao
					.getDetailsPromotionDaoOfProductAndStatus(product.getId(), true);
			double reducePrice = 0;
			if (detailsPromotion != null) {
				double promotionLimit = detailsPromotion.getPromotion().getPromotionLitmit();
				double price = product.getDetailsUpdatePrices().get(product.getDetailsUpdatePrices().size() - 1)
						.getPrice();
				reducePrice = price * promotionLimit;
				listreduce.add(product.handlePromotion(reducePrice));
			} else {
				listreduce.add("");
			}
		}
		mav.addObject("cart", cart);
		mav.addObject("listreduce", listreduce);
		mav.addObject("NoDetailsCart", false);
		return mav;
	}

}