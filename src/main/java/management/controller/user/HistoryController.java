package management.controller.user;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import management.bean.TienIch;
import management.dao.IBillDao;
import management.entity.Bill;
import management.entity.Customer;
import management.entity.DetailsCart;
import management.entity.DetailsExchangeVoucher;
import management.entity.DetailsExchangeVoucherPk;
import management.entity.DetailsReturnVoucher;
import management.entity.DetailsReturnVoucherPk;
import management.entity.ProductExchangeVoucher;
import management.entity.Promotion;
import management.entity.Seri;

@Controller
@RequestMapping("/user/")
public class HistoryController {

	@Autowired
	private IBillDao billDao;
	

	@GetMapping("history")
	public ModelAndView his(HttpServletRequest request) {
			
		HttpSession session=request.getSession();
		Customer customer = (Customer)session.getAttribute("user");
		
		ModelAndView modelAndView = new ModelAndView("user/History");
		List<Bill> list_Bills = billDao.getListBillOfCustomer(customer.getId());
		modelAndView.addObject("list_Bills", list_Bills);
		modelAndView.addObject("billDao", billDao);
		list_Bills.get(0).getPromotionlPrice();
		return modelAndView;
	}
	
	@GetMapping("created_return_voucher/{id}")
	public ModelAndView createReturnVoucher(@PathVariable("id") int id ) {
		Bill bill = billDao.getBill(id);
		System.out.println("txt: "+bill.getApplicableDate() );
		ModelAndView modelAndView = new ModelAndView("user/ReturnVoucher");
		Date currentDate = new Date();
		modelAndView.addObject("ngayHienTai", currentDate);
		modelAndView.addObject("bill", bill);
		modelAndView.addObject("billDao", billDao);
		billDao.getDetailsCartsOfBill(id);
		return modelAndView;
	}
	
	@PostMapping("return_voucher")
	public ModelAndView returnVoucher(@RequestParam("id") String id,
								      @RequestParam("liDo") String liDo,
								      @RequestParam("soLuong")int[] soLuong,
								      @RequestParam("productId") String[] productId,
								      @RequestParam("ngayHienTai")@DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayHienTai){
		
		ProductExchangeVoucher productExchangeVoucher = new ProductExchangeVoucher();
		
		String maPN = "PN" + (billDao.soLuongPhieuDoi()+1);
		
		productExchangeVoucher.setId(maPN);
		productExchangeVoucher.setExchangeDate(ngayHienTai);
		//productExchangeVoucher.setReason(liDo);
		productExchangeVoucher.setCustomer(billDao.getBill(id).getDetailsCarts().get(0).getCustomer());
	
		billDao.addProductExchangeVoucher(productExchangeVoucher);
		
		System.out.println("list: "+productId[0]+"  "+soLuong[0]);
		
		for(int i =0; i< soLuong.length; i++) {
			if(soLuong[i] > 0) {
				List<Seri> listSeri = billDao.get_n_SeriOfBillAndProduct(id, productId[i], soLuong[i]);
				System.out.println("list: "+productId[i]+"  "+soLuong[i]+ listSeri.get(i).getId());
				for(int j=0; j< soLuong[i]; j++) {
					DetailsExchangeVoucherPk dev_pk = new DetailsExchangeVoucherPk();
					dev_pk.setSeri(listSeri.get(j).getId());
					dev_pk.setExchangeVoucher(maPN);
					
					DetailsExchangeVoucher dev = new DetailsExchangeVoucher();
					dev.setSeri(listSeri.get(j));
					dev.setExchangeVoucher(productExchangeVoucher);
					dev.setId(dev_pk);
					billDao.addDetailsExchangeVoucher(dev);
				}
			}
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/user/history");
		
		return modelAndView;
	}

}
