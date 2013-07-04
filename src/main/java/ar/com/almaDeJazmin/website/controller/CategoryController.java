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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.com.almaDeJazmin.website.controller.validation.CategoryValidator;
import ar.com.almaDeJazmin.website.dao.CategoryDao;
import ar.com.almaDeJazmin.website.domain.Category;


@Controller
public class CategoryController {
	
	private static Log log = LogFactory.getLog(CategoryController.class);
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CategoryValidator categoryValidator;
	
	
	@RequestMapping("/admin/categoryList.html")
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDao.getAll();
	}

	@RequestMapping("/admin/categoryDetails.html")
	@ModelAttribute("category")
	public Category getCategory(@RequestParam(value = "id", required = true) int id) {
		
		Category category = categoryDao.getById(id);
		log.debug("Retrieving " + category);
		return category;
	}

	
	@RequestMapping("/admin/categoryFormInit.html")
	public ModelAndView categoryFormInit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Integer id = ServletRequestUtils.getIntParameter(request, "id");
		
		Category category = new Category();
		
		if (id != null) {
			category = categoryDao.getById(id);
		}
		
		return new ModelAndView("admin/categoryForm", "category", category);
	}
		
	
	
	@RequestMapping(value="/admin/deleteCategory.html")
	public ModelAndView deleteCategory(Category category) {
		
		category = categoryDao.getById(category.getId());
		categoryDao.delete(category);
		return new ModelAndView("redirect:categoryList.html");
	}


	@RequestMapping(value="/admin/updateCategory.html")
	public ModelAndView updateCategory(Category category,
			BindingResult result) throws ServletRequestBindingException, IOException {
		
		categoryValidator.validate(category, result);
		
		if (!result.hasErrors()) {
			categoryDao.update(category);
			return new ModelAndView("redirect:categoryList.html");
		}
		
		return new ModelAndView("/admin/categoryForm").addAllObjects(
				result.getModel()).addObject("msgError", "Por favor complete todos los campos.");
	}

	@RequestMapping(value="/admin/createCategory.html")
	public ModelAndView createCategory(Category category,
			BindingResult result) throws IOException, ServletRequestBindingException {
		
		categoryValidator.validate(category, result);
		
		if (!result.hasErrors()) {
			categoryDao.create(category);
			return new ModelAndView("redirect:categoryList.html");
		}
		
		return new ModelAndView("/admin/categoryForm").addAllObjects(
				result.getModel());
	}



}
