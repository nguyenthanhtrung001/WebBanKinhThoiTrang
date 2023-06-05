// DashBoardController.java

package management.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;

import management.dao.IBillDao;

@Controller
@RequestMapping("/admin/")
public class DashBoardController {

    @Autowired
    private IBillDao billDao;

    @GetMapping("dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("admin/dashboard");
    }

    @PostMapping("statistics")
    public ModelAndView getStatistics(@RequestParam("year") int year) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(totalMonthForYear(i, year));
            System.out.println(i+":"+totalMonthForYear(i, year));
        }

        ModelAndView modelAndView = new ModelAndView("admin/statistics");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    private int totalMonthForYear(int month, int year) {
        return (int)billDao.getTotalPriceByMonthAndYear(month, year);
    }
}
