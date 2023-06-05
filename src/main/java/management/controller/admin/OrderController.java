package management.controller.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import management.bean.Bill_NameCustomer;
import management.dao.IBillDao;
import management.dao.IDetailsCartDao;

import management.entity.Bill;






@Controller
@Transactional
@RequestMapping("/admin/")

public class OrderController {

	@Autowired
	private IBillDao billDao;
	@Autowired
	private IDetailsCartDao detailsCartDao;
	@Autowired
	SessionFactory sessionFactory;
	
	
	
	
	@GetMapping("order")
	public ModelAndView bills0(ModelMap model) {
		
		List<Bill>list=billDao.getListBillTT(0);
//		List<Bill_NameCustomer>list0= new ArrayList<Bill_NameCustomer>();
//		 for (Bill b: list) {
//			
//			 Bill_NameCustomer  bNameCustomer=new Bill_NameCustomer();
//			 bNameCustomer.setBill(b);
//			 bNameCustomer.setCustomer(detailsCartDao.get_customer_by_IDHD(b.getId()));
//			 list0.add(bNameCustomer);
//		 }
		
		model.addAttribute("listBill", list);
		ModelAndView modelAndView = new ModelAndView("admin/order");
		return modelAndView;
	}
	@GetMapping("order/xl")
	public ModelAndView bills1(ModelMap model) {
		
		List<Bill>list=billDao.getListBillTT(1);
//		List<Bill_NameCustomer>list1= new ArrayList<Bill_NameCustomer>();
//		 for (Bill b: list) {
//			 Bill_NameCustomer  bNameCustomer=new Bill_NameCustomer();
//			 bNameCustomer.setBill(b);
//			 bNameCustomer.setCustomer(detailsCartDao.get_customer_by_IDHD(b.getId()));
//			 list1.add(bNameCustomer);
//		 }
		
		model.addAttribute("listBill", list);
		
		ModelAndView modelAndView = new ModelAndView("admin/order");
		return modelAndView;
	}
	@GetMapping("order/h")
	public ModelAndView bills2(ModelMap model) {
		
		List<Bill>list=billDao.getListBillTT(2);
//		List<Bill_NameCustomer>list2= new ArrayList<Bill_NameCustomer>();
//		 for (Bill b: list) {
//			 Bill_NameCustomer  bNameCustomer=new Bill_NameCustomer();
//			 bNameCustomer.setBill(b);
//			 bNameCustomer.setCustomer(detailsCartDao.get_customer_by_IDHD(b.getId()));
//			 list2.add(bNameCustomer);
//		 }
		
		model.addAttribute("listBill", list);
		ModelAndView modelAndView = new ModelAndView("admin/order");
		return modelAndView;
	}
	
	@ModelAttribute
	public String getNameCus() {
		return null;
	}

	@RequestMapping(value = "order/update", method = RequestMethod.POST)
	public String updateBill(ModelMap model,
			@ModelAttribute("billUpdate") Bill bill,
			@RequestParam("maDonHang") String maDonHang,
			@RequestParam("trangThai") String status,HttpServletRequest request,RedirectAttributes redirectAttributes ) {
			
		String statustmp=status;
		
			try {
				
				billDao.updateStatus(Integer.parseInt(maDonHang), Integer.parseInt(status));
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("57");
			}
			
			if(statustmp.equals("0"))  return "redirect:/admin/order";
				

			if(status.equals("1")) {
				return "redirect:/admin/order/h";
			}
			else if (status.equals("2")) {
				return "redirect:/admin/order/xl";
			}
			else return "redirect:/admin/order";
		
		
		
		
	}


}
