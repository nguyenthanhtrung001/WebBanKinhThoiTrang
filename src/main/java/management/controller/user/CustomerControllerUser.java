package management.controller.user;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import management.bean.Message;
import management.bean.Product_Price;
import management.dao.ICategoryDao;
import management.dao.ICustomerDao;
import management.dao.IProductDao;
import management.entity.Account;
import management.entity.Category;
import management.entity.Customer;
import management.entity.Paging;
import management.entity.Product;
import management.entity.Role;
import management.entity.Staff;

@Controller
@RequestMapping("/customer/")
public class CustomerControllerUser {

	@Autowired
	ICustomerDao customerDao;

	@RequestMapping(value = "update")
	public String update(ModelMap model, @RequestParam("fullName1") String tenKh,
			@RequestParam("phoneNumber") String sdt, @RequestParam("address") String diaChi,
			@RequestParam("pv") String tinh, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session1 = request.getSession();
		Customer cus = (Customer) session1.getAttribute("user");
		cus.setAddress(diaChi + ", " + tinh);
		cus.setPhoneNumber(sdt);
		cus.setName(tenKh);

		try {
			customerDao.updateCustomer(cus);
			redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công");
			System.out.println("Thành công cập nhật");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thất bại cập nhật thông tin khách hàng");
		}

			return "redirect:/user/cart/payingN";
	}

}