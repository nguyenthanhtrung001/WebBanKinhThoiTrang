package management.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import management.dao.ICustomerDao;
import management.entity.Customer;

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