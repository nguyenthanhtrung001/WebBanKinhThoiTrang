package management.controller.user;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import management.bean.Message;
import management.dao.IDetailsCartDao;
import management.dao.IProductDao;
import management.entity.Account;
import management.entity.Customer;
import management.entity.DetailsCart;
import management.entity.DetailsUpdatePrice;
import management.entity.DetailsUpdatePricePK;
import management.entity.Product;
import management.entity.Role;
import management.entity.Staff;


@Controller
@RequestMapping("/user/product/")
public class ProductsControler {
	
	
		@Autowired
		private IProductDao productDao;
		

		
		
		@RequestMapping(value="home",method = RequestMethod.GET)
		public ModelAndView getproducts(ModelMap model) {
			List<Product>list=productDao.getListProducts(true);
			
			model.addAttribute("products", list);
			
			
			ModelAndView modelAndView = new ModelAndView("user/product");
			return modelAndView;
		}
		@RequestMapping("detail/ma={id}")
		public String detail(ModelMap model, @PathVariable("id") String id) {
			Product p = productDao.getProductById(id);
			model.addAttribute("product", p);
			
			return "user/DetailsProduct";
		}
	
		
		

	}


