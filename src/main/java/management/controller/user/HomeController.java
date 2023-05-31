package management.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import management.bean.Product_Price;
import management.dao.ICategoryDao;
import management.dao.IProductDao;
import management.entity.Category;
import management.entity.Paging;
import management.entity.Product;

@Controller
@RequestMapping("")
public class HomeController {

	@RequestMapping(value="home",method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
}
