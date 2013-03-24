package ar.com.almaDeJazmin.website.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.almaDeJazmin.website.dao.CategoryDao;
import ar.com.almaDeJazmin.website.domain.Category;
import ar.com.almaDeJazmin.website.domain.CorporateSalesContact;
import ar.com.almaDeJazmin.website.domain.FinalCustomer;
import ar.com.almaDeJazmin.website.domain.JobCandidate;
import ar.com.almaDeJazmin.website.domain.Retailer;


@Controller
public class RedirectController {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@RequestMapping("/index.html")
	public ModelAndView index() {
		return new ModelAndView("/index");
	}
	
	@RequestMapping("/home.html")
	public ModelAndView home() {
		return new ModelAndView("/home");
	}
	
	@RequestMapping("/nosotros.html")
	public ModelAndView nosotros() {
		
		JobCandidate jobCandidate = new JobCandidate();
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("jobCandidate", jobCandidate);
		params.put("command", jobCandidate);
		return new ModelAndView("/nosotros", params);
	}
	
	@RequestMapping("/productos.html")
	public ModelAndView productos() {
		List<Category> categories = categoryDao.getAll();
		return new ModelAndView("/productos", "categories", categories);
	}
	
	@RequestMapping("/locales.html")
	public ModelAndView locales() {
		return new ModelAndView("/locales");
	}
	
	@RequestMapping("/contacto.html")
	public ModelAndView contacto() {
		return new ModelAndView("/contacto");
	}
	
	@RequestMapping("/prensa.html")
	public ModelAndView prensa() {
		return new ModelAndView("/prensa");
	}
	
	@RequestMapping(value="/errors/404.html")
    public ModelAndView handle404() {
        return new ModelAndView("/errorPage", "error-msg-code", "this.page.does.not.exist");
    }
	
	@RequestMapping(value="/errors/403.html")
    public ModelAndView handle403() {
        return new ModelAndView("/errorPage", "error-msg-code", "you.do.not.have.permissions.to.view.this.page");
    }

}
