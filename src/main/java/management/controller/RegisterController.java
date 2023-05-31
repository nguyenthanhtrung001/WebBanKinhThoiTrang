package management.controller;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			Account a= (Account) session1.getAttribute("user");
			model.addAttribute("tk",a);
			session1.removeAttribute("user");
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
		
		boolean kt = true;
		if (khachHang.getName().trim().toString().equals("")) {
			errors.rejectValue("tenKH", "KhachHang", "Họ và tên không được để trống!");
			kt = false;
		}
		if (khachHang.getAddress().trim().toString().equals("")) {
			errors.rejectValue("diaChi", "KhachHang", "Địa chỉ không được để trống!");
			kt = false;
		}
		if (khachHang.getPhoneNumber().trim().toString().equals("")) {
			errors.rejectValue("soDT", "KhachHang", "Số điện thoại không được để trống!");
			kt = false;
		}
		
		if (khachHang.getAccount().getEmail().trim().toString().equals("")) {
			errors.rejectValue("dstaikhoan.email", "KhachHang", "Email không được để trống!");
			kt = false;
		}
		
		if (accountDao.getSingleAccount(khachHang.getAccount().getEmail().trim().toString())!=null) {
			errors.rejectValue("account.email", "KhachHang", "Email đã tồn tại!");
			kt = false;
		}
		if (khachHang.getAccount().getPassword().trim().toString().equals("")) {
			errors.rejectValue("dstaikhoan.password", "KhachHang", "Mật khẩu không được để trống!");
			kt = false;
		}
		
		if (khachHang.getAccount().getPassword().trim().toString().length() < 3) {
			errors.rejectValue("dstaikhoan.password", "KhachHang", "Mật khẩu không được quá ngắn");
			kt = false;
		}

		if(kt == false)
		{
			model.addAttribute("login", false);
			return "register";
		}
		
		
		
		taiKhoan.setEmail(khachHang.getAccount().getEmail());
		taiKhoan.setPassword(khachHang.getAccount().getPassword());
		//taiKhoan.setPassword(myHash);
		Role role=new Role();
		role.setId("KH");
		taiKhoan.setRole(role);
		if(customerDao.addCustomer(khachHang, taiKhoan)) {
			model.addAttribute("tk",taiKhoan);
			ss.setAttribute("user", khachHang);
			model.addAttribute("message","Thêm thành công!");
			model.addAttribute("login", false);
			return "login";
		}else 
		{
			model.addAttribute("message","Thất Bại");
			model.addAttribute("login",false);
			return "register";
		}
		
		
		
	}
}
