package management.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import management.dao.IProductAdminDao;
import management.dao.IProfileAdminDao;
import management.dao.IPromotionAdminDao;
import management.entity.DetailsPromotion;
import management.entity.DetailsPromotionPk;
import management.entity.Product;
import management.entity.Promotion;
import management.entity.Staff;

@Controller
@RequestMapping("/admin/")

public class PromotionController {

	@Autowired
	private IPromotionAdminDao promotionAdminDao;

	@Autowired
	private IProductAdminDao productAdminDao;

	@Autowired
	private IProfileAdminDao profileAdminDao;

	@RequestMapping(value = "promotion/Add")
	public String addKM(HttpServletRequest request, @ModelAttribute("km") Promotion km, BindingResult result,
			ModelMap model) throws ParseException {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//
//		if (userObj == null) {
//			return "login";
//		}
		String makm = request.getParameter("makm");
		String tenkm = request.getParameter("ten");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ngaybdkmString = request.getParameter("ngaybd");
		Date ngaybdkm = format.parse(ngaybdkmString);
		String ngayktkmString = request.getParameter("ngaykt");
		Date ngayktkm = format.parse(ngayktkmString);
		String muckmString = request.getParameter("muckm");
		double muckm = Double.parseDouble(muckmString);

//		Staff nv = (Staff) userObj;

//		Staff staff = profileAdminDao.getStaffByEmail(nv.getAccount().getEmail());
		try {

			Promotion checkPromotion = promotionAdminDao.getKM(makm);
			if (checkPromotion != null) {
				result.rejectValue("id", "promotion", "Mã khuyến mãi đã tồn tại");
			}

			Date currentDate = new Date();
			Date startDate = ngaybdkm;
			Date endDate = ngayktkm;

			if (startDate == null) {
				result.rejectValue("startDate", "promotion", "Ngày bắt đầu không được để trống");
			} else if (startDate.compareTo(currentDate) < 0) {
				result.rejectValue("startDate", "promotion", "Ngày bắt đầu không được nhỏ hơn ngày hiện tại");
			}

			if (endDate == null) {
				result.rejectValue("endDate", "promotion", "Ngày kết thúc không được để trống");
			} else if (startDate.compareTo(endDate) > 0) {
				result.rejectValue("endDate", "promotion", "Ngày kết thúc không được nhỏ hơn ngày bắt đầu");
			} else if (endDate.compareTo(currentDate) < 0) {
				result.rejectValue("endDate", "promotion", "Ngày kết thúc không được nhỏ hơn ngày hiện tại");
			}

			if (result.hasErrors()) {
				List<FieldError> errors = result.getFieldErrors();
				List<Promotion> promotionlist = promotionAdminDao.getAllKM();
				model.addAttribute("promotionlist", promotionlist);
				model.addAttribute("errorMessage", errors);
				return "admin/promotion";
			}

			Promotion promotion = new Promotion();
			promotion.setId(makm);
			promotion.setName(tenkm);
			promotion.setStartDate(ngaybdkm);
			promotion.setEndDate(ngayktkm);
			promotion.setPromotionLitmit(muckm);
			promotion.setStatus(true);
//			promotion.setStaff(staff);

			Integer temp = promotionAdminDao.insertPromotion(promotion);
			if (temp != 0) {
				model.addAttribute("successMessage", "Thêm thành công!");
			} else {
				model.addAttribute("errorMessage", "Thêm thất bại!");
			}

			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			model.addAttribute("promotionlist", promotionlist);
		} catch (Exception e) {
			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("errorMessage", "Lỗi: Thêm thất bại!");
			return "admin/promotion";
		}

		return "admin/promotion";

	}

	@RequestMapping(value = "promotion/Edit")
	public String editKM(HttpServletRequest request, @ModelAttribute("km") Promotion km, BindingResult result,
			ModelMap model) throws ParseException {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//
//		if (userObj == null) {
//			return "login";
//		}
		String makm = request.getParameter("makm");
		String tenkm = request.getParameter("ten");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ngaybdkmString = request.getParameter("ngaybd");
		Date ngaybdkm = format.parse(ngaybdkmString);
		String ngayktkmString = request.getParameter("ngaykt");
		Date ngayktkm = format.parse(ngayktkmString);
		String muckmString = request.getParameter("muckm");
		double muckm = Double.parseDouble(muckmString);

//		Staff nv = (Staff) userObj;

//		Staff staff = profileAdminDao.getStaffByEmail(nv.getAccount().getEmail());
		try {

			Date currentDate = new Date();
			Date startDate = ngaybdkm;
			Date endDate = ngayktkm;

			if (startDate == null) {
				result.rejectValue("startDate", "promotion", "Ngày bắt đầu không được để trống");
			}

			if (endDate == null) {
				result.rejectValue("endDate", "promotion", "Ngày kết thúc không được để trống");
			} else if (startDate.compareTo(endDate) > 0) {
				result.rejectValue("endDate", "promotion", "Ngày kết thúc không được nhỏ hơn ngày bắt đầu");
			} else if (endDate.compareTo(currentDate) < 0) {
				result.rejectValue("endDate", "promotion", "Ngày kết thúc không được nhỏ hơn ngày hiện tại");
			}

			if (result.hasErrors()) {
				List<FieldError> errors = result.getFieldErrors();
				List<Promotion> promotionlist = promotionAdminDao.getAllKM();
				model.addAttribute("promotionlist", promotionlist);
				model.addAttribute("errorMessage", errors);
				return "admin/promotion";
			}

			Promotion promotion = promotionAdminDao.getKM(makm);
			promotion.setName(tenkm);
			promotion.setStartDate(ngaybdkm);
			promotion.setEndDate(ngayktkm);
			promotion.setPromotionLitmit(muckm);
			promotion.setStatus(true);
//			promotion.setStaff(staff);

			Integer temp = promotionAdminDao.updatePromotion(promotion);
			if (temp != 0) {
				model.addAttribute("successMessage", "Cập nhật thành công!");
			} else {
				model.addAttribute("errorMessage", "Cập nhật thất bại!");
			}

			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			model.addAttribute("promotionlist", promotionlist);
		} catch (Exception e) {
			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("errorMessage", "Lỗi: Cập nhật thất bại!");
			return "admin/promotion";
		}

		return "admin/promotion";

	}

	@RequestMapping("promotion/Delete")
	public String deleteKM(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//
//		if (userObj == null) {
//			return "login";
//		}
		String makm = request.getParameter("makm");
		Promotion km = promotionAdminDao.getKM(makm);
		km.setStatus(false);
		Integer temp = promotionAdminDao.updatePromotion(km);
		if (temp != 0) {
			model.addAttribute("successMessage", "Xóa thành công!");
		} else {
			model.addAttribute("errorMessage", "Xóa thất bại!");
		}
		List<Promotion> promotionlist = promotionAdminDao.getAllKM();
		model.addAttribute("promotionlist", promotionlist);
		return "admin/promotion";
	}

	@RequestMapping(value = "promotionForProduct/AddPromotionForProduct")
	public String addKMforProduct(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//
//		if (userObj == null) {
//			return "login";
//		}
		String masp = request.getParameter("masp");
		String makm = request.getParameter("makm");
		try {
			DetailsPromotion dt = promotionAdminDao.getDetailsPromotion(masp, makm);
			if (dt != null) {
				model.addAttribute("errorMessage", "Sản phẩm đã được có khuyến mãi!");
				List<Promotion> promotionlist = promotionAdminDao.getAllKM();
				List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
				List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
				for (Object[] objects : detailsPromotionlist) {
					DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
					listDetailsPromotion.add(detailsPromotion);
				}
				model.addAttribute("promotionlist", promotionlist);
				model.addAttribute("listDetailsPromotion", listDetailsPromotion);
				return "admin/promotionForProduct";
			}
			DetailsPromotion dp = new DetailsPromotion();
			DetailsPromotionPk dk = new DetailsPromotionPk();

			dk.setProduct(masp);
			dk.setPromotion(makm);
			dp.setId(dk);
			Product pd = productAdminDao.getSP(masp);
			Promotion pm = promotionAdminDao.getKM(makm);
			dp.setProduct(pd);
			dp.setPromotion(pm);
			dp.setStatus(false);

			Integer temp = promotionAdminDao.insertDetailsPromotion(dp);

			if (temp != 0) {
				model.addAttribute("successMessage", "Thêm thành công!");
			} else {
				model.addAttribute("errorMessage", "Thêm thất bại!1");
			}

			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
			List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
			for (Object[] objects : detailsPromotionlist) {
				DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
				listDetailsPromotion.add(detailsPromotion);
			}
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("listDetailsPromotion", listDetailsPromotion);
		} catch (Exception e) {
			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
			List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
			for (Object[] objects : detailsPromotionlist) {
				DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
				listDetailsPromotion.add(detailsPromotion);
			}
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("listDetailsPromotion", listDetailsPromotion);
			model.addAttribute("errorMessage", "Lỗi: Thêm thất bại!");
			return "admin/promotionForProduct";
		}

		return "admin/promotionForProduct";

	}

	@RequestMapping(value = "promotionForProduct/UpdateStatusTruePromotionForProduct")
	public String updateStatusTrueKMforProduct(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//
//		if (userObj == null) {
//			return "login";
//		}
		String masp = request.getParameter("masp");
		String makm = request.getParameter("makm");
		try {
			DetailsPromotion dp = promotionAdminDao.getDetailsPromotion(masp, makm);
			dp.setStatus(true);

			Integer temp = promotionAdminDao.updateDetailsPromotion(dp);

			if (temp != 0) {
				model.addAttribute("successMessage", "Update thành công!");
			} else {
				model.addAttribute("errorMessage", "Update thất bại!1");
			}

			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
			List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
			for (Object[] objects : detailsPromotionlist) {
				DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
				listDetailsPromotion.add(detailsPromotion);
			}
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("listDetailsPromotion", listDetailsPromotion);
		} catch (Exception e) {
			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
			List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
			for (Object[] objects : detailsPromotionlist) {
				DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
				listDetailsPromotion.add(detailsPromotion);
			}
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("listDetailsPromotion", listDetailsPromotion);
			model.addAttribute("errorMessage", "Update thất bại!");
			return "admin/promotionForProduct";
		}

		return "admin/promotionForProduct";

	}

	@RequestMapping(value = "promotionForProduct/UpdateStatusFalsePromotionForProduct")
	public String updateStatusFalseKMforProduct(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//
//		if (userObj == null) {
//			return "login";
//		}
		String masp = request.getParameter("masp");
		String makm = request.getParameter("makm");
		try {
			DetailsPromotion dp = promotionAdminDao.getDetailsPromotion(masp, makm);
			dp.setStatus(false);

			Integer temp = promotionAdminDao.updateDetailsPromotion(dp);

			if (temp != 0) {
				model.addAttribute("successMessage", "Update thành công!");
			} else {
				model.addAttribute("errorMessage", "Update thất bại!1");
			}

			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
			List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
			for (Object[] objects : detailsPromotionlist) {
				DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
				listDetailsPromotion.add(detailsPromotion);
			}
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("listDetailsPromotion", listDetailsPromotion);
		} catch (Exception e) {
			List<Promotion> promotionlist = promotionAdminDao.getAllKM();
			List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
			List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
			for (Object[] objects : detailsPromotionlist) {
				DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
				listDetailsPromotion.add(detailsPromotion);
			}
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("listDetailsPromotion", listDetailsPromotion);
			model.addAttribute("errorMessage", "Update thất bại!");
			return "admin/promotionForProduct";
		}

		return "admin/promotionForProduct";

	}

	@GetMapping("promotionForProduct")
	public ModelAndView promotionForProduct(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//
//		if (userObj == null) {
//			return new ModelAndView("login");
//		}
		List<Promotion> promotionlist = promotionAdminDao.getAllKM();
		List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
		List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
		for (Object[] objects : detailsPromotionlist) {
			DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
			listDetailsPromotion.add(detailsPromotion);
		}
		model.addAttribute("promotionlist", promotionlist);
		model.addAttribute("listDetailsPromotion", listDetailsPromotion);

		ModelAndView modelAndView1 = new ModelAndView("admin/promotionForProduct");
		modelAndView1.addObject("promotionlist", promotionlist);
		modelAndView1.addObject("listDetailsPromotion", listDetailsPromotion);
		return modelAndView1;
	}

	@GetMapping("promotion")
	public ModelAndView promotion(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//
//		if (userObj == null) {
//			return new ModelAndView("login");
//		}
		List<Promotion> promotionlist = promotionAdminDao.getAllKM();
		List<Product> productlist = productAdminDao.getAllSP();
		List<Object[]> detailsPromotionlist = promotionAdminDao.listDetailsPromotion();
		List<DetailsPromotion> listDetailsPromotion = new ArrayList<>();
		for (Object[] objects : detailsPromotionlist) {
			DetailsPromotion detailsPromotion = (DetailsPromotion) objects[0];
			listDetailsPromotion.add(detailsPromotion);
		}

		ModelAndView modelAndView = new ModelAndView("admin/promotion");
		modelAndView.addObject("promotionlist", promotionlist);
		modelAndView.addObject("productlist", productlist);
		modelAndView.addObject("listDetailsPromotion", listDetailsPromotion);
		return modelAndView;
	}
}
