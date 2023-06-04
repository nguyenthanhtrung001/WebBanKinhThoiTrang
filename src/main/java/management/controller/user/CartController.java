package management.controller.user;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import management.bean.Message;
import management.dao.IBillDao;
import management.dao.IDetailsCartDao;
import management.dao.IProductDao;

import management.dao.IPromotionDao;
import management.dao.ISeriDao;
import management.entity.Account;
import management.entity.Bill;
import management.entity.Customer;
import management.entity.DetailsCart;
import management.entity.DetailsUpdatePrice;
import management.entity.DetailsUpdatePricePK;
import management.entity.Product;
import management.entity.Promotion;
import management.entity.Role;
import management.entity.Seri;


@Controller
@RequestMapping("/user/")
public class CartController {

	@Autowired
	private IDetailsCartDao detailsCartDao;
	@Autowired
	private IBillDao billDao;
	@Autowired
	private IPromotionDao promotionDao;
	@Autowired
	private ISeriDao seriDao;

	// List<DetailsCart> listTmp = new ArrayList<>();

	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public ModelAndView getcart(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Object userObj = session.getAttribute("user");
		session.removeAttribute("cart");

		if (userObj == null) {
			return new ModelAndView("redirect:/login");
		}
		Customer user = (Customer) userObj;

		List<DetailsCart> list = detailsCartDao.getDetailsCart(user.getId());

		model.addAttribute("cart", list);
		ModelAndView modelAndView = new ModelAndView("user/cart");
		return modelAndView;

	}

	@RequestMapping(value = "cart/add/id={id}", method = RequestMethod.POST)
	public String add_product_cart(ModelMap model, @ModelAttribute("cart") DetailsCart cart, BindingResult errors,

			@PathVariable("id") String maSP, @RequestParam("quantity") String soLuong, HttpSession ss,
			HttpServletRequest request, RedirectAttributes redirectAttributes)
			throws ParseException, NoSuchAlgorithmException {

		HttpSession session = request.getSession();
		Object userObj = session.getAttribute("user");

		if (userObj == null) {
			return "redirect:/login";
		}
		Customer user = (Customer) userObj;
		Date ngayADDate = detailsCartDao.getLatestApplicableDateByProductId(maSP);
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
		// Locale.ENGLISH);
		// Date ngayADDate = formatter.parse(ngayAD);

		DetailsCart carttmp = detailsCartDao.checkSP_cart(maSP);

		if (carttmp != null) {
			long idtmp = (long) detailsCartDao.getID(maSP);
			int q = detailsCartDao.getQuantitybyID(idtmp) + Integer.parseInt(soLuong);
			detailsCartDao.updateQuantityById(idtmp, q);

			return "redirect:/user/cart";
		}

		try {
			Customer customer = new Customer();

			customer.setId(user.getId());

			cart.setCustomer(customer);
			DetailsUpdatePrice price = new DetailsUpdatePrice();
			DetailsUpdatePricePK pricePK = new DetailsUpdatePricePK();
			pricePK.setProductId(maSP);
			pricePK.setApplicableDate(ngayADDate);
			price.setId(pricePK);
			// String soLuong="1";
			cart.setQuantity(Integer.parseInt(soLuong));
			cart.setDetailsUpdatePrice(price);

			if (detailsCartDao.addProductfor_(cart))
				System.out.println("Thanh cong");
			else
				System.out.println("That Bai");

			redirectAttributes.addFlashAttribute("message", new Message("success", "Thêm mới thành công"));

			return "redirect:/user/cart";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thất bại thêm tài khoản");
			redirectAttributes.addFlashAttribute("message", new Message("error", "Thêm mới thất bại"));
			System.out.println("Thất bại thêm nhân viên3");
			return "redirect:/user/cart";

		}

	}

	@ModelAttribute("listPromotion")
	public List<Promotion> getListPromotions() {
		List<Promotion> promotions = promotionDao.getListPromotion();

		for (Promotion p : promotions) {
			System.out.println(p.getId());

		}
		return promotionDao.getListPromotion();

	}

	@RequestMapping(value = "cart/del_products", method = RequestMethod.POST)
	public String del_products(@RequestParam(value = "productId", required = false) List<Long> productIds) {
		// Process the selected products here
		if (productIds != null) {
			for (long productId : productIds) {
				System.out.println("id:" + productId);
				if (detailsCartDao.deleteProductfor_(productId))
					System.out.println("xoa tt");
				else
					System.out.println("x tb");
			}
		} else {
			System.out.println("No items selected");
		}

		// Redirect to the checkout page
		return "redirect:/user/cart";
	}

	@RequestMapping("cart/del_product/id={id}")
	public String delete_product_id(ModelMap model, @PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		System.out.println("id:" + id);
		detailsCartDao.deleteProductfor_(Long.parseLong(id));
		return "redirect:/user/cart";
	}

	@RequestMapping("cart/payingN")
	public ModelAndView paying(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam(value = "productId", required = false) List<Long> productIds) {
		// Process the selected products here
		HttpSession session1 = request.getSession();
		List<DetailsCart> list = new ArrayList<DetailsCart>();

		// model.addAttribute("cart", list);
		//

		if (session1.getAttribute("cart") == null) {
			// listTmp.clear();
			if (productIds != null) {
				for (long productId : productIds) {
					// detailsCartDao.updateQuantityById(productId, Integer.parseInt(quantity));
					list.add(detailsCartDao.get_One_P_Cart_Pay(productId));
				}
			} else {
				System.out.println("No items selected");
				return new ModelAndView("redirect:/user/cart");

			}
			// listTmp = list;
			session1.setAttribute("cart", list);
			System.out.println("cart:" + list.size());
		}

		Customer cus = (Customer) session1.getAttribute("user");

		model.addAttribute("cus", cus);
		double ship = 0;

		if (cus.getAddress().contains("Minh")) {
			ship = 15000;

		} else
			ship = 35000;
		String tinh = Helpper.layTenTinhTuDiaChi(cus.getAddress());
		String diaChi_ = Helpper.loaiBoTenTinh(cus.getAddress());
		model.addAttribute("ship", ship);
		model.addAttribute("tinh", tinh);
		model.addAttribute("diachi", diaChi_);

		ModelAndView modelAndView = new ModelAndView("user/paying");

		return modelAndView;
	}

	@RequestMapping(value = "cart/paying/success", method = RequestMethod.POST)
	public ModelAndView success(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "moneyship", required = false, defaultValue = "0") double ship,
			@RequestParam(value = "total", required = false, defaultValue = "0") double total,
			@RequestParam(value = "promotionVoucher", required = false, defaultValue = "0") double voucher) {

		System.out.println("coucher" + voucher);
		List<DetailsCart> listTmp = new ArrayList<>();
		HttpSession session = request.getSession();
		listTmp = (List<DetailsCart>) session.getAttribute("cart");
		System.out.println("sub:" + listTmp.size());
		Bill newBill = new Bill();
		// newBill.setStaff(null);
		newBill.setStatus(0);
		newBill.setApplicableDate(new Date());
		billDao.create_Bill(newBill);
		for (DetailsCart detailsCart : listTmp) {
			detailsCartDao.update_HD_DetailsCart(detailsCart.getId(), newBill);
			int i = detailsCart.getQuantity();
			for (Seri s : seriDao.get_List_SeriOfProduct(detailsCart.getDetailsUpdatePrice().getProduct().getId())) {
				if (i == 0)
					break;
				// s.setStatus(true);
				s.setSaleDate(new Date());
				seriDao.update_Seri_TT_DAY(s);
				i--;

			}

		}

		model.addAttribute("ship", ship);
		model.addAttribute("total", total);

		model.addAttribute("cart", listTmp);
		session.removeAttribute("cart");
		// listTmp.clear();

		ModelAndView modelAndView = new ModelAndView("user/success");

		return modelAndView;
	}

}
