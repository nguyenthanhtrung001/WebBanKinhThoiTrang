package management.controller;


import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import management.bean.Validator_check;
import management.dao.IAccountDao;
import management.dao.ICustomerDao;
import management.entity.Account;
import management.entity.Customer;
import management.entity.Role;

@Transactional
@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	SessionFactory factory;
	@Autowired
	IAccountDao accountDao;
	@Autowired
	ICustomerDao customerDao;
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request,HttpSession ss) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
		{
			model.addAttribute("login","Guest");
		}
		else
		{
			Account a= (Account) session.getAttribute("tk");
			model.addAttribute("tk",a);
		}
		model.addAttribute("login", false);
		model.addAttribute("KhachHang",new Customer());
		model.addAttribute("TaiKhoan",new Account());
		return "register";
	}
	
	
	
	
	@RequestMapping(value = "insert",method=RequestMethod.POST)
	public String insert(ModelMap model,@Validated @ModelAttribute("KhachHang") Customer khachHang,HttpServletRequest request,BindingResult errors,HttpSession ss,@ModelAttribute("dstaikhoan") Account taiKhoan) throws NoSuchAlgorithmException
	{
		HttpSession session1 = request.getSession();
		if(session1.getAttribute("user")==null)
		{
			
		}
		else
		{
			Account a= (Account) session1.getAttribute("tk");
			model.addAttribute("tk",a);
			session1.removeAttribute("tk");
		}

		
		String ngay = request.getParameter("day");
		String thang = request.getParameter("month");
		String nam = request.getParameter("year");
		String sDate = thang + "/" + ngay +"/" + nam;
        Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		khachHang.setDateOfBirth(date);
		LocalDate dateOfBirth = khachHang.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    int minimumAge = 12;
	    boolean kt = true;
	    if (!Validator_check.isMinimumAge(dateOfBirth, minimumAge)) {
	    	System.out.println("tuôi bé");
	        errors.rejectValue("name", "KhachHang", "Bạn phải lớn hơn " + minimumAge + " tuổi để đăng ký!");
	        kt = false;
	    }
		
		
		if (khachHang.getName().trim().isEmpty()) {
	        errors.rejectValue("name", "KhachHang", "Họ và tên không được để trống!");
	        kt = false;
	    }
	    if (khachHang.getAddress().trim().isEmpty()) {
	        errors.rejectValue("account.password", "KhachHang", "Địa chỉ không được để trống!");
	        kt = false;
	    }
	    if (khachHang.getPhoneNumber().trim().isEmpty()) {
	        errors.rejectValue("phoneNumber", "KhachHang", "Số điện thoại không được để trống!");
	        kt = false;
	    }
	    if (!Validator_check.isValidPhoneNumber(khachHang.getPhoneNumber())) {
	        errors.rejectValue("phoneNumber", "KhachHang", "Số điện thoại không hợp lệ!");
	        kt = false;
	    }

		
		
		if (khachHang.getAccount().getEmail().trim().toString().equals("")) {
			errors.rejectValue("account.email", "KhachHang", "Email không được để trống!");
			kt = false;
		}
		
		if (accountDao.getSingleAccount(khachHang.getAccount().getEmail().trim().toString())!=null) {
			errors.rejectValue("account.email", "KhachHang", "Email đã tồn tại!");
			kt = false;
		}
		if (khachHang.getAccount().getPassword().trim().toString().equals("")) {
			errors.rejectValue("account.password", "KhachHang", "Mật khẩu không được để trống!");
			kt = false;
		}		
		else if (khachHang.getAccount().getPassword().trim().toString().length() < 3) {
			errors.rejectValue("account.password", "KhachHang", "Mật khẩu không được quá ngắn");
			kt = false;
		}

		if(kt == false)
		{
			model.addAttribute("login", false);
			return "register";
		}
		
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	     String encodedPassword = encoder.encode(khachHang.getAccount().getPassword());
	        
	        System.out.println("Mật khẩu gốc: " + khachHang.getAccount().getPassword());
	        System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
		
		
		taiKhoan.setEmail(khachHang.getAccount().getEmail());
		//taiKhoan.setPassword(khachHang.getAccount().getPassword());
		taiKhoan.setPassword(encodedPassword);
		Role role=new Role();
		role.setId("KH");
		taiKhoan.setRole(role);
		if (customerDao.addCustomer(khachHang, taiKhoan)) {
		    model.addAttribute("tk", taiKhoan);
		    ss.setAttribute("user", khachHang);
		    model.addAttribute("message", "Đăng Ký Thành Công");
		    model.addAttribute("login", false);
		    model.addAttribute("redirect", true); // Add this line to indicate a redirection is needed
		    return "login";
		} else {
		    model.addAttribute("message", "Thất Bại");
		    model.addAttribute("login", false);
		    return "register";
		}
		
		
		
	}
}
