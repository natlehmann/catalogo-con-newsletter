package ar.com.almaDeJazmin.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RedirectController {
	
	@RequestMapping("/index.html")
	public ModelAndView index() {
		return new ModelAndView("/index");
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
