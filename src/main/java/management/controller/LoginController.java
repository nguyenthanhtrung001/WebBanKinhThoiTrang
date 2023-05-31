package management.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import management.dao.IAccountDao;
import management.dao.IStaffDao;
import management.dao.impl.AccountDaoImpl;
import management.entity.Account;
import management.entity.Customer;
import management.entity.Staff;



@Transactional
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	SessionFactory factory;
	@Autowired
	IAccountDao accountDao;
	@Autowired
	IStaffDao staffDao;
	
	public static Account taikhoan=new Account();
	public static Staff nv=new Staff();
	public static Customer kh=new Customer();
	public static String matKhau;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request,HttpSession ss) {
		
		//HttpSession session = request.getSession();
		if(LoginController.taikhoan.getEmail() == null)
		{
			//model.addAttribute("login","Login");
		}
		else
		{
			LoginController.taikhoan = new Account();
		}
		model.addAttribute("login", false);
		return "login";
	}
	
	public Staff layNV(String email)
	{
		email.trim();
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff where account = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		Staff p =(Staff)query.list().get(0);
		return p;
	}
	
	
	
	
	
	@RequestMapping(value = "/log",method=RequestMethod.POST)
	public String insert(ModelMap model,HttpServletRequest request,HttpSession ss) 
			throws NoSuchAlgorithmException
	{
		HttpSession session1 = request.getSession();
		session1.removeAttribute("user");
		String username = request.getParameter("email"); 
		String password = request.getParameter("password");
		username.trim();
		password.trim();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		//String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
		
		
		boolean kt=true;
		//Kiểm tra Email đăng nhập
		if(username.equals("")==true)
		{
			model.addAttribute("errorTK", "Email không được để trống!");
			kt=false;
		}
		
		//Kiểm tra mật khẩu đăng nhập
		if(password.equals("")==true)
		{
			model.addAttribute("errorMK", "Mật khẩu không được để trống!");
			kt=false;
		}

		if(kt==false)
		{
			return "login";
		}
		
		
		Account account=accountDao.getSingleAccount(username, password);
		
		if(account==null)
		{
			model.addAttribute("messageA", "Tài khoản hoặc mật khẩu không đúng!");
			model.addAttribute("login","Login");
			return "login";
		}
		
		else
			{
					//Kiểm tra mã quyền có phải NHÂN VIÊN không!
					if(account.getRole().getId().trim().equals("NV"))
					{
						taikhoan = account;
						nv = taikhoan.getStaff();
						matKhau = password;
						
						if(nv.getAccount().getStatus()==2) {
							model.addAttribute("messageA","Tài khoản đã quá hạn sử dụng!");
							return "login";
							}
						session1.setAttribute("tk",taikhoan);
						session1.setAttribute("user", kh);
						session1.setAttribute("mk", matKhau);
					
					return "redirect:/admin/order";
					}
					
					
					
					//Kiểm tra mã quyên có phải QUẢN LÝ không!
					else if(account.getRole().getId().trim().equals("QL"))
					{
						taikhoan=account;
						nv=taikhoan.getStaff();
						matKhau = password;
						session1.setAttribute("tk",taikhoan);
						session1.setAttribute("user", kh);
						session1.setAttribute("mk", matKhau);
						//model.addAttribute("login", true);
						return "redirect:/admin/home";
					}
					
					//Kiểm tra mã quyên có phải KHÁCH HÀNG không!
					else if(account.getRole().getId().trim().equals("KH"))
					{
						taikhoan = account;
						kh = taikhoan.getCustomer();
						
						matKhau = password;
						
						
						session1.setAttribute("tk",taikhoan);
						session1.setAttribute("user", kh);
						session1.setAttribute("mk", matKhau);
						session1.setAttribute("login", true);
						
						
						
					
						return "redirect:/user/cart";
					}
					else
					{
						model.addAttribute("messageA", "Vui lòng đăng nhập lại!");
						model.addAttribute("login","Login");
						return "login";
					}
					
					
				}
//		model.addAttribute("messageA", "Email không tồn tại!");
//		model.addAttribute("login","Login");
//		return "login";
		
	}
	
	// register
	
	
	
	
}