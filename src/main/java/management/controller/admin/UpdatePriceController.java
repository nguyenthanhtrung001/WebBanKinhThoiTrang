package management.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import management.dao.IProductAdminDao;
import management.dao.IProfileAdminDao;
import management.dao.IUpdatePriceDao;
import management.entity.DetailsUpdatePrice;
import management.entity.DetailsUpdatePricePK;
import management.entity.Product;
import management.entity.Staff;

@Controller
@RequestMapping("/admin/")
public class UpdatePriceController {
	
	@Autowired
	private IProductAdminDao productAdminDao;
	
	@Autowired
	private IUpdatePriceDao updatePriceDao;
	
	@Autowired
	private IProfileAdminDao profileAdminDao;
	
	@RequestMapping(value = "updatePrice/Update")
	public String updatePrice(HttpServletRequest request, ModelMap model) throws ParseException {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//		
//		if (userObj == null) {
//	        return "login";
//	    }
		String masp = request.getParameter("masp");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ngayadStr = request.getParameter("ngayapdung");
		Date ngayad = format.parse(ngayadStr);
		Double giasp = Double.parseDouble(request.getParameter("gia"));
		
//		Staff nv = (Staff) userObj;
//
//		Staff staff = profileAdminDao.getStaffByEmail(nv.getAccount().getEmail());
		
		try {
			DetailsUpdatePricePK dk = new DetailsUpdatePricePK();
			dk.setApplicableDate(ngayad);
			dk.setProductId(masp);
			DetailsUpdatePrice dt = updatePriceDao.getDetailsUpdatePrice(masp, dk);
			dt.setId(dk);
			dt.setPrice(giasp);	
//			dt.setStaff(staff);
			
			Integer temp = updatePriceDao.updateDetailsUpdatePrice(dt);

			if (temp != 0) {
				model.addAttribute("successMessage", "Cập nhật thành công!");
			} else {
				model.addAttribute("errorMessage", "Cập nhật thất bại!");
			}
			List<Object[]> detailsUpdatePriceList = updatePriceDao.listDetailsUpdatePrice();
			List<DetailsUpdatePrice> listDetailsUpdatePrice = new ArrayList<>();
			for (Object[] objects : detailsUpdatePriceList) {
			    DetailsUpdatePrice detailsUpdatePrice = (DetailsUpdatePrice) objects[0];
			    listDetailsUpdatePrice.add(detailsUpdatePrice);
			}
			model.addAttribute("listDetailsUpdatePrice", listDetailsUpdatePrice);
		} catch (Exception e) {
			List<Object[]> detailsUpdatePriceList = updatePriceDao.listDetailsUpdatePrice();
			List<DetailsUpdatePrice> listDetailsUpdatePrice = new ArrayList<>();
			for (Object[] objects : detailsUpdatePriceList) {
			    DetailsUpdatePrice detailsUpdatePrice = (DetailsUpdatePrice) objects[0];
			    listDetailsUpdatePrice.add(detailsUpdatePrice);
			}
			model.addAttribute("listDetailsUpdatePrice", listDetailsUpdatePrice);
			model.addAttribute("errorMessage", "Cập nhật thất bại!");
			return "admin/updatePrice";
		}
		return "admin/updatePrice";
	}
	
	@GetMapping("updatePrice")
	public ModelAndView updateprice(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		Object userObj = session.getAttribute("user");
//		
//		if (userObj == null) {
//	        return new ModelAndView("login");
//	    }
		List<Product> productlist = productAdminDao.getAllSP();
		List<Object[]> detailsUpdatePriceList = updatePriceDao.listDetailsUpdatePrice();
		List<DetailsUpdatePrice> listDetailsUpdatePrice = new ArrayList<>();
		for (Object[] objects : detailsUpdatePriceList) {
		    DetailsUpdatePrice detailsUpdatePrice = (DetailsUpdatePrice) objects[0];
		    listDetailsUpdatePrice.add(detailsUpdatePrice);
		}
		
		ModelAndView modelAndView = new ModelAndView("admin/updatePrice");
		modelAndView.addObject("listDetailsUpdatePrice", listDetailsUpdatePrice);	
		modelAndView.addObject("productlist", productlist);
		modelAndView.addObject("productAdminDao", productAdminDao);

		return modelAndView;
	}
}
