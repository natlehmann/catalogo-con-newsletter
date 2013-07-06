package ar.com.almaDeJazmin.website.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.com.almaDeJazmin.website.dao.CategoryDao;
import ar.com.almaDeJazmin.website.domain.Category;


@Component
public class CategoryValidator implements Validator {
	
	@Autowired
	private CategoryDao categoryDao;
	

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Category.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "fill.in.all.fields");
		
		if (!errors.hasFieldErrors("name")) {
			
			Category category = (Category)target;
			Category existent = categoryDao.getByName(category.getName());
			
			if (existent != null
					&& (category.getId() == null  // la catgoria es nueva (no la estoy modificando)
							|| category.getId().intValue() != existent.getId().intValue())) { // la categoria es distinta
				
				errors.rejectValue("name", "category.validation.exists", "already exists.");
			}
		}
		
		if (!errors.hasFieldErrors("name")) {
			
			long count = categoryDao.getCategoryCount();
			if (count >= 14) {
				errors.rejectValue("name", "category.exceeds.maximum", "too many");
			}
		}
		
	}

	
}
