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
@RequestMapping("/user/")
public class CartController {
	
	
		
		@Autowired
		private IDetailsCartDao detailsCartDao;
		
		@RequestMapping(value="cart",method = RequestMethod.GET)
		public ModelAndView getcart(ModelMap model) {
			List<DetailsCart>list=detailsCartDao.getDetailsCart();
			model.addAttribute("cart", list);
			ModelAndView modelAndView = new ModelAndView("user/cart");
			return modelAndView;
		}

		
		
		
		
		@RequestMapping(value = "cart/add/id={id}",method = RequestMethod.POST)
		public String add_product_cart(ModelMap model, @ModelAttribute("cart") DetailsCart cart, BindingResult errors,
				
				@PathVariable("id") String maSP, 
				@RequestParam("quantity") String soLuong,
				HttpSession ss, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ParseException, NoSuchAlgorithmException
		{
			Date ngayADDate=detailsCartDao.getLatestApplicableDateByProductId(maSP);
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		//	Date ngayADDate = formatter.parse(ngayAD);
			
			
			
			
			  DetailsCart carttmp = detailsCartDao.checkSP_cart(maSP);
			
			
			if (carttmp != null) {
				long idtmp=(long) detailsCartDao.getID(maSP);
				int q=detailsCartDao.getQuantitybyID(idtmp)+Integer.parseInt(soLuong);
				detailsCartDao.updateQuantityById(idtmp, q);
				
				
				return "redirect:/user/cart";
			}
			
			
			
			
			try {
				Customer customer=new Customer();
				customer.setId("KH01");
				cart.setCustomer(customer);
				DetailsUpdatePrice price=new DetailsUpdatePrice();
				DetailsUpdatePricePK pricePK=new DetailsUpdatePricePK();
				pricePK.setProductId(maSP);
				pricePK.setApplicableDate(ngayADDate);
				price.setId(pricePK);
				//String soLuong="1";
				cart.setQuantity(Integer.parseInt(soLuong));
				cart.setDetailsUpdatePrice(price);

				if(detailsCartDao.addProductfor_(cart)) System.out.println("Thanh cong");
				else System.out.println("That Bai");

				

				redirectAttributes.addFlashAttribute("message", new Message("success", "Thêm mới thành công"));
				

				return "redirect:/user/cart";

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Thất bại thêm tài khoản");
				redirectAttributes.addFlashAttribute("message", new Message("error", "Thêm mới thất bại"));
				System.out.println("Thất bại thêm nhân viên3");
				return "redirect:/user/cart";

			}
				
			
		}
		
		@RequestMapping(value = "cart/del_products", method = RequestMethod.POST)
	    public String del_products(@RequestParam(value = "productId", required = false) List<Long> productIds) {
	        // Process the selected products here
	        if (productIds != null) {
	            for (long productId : productIds) {
	               System.out.println("id:"+productId);
	               if(detailsCartDao.deleteProductfor_(productId)) System.out.println("xoa tt");
	               else System.out.println("x tb");
	            }
	        }
	        else {
		        System.out.println("No items selected");
		    }

	        // Redirect to the checkout page
	        return "redirect:/user/cart";
	    }
		@RequestMapping("cart/del_product/id={id}")
		public String delete_product_id(ModelMap model, @PathVariable("id") String id,RedirectAttributes redirectAttributes) {
			System.out.println("id:"+id);
			detailsCartDao.deleteProductfor_(Long.parseLong(id));
			return "redirect:/user/cart";
		}
		
		@RequestMapping(value="paying",method = RequestMethod.GET)
		public ModelAndView getproducts(ModelMap model) {
			
			
			
			
			ModelAndView modelAndView = new ModelAndView("user/paying");
			return modelAndView;
		}
		
		

	}


