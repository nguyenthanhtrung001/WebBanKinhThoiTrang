package management.controller.user;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import management.bean.BasePath;
import management.dao.ICustomerDao;
import management.entity.Account;
import management.entity.Customer;

@Controller
@RequestMapping("/user/")
public class ProfileController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ICustomerDao customerDao;

	@GetMapping("profile")
	public ModelAndView showProfile(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("user/Profile");

		HttpSession session=request.getSession();
		Customer kh = (Customer)session.getAttribute("user");
		
		if(kh == null) {
			return new ModelAndView("redirect:/login");
		}
		Customer customer = customerDao.getCustomerByEmail(kh.getAccount().getEmail());

		System.out.println(customer);
		modelAndView.addObject("customer", customer);
		return modelAndView;
	}

	@PostMapping("profile")
	public ModelAndView editProfile(@RequestParam("hoTen") String hoTen,
			@RequestParam("gioiTinh") String gioiTinh, @RequestParam("ngaySinh") String ngaySinh,
			@RequestParam("diaChi") String diaChi, @RequestParam("sdt") String sdt, @RequestParam("email") String email,
			@RequestParam("file") MultipartFile file, @RequestParam("anhGoc") String anhGoc,
			@RequestParam("id") int id) {

		ModelAndView modelAndView = new ModelAndView("user/Profile");
		Session session = sessionFactory.openSession();
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date ngaySinhDate = formatter.parse(ngaySinh);
			
			if (!file.isEmpty()) {
				BasePath bPath = new BasePath();
				String path = bPath.getPathImgCustomer() + id + ".jpg";
				System.out.println("Upload ảnh: " + file.getOriginalFilename() + " thành công");
				file.transferTo(new File(path));
				anhGoc = id + ".jpg";
				Thread.sleep(7000);
				System.out.println(path);
			}

			Customer customer = new Customer();
			
			System.out.println("hoTen: "+hoTen);
			
			
			customer.setId(id);
			customer.setName(hoTen);
//			customer.setGender(gioiTinh);
			customer.setDateOfBirth(ngaySinhDate);
			customer.setAddress(diaChi);
			customer.setPhoneNumber(sdt);
			customer.setAccount((Account) session.get(Account.class, email));
			
			customerDao.updateCustomer(customer);

			System.out.println(customer);
			System.out.println("Cập nhật khách hàng thành công !!!");

			modelAndView.addObject("customer", customer);
			
			return modelAndView;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thất bại thêm tài khoản");
			return modelAndView;

		}
		finally {
			session.close();
		}

	}
	
	@GetMapping("logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		ModelAndView modelAndView = new ModelAndView("redirect:/login");
		return modelAndView;
	}
}
