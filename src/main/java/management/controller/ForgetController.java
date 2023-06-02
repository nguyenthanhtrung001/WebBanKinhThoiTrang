
package management.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.mail.MessagingException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import management.bean.Mailer;
import management.dao.IAccountDao;
import management.entity.Account;

@Transactional

@Controller

@RequestMapping("/forget")
public class ForgetController {

	@Autowired
	SessionFactory factory;

	@Autowired
	JavaMailSender mailer;

	@Autowired
	IAccountDao accountDao;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request, HttpSession ss) {

		HttpSession session = request.getSession();
		if (LoginController.taikhoan.getEmail() == null) {
			model.addAttribute("login", "Login");
		} else {
			session.removeAttribute("user");
			model.addAttribute("login", "Login");
			LoginController.taikhoan = new Account();
		}
		model.addAttribute("login", false);
		return "forget";
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String insert(ModelMap model, HttpServletRequest request, HttpSession ss,

			@ModelAttribute("email") String email)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, MessagingException {
		email.trim();
		if (email.equals("") == true) {
			model.addAttribute("errorTK", "Email không được để trống!");
			return "forger";
		}

//		String hql = "FROM TaiKhoan";
//		Query query = session.createQuery(hql);
//		List<Account> list = query.list();

//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update("01234567".getBytes());
//		byte[] digest = md.digest();
//		String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();

		Account account = accountDao.getSingleAccountNoPass(email);
		if (account != null) {
			String myHash = "12345";
			account.setPassword(myHash);
			accountDao.updateAccount(account);
//			Mailer mailer = new Mailer();
//			mailer.send("n20dccn151@student.ptithcm.edu.vn", "n20dccn160@student.ptithcm.edu.vn", "tmp", "thinhne");
			model.addAttribute("messageA", "Vào email của bạn để lấy mật khẩu mới!");
			model.addAttribute("login", false);

			return "forget";
		}

		model.addAttribute("login", false);
		model.addAttribute("messageA", "Email đăng kí không tồn tại!");
		return "forget";
	}
}
