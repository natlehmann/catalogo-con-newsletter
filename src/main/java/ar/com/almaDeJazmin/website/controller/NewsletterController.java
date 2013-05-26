package ar.com.almaDeJazmin.website.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.almaDeJazmin.website.controller.validation.NewsletterValidator;
import ar.com.almaDeJazmin.website.dao.NewsletterDao;
import ar.com.almaDeJazmin.website.domain.Newsletter;

@Controller
public class NewsletterController {
	
	private static Log log = LogFactory.getLog(NewsletterController.class);
	
	@Autowired
	private NewsletterDao newsletterDao;
	
	@Autowired
	private NewsletterValidator newsletterValidator;
	
	@RequestMapping("/admin/newsletterList.html")
	@ModelAttribute("newsletters")
	public List<Newsletter> getNewsletters() {
		return newsletterDao.getAll();
	}
	
	
	@RequestMapping("/admin/newsletterFormInit.html")
	public ModelAndView newsletterFormInit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Integer id = ServletRequestUtils.getIntParameter(request, "id");
		
		Newsletter newsletter = new Newsletter();
		
		if (id != null) {
			newsletter = newsletterDao.getById(id);
		}
		
		return new ModelAndView("admin/newsletterForm", "newsletter", newsletter);
	}
	
	@RequestMapping("/admin/copyNewsletter.html")
	public ModelAndView copyNewsletter(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Integer id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		
		Newsletter newsletter = newsletterDao.getById(id);
		Newsletter newInstance = newsletter.clone();
		
		return new ModelAndView("admin/newsletterForm", "newsletter", newInstance);
	}

	@RequestMapping(value="/admin/createNewsletter.html")
	public ModelAndView createNewsletter(Newsletter newsletter,
			BindingResult result) throws IOException, ServletRequestBindingException {
		
		newsletterValidator.validate(newsletter, result);
		
		if (!result.hasErrors()) {
			newsletterDao.create(newsletter);
			return new ModelAndView("redirect:newsletterList.html");
		}
		
		return new ModelAndView("/admin/newsletterForm").addAllObjects(
				result.getModel());
	}
	
	@RequestMapping(value="/admin/deleteNewsletter.html")
	public ModelAndView deleteNewsletter(Newsletter newsletter) {
		
		newsletter = newsletterDao.getById(newsletter.getId());
		newsletterDao.delete(newsletter);
		return new ModelAndView("redirect:newsletterList.html");
	}
	
	@RequestMapping(value="/admin/updateNewsletter.html")
	public ModelAndView updateNewsletter(Newsletter newsletter,
			BindingResult result) throws ServletRequestBindingException, IOException {
		
		newsletterValidator.validate(newsletter, result);
		
		if (!result.hasErrors()) {
			newsletterDao.update(newsletter);
			return new ModelAndView("redirect:newsletterList.html");
		}
		
		return new ModelAndView("/admin/newsletterForm").addAllObjects(
				result.getModel());
	}
	
	@RequestMapping("/admin/newsletterPreview.html")
	public ModelAndView newsletterPreview(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Integer id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		Newsletter newsletter = newsletterDao.getById(id);
		
		return new ModelAndView("/admin/newsletterPreview", "newsletter", newsletter);
	}
	
	@RequestMapping("/admin/sendNewsletter.html")
	public ModelAndView sendNewsletter(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Integer id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		Newsletter newsletter = newsletterDao.getById(id);
		
		if (request.getParameter("back") != null) {
			return new ModelAndView("redirect:newsletterList.html");
		} 
		
		if (request.getParameter("sendFinalCustomers") != null) {
			log.error("sendFinalCustomers");
		}
		
		if (request.getParameter("sendRetailers") != null) {
			log.error("sendRetailers");
		}
		
		if (request.getParameter("sendCorporateSales") != null) {
			log.error("sendCorporateSales");
		}
		
		if (request.getParameter("sendTest") != null) {
			log.error("sendTest");
		}
		
		return new ModelAndView("/admin/newsletterPreview", "newsletter", newsletter);
	}
}
