package management.controller.admin;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import management.dao.IBillDao;
import management.dao.ICustomerDao;
import management.dao.IDetailsCartDao;
import management.dao.IProductDao;
import management.dao.ISeriDao;
import management.dao.IStaffDao;
import management.entity.Product;


@Controller("abc")
@RequestMapping("/admin/")

public class HomeController {
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private IStaffDao staffDao;
	@Autowired
	private IBillDao billDao;
	@Autowired
	private ISeriDao seriDao;
	@Autowired
	private IProductDao productDao;
	@Autowired IDetailsCartDao detailsCartDao;
	
	@GetMapping("home")
	public ModelAndView Home(ModelMap model) {
		List<String>seriList=seriDao.getTopSeriList(5);
		List<Product>products=new ArrayList<>();
		for(String s:seriList) {
			products.add(productDao.getProductById(s));
			
		}
		
		double total=detailsCartDao.getTotalAmountOfBillItems();
		NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String formattedtotal = vndFormat.format(total);
		
		
		
		int countCus=0;
		if(customerDao.getListCustomer()!=null) {
			countCus=customerDao.getListCustomer().size();
		}
		int countStaff=0;
		if(staffDao.getListStaff(1)!=null) {
			countStaff=staffDao.getListStaff(1).size();
		}
		long countBill=billDao.getCountBill();
		
		model.addAttribute("countCustomer",countCus);
		model.addAttribute("countStaff",countStaff);
		model.addAttribute("countBill",countBill);
		model.addAttribute("listTop",products);
		model.addAttribute("total",formattedtotal);
		
		ModelAndView modelAndView = new ModelAndView("admin/Home");
		return modelAndView;
	}
	
	
	
}
