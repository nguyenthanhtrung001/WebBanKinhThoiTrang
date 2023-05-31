package management.controller.user;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
import management.bean.Product_Price;
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
			List<Product_Price> listP_P=new ArrayList<>();
			List<Product>list=productDao.getListProducts(true);
			for( Product p: list) {
				Product_Price pp=new Product_Price();
				
				pp.setProduct(p);
				pp.setPrice(productDao.get_Price_new(p.getId()));
				System.out.println("id:"+pp.getProduct().getId()+"-"+productDao.get_Price_new(p.getId()));
				listP_P.add(pp);
				
			}
			
			
			model.addAttribute("products", listP_P);
			
			
			 
			
			ModelAndView modelAndView = new ModelAndView("user/product");
			return modelAndView;
		}
		@RequestMapping(value="home/{categoryId}",method = RequestMethod.GET)
		public ModelAndView getproducts1(ModelMap model,@PathVariable("categoryId") String categoryId) {
			List<Product_Price> listP_P=new ArrayList<>();
			
			List<Product>list=productDao.getListProducts(true,categoryId);
			for( Product p: list) {
				Product_Price pp=new Product_Price();
				
				pp.setProduct(p);
				pp.setPrice(productDao.get_Price_new(p.getId()));
				System.out.println("id:"+pp.getProduct().getId()+"-"+productDao.get_Price_new(p.getId()));
				listP_P.add(pp);
				
			}
			
			
			model.addAttribute("products", listP_P);
			
			
			 
			
			ModelAndView modelAndView = new ModelAndView("user/product");
			return modelAndView;
		}
		@RequestMapping("detail/ma={id}")
		public String detail(ModelMap model, @PathVariable("id") String id) {
			Product p = productDao.getProductById(id);
			Product_Price pp=new Product_Price();
			pp.setProduct(p);
			pp.setPrice(productDao.get_Price_new(id));
			model.addAttribute("product", pp);
			
			return "user/DetailsProduct";
		}
	
		
		

	}


