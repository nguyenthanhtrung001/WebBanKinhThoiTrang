package management.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import management.bean.BasePath;
import management.bean.Message;
import management.dao.IAccountDao;
import management.dao.IStaffDao;
import management.entity.Account;
import management.entity.Role;
import management.entity.Staff;
import org.springframework.core.io.InputStreamSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/admin/")

public class StaffController {

	@Autowired
	private IStaffDao staffDao;
	@Autowired
	private IAccountDao accountDao;
	@Autowired
	ServletContext context;
	
	
	@RequestMapping(value="staff",method = RequestMethod.GET)
	public ModelAndView staff(ModelMap model) {
		List<Staff>list=staffDao.getListStaff(1);
		System.out.println("hello"+list.size());
		model.addAttribute("listStaff", list);
		
		ModelAndView modelAndView = new ModelAndView("admin/staffs");
		return modelAndView;
	}
	@RequestMapping(value="staffdel",method = RequestMethod.GET)
	public ModelAndView staffdel(ModelMap model) {
		List<Staff>list=staffDao.getListStaff(2);
		System.out.println("hello"+list.size());
		model.addAttribute("listStaff", list);
		
		ModelAndView modelAndView = new ModelAndView("admin/staffsDel");
		return modelAndView;
	}
	
	@RequestMapping(value = "staff/add", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("taikhoan") Account tk, BindingResult errors,
			@RequestParam("tenNV") String tenNV, @RequestParam("cmnd") String cmnd,
			@RequestParam("soDT") String sdt, @RequestParam("diaChi") String diaChi,
			@RequestParam("gioiTinh") Boolean gioiTinh, @RequestParam("ngaySinh") String ngaySinh,
			@RequestParam("role") String chucVu,@RequestParam("anh") MultipartFile anh,
			
			@RequestParam("email") String email, HttpSession ss, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ParseException, NoSuchAlgorithmException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date ngaySinhDate = formatter.parse(ngaySinh);
		String password = "13579";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);

		
		
		
		
		
		Account tkhoan = accountDao.checkEmail(email);
		
		
		if (tkhoan != null) {
			errors.rejectValue("email", "taikhoan", "Tài Khoản Đã tồn Tại");
			redirectAttributes.addFlashAttribute("message",
					new Message("error","Thêm mới thất bại do trùng email"));
			return "redirect:/admin/staff";
		}
		
		
		
		try {
			Role r = new Role();
			r.setId(chucVu);
			tk.setRole(r);
			tk.setStatus(1);
			tk.setEmail(email);
			tk.setPassword(encodedPassword);

			model.addAttribute("taikhoan", new Account());
			Staff nv = new Staff();

			
			nv.setAccount(tk);
			nv.setName(tenNV);
			nv.setAddress(diaChi);

			nv.setGender(gioiTinh ? true : false);

			nv.setPhoneNumber(sdt);
			nv.setDateOfBirth(ngaySinhDate);
			nv.setcMND(cmnd);
		//	nv.setImage(anh);
			nv.setAccount(tk);

			Staff tmp=staffDao.addStaff(nv, tk);
			
			String relativePath = "/templates/image/staff/";
			String rootPath = context.getRealPath(relativePath);
			System.out.println("rootPath"+rootPath);
			String photoPath = rootPath + tmp.getId() + ".jpg";
			System.out.println(photoPath);

			if (!anh.isEmpty()) {
			    try {
			        anh.transferTo(new File(photoPath));

			        Path sourcePath = Paths.get(photoPath);
			        Path destinationPath = Paths.get(rootPath + tmp.getId() + ".jpg");
			        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

			        // Tiếp tục xử lý sau khi tải lên thành công
			    } catch (IOException e) {
			        // Xử lý lỗi nếu có
			        e.printStackTrace();
			    }
			}



			redirectAttributes.addFlashAttribute("message", new Message("success", "Thêm mới thành công"));
			System.out.println("Thanh cong");

			return "redirect:/admin/staff";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thất bại thêm tài khoản");
			redirectAttributes.addFlashAttribute("message", new Message("error", "Thêm mới thất bại"));
			System.out.println("Thất bại thêm nhân viên3");
			return "redirect:/admin/staff/add";

		}
			
		
	}
	
	@RequestMapping(value = "staff/update", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("taikhoan") Account tk, BindingResult errors,
			@RequestParam("tenNV") String tenNV, @RequestParam("cmnd") String cmnd,
			@RequestParam("soDT") String sdt, @RequestParam("diaChi") String diaChi,
			@RequestParam("gioiTinh") Boolean gioiTinh, @RequestParam("ngaySinh") String ngaySinh,
			@RequestParam("role") String chucVu,@RequestParam("anh1") MultipartFile anh,
			@RequestParam("trangThai") Integer trangThai,
			@RequestParam("email") String email,
			@RequestParam("id") int id,
			
			HttpSession ss, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ParseException, NoSuchAlgorithmException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date ngaySinhDate = formatter.parse(ngaySinh);
		
		
		
		try {
			Role r = new Role();
			r.setId(chucVu);
			tk.setRole(r);

			tk.setStatus(trangThai);
			tk.setEmail(email);
	
			Staff nv = new Staff();

			nv.setAccount(tk);
			nv.setName(tenNV);
			nv.setAddress(diaChi);

			nv.setGender(gioiTinh ? true : false);

			nv.setPhoneNumber(sdt);
			nv.setDateOfBirth(ngaySinhDate);
			nv.setcMND(cmnd);
		//	nv.setImage(anh);
			nv.setId(id);
			
			staffDao.updateStaff(nv, tk);
			

			String relativePath = "/templates/image/staff/";
			String rootPath = context.getRealPath(relativePath);
			System.out.println("rootPath" + rootPath);
			String photoPath = rootPath + nv.getId() + ".jpg";
			System.out.println(photoPath);

			if (!anh.isEmpty()) {
			    try {
			        // Xóa file nếu đã tồn tại
			        File existingFile = new File(photoPath);
			        if (existingFile.exists()) {
			        	System.out.println("xóa file củ");
			            existingFile.delete();
			        }
			        System.out.println(" cập nhạt anh mới");
			        
			        anh.transferTo(new File(photoPath));

			        Path sourcePath = Paths.get(photoPath);
			        Path destinationPath = Paths.get(rootPath + nv.getId() + ".jpg");
			        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

			        // Tiếp tục xử lý sau khi tải lên thành công
			    } catch (IOException e) {
			        // Xử lý lỗi nếu có
			        e.printStackTrace();
			    }
			}


			redirectAttributes.addFlashAttribute("message", new Message("success", "Thêm mới thành công"));
			System.out.println("Thanh cong cap nhat");

			return "redirect:/admin/staff";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thất bại thêm tài khoản");
			redirectAttributes.addFlashAttribute("message", new Message("error", "Thêm mới thất bại"));
			System.out.println("Thất bại thêm nhân viên3");
			return "redirect:/admin/staff/update";

		}
			
		
	}
	
	@RequestMapping(value = "staff/remove/{id}",  method=RequestMethod.POST)
	public String updateStatusE(ModelMap model,  @ModelAttribute("taikhoan") Account tk,@PathVariable("id") String email,RedirectAttributes redirectAttributes) {
		String em=email+".com";
		try {
			tk=accountDao.getSingleAccount(em);
			
			tk.setStatus(2);
			accountDao.updateAccount(tk);
			System.out.println("1");
			return "redirect:/admin/staff";
			
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		

//		List<NhanVien> listNV = employee();
//		
//		model.addAttribute("nv", listNV);
		
		return "/admin/staff";
	}
	
	@RequestMapping(value = "staff/restore/{id}",  method=RequestMethod.POST)
	public String updateStatusE1(ModelMap model,  @ModelAttribute("taikhoan") Account tk,@PathVariable("id") String email,RedirectAttributes redirectAttributes) {
		String em=email+".com";
		try {
			tk=accountDao.getSingleAccount(em);
			
			tk.setStatus(1);
			accountDao.updateAccount(tk);
			System.out.println("1");
			return "redirect:/admin/staffdel";
			
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		

//		List<NhanVien> listNV = employee();
//		
//		model.addAttribute("nv", listNV);
		
		return "/admin/staffdel";
	}
	
}
	
	

	
