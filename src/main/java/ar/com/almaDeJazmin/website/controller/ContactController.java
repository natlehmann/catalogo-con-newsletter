package ar.com.almaDeJazmin.website.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.almaDeJazmin.website.controller.validation.ContactValidator;
import ar.com.almaDeJazmin.website.dao.ContactDao;
import ar.com.almaDeJazmin.website.domain.Category;
import ar.com.almaDeJazmin.website.domain.CorporateSalesContact;
import ar.com.almaDeJazmin.website.domain.FinalCustomer;
import ar.com.almaDeJazmin.website.domain.Retailer;

@Controller
public class ContactController {
	
	private ContactDao contactDao;
	
	private ContactValidator contactValidator;
	
	@Autowired
	public ContactController(ContactDao contactDao) {
		this.contactDao = contactDao;
		this.contactValidator = new ContactValidator(contactDao);
	}
	
	@RequestMapping("/tusComentarios.html")
	public ModelAndView tusComentarios() {
		
		FinalCustomer finalCustomer = new FinalCustomer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("finalCustomer", finalCustomer);
		params.put("command", finalCustomer);
		return new ModelAndView("/tusComentarios", params);
	}
	
	@RequestMapping("/franquiciasYmayoristas.html")
	public ModelAndView franquiciasYmayoristas() {
		
		Retailer retailer = new Retailer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("retailer", retailer);
		params.put("command", retailer);
		return new ModelAndView("/franquiciasYmayoristas", params);
	}
	
	@RequestMapping("/ventasCorporativas.html")
	public ModelAndView ventasCorporativas() {
		
		CorporateSalesContact corporateSalesContact = new CorporateSalesContact();
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("corporateSalesContact", corporateSalesContact);
		params.put("command", corporateSalesContact);
		return new ModelAndView("/ventasCorporativas", params);
	}
	
	@RequestMapping("/enviarComentarios.html")
	public ModelAndView sendFinalCustomerComments(FinalCustomer finalCustomer,
			BindingResult result) throws IOException, ServletRequestBindingException {
		
		contactValidator.validate(finalCustomer, result);
		
		if (!result.hasErrors()) {
			contactDao.create(finalCustomer);
			return new ModelAndView("/tusComentarios", 
					"successMsg", "Su email se ha enviado con exito");
		}
		
		return new ModelAndView("/tusComentarios").addAllObjects(
				result.getModel());
		
	}
	
	@RequestMapping("/enviarVentasCorporativas.html")
	public ModelAndView sendCorporateSalesContactComments(CorporateSalesContact corporateSalesContact,
			BindingResult result) throws IOException, ServletRequestBindingException {
		
		contactValidator.validate(corporateSalesContact, result);
		
		if (!result.hasErrors()) {
			contactDao.create(corporateSalesContact);
			return new ModelAndView("/ventasCorporativas", 
					"successMsg", "Su email se ha enviado con exito");
		}
		
		return new ModelAndView("/ventasCorporativas").addAllObjects(
				result.getModel());
		
	}
	
	@RequestMapping("/enviarFranquiciasYMayoristas.html")
	public ModelAndView sendRetailerComments(Retailer retailer,
			BindingResult result) throws IOException, ServletRequestBindingException {
		
		contactValidator.validate(retailer, result);
		
		if (!result.hasErrors()) {
			contactDao.create(retailer);
			return new ModelAndView("/franquiciasYmayoristas", 
					"successMsg", "Su email se ha enviado con exito");
		}
		
		return new ModelAndView("/franquiciasYmayoristas").addAllObjects(
				result.getModel());
		
	}

}
