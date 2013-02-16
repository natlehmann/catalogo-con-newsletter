package ar.com.almaDeJazmin.website.controller.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.com.almaDeJazmin.website.dao.ProductDao;
import ar.com.almaDeJazmin.website.domain.Product;

public class ProductValidator implements Validator {

	private ProductDao productDao;
	
	public ProductValidator(ProductDao productDao) {
		this.productDao = productDao;
	}

	private void validate(Product product, Errors errors) {

		if (!errors.hasFieldErrors("code") && product.getCode() != null) {
			
			Product existingProd = productDao.getByCode(product.getCode());
			if (existingProd != null
					&& (product.getId() == null 
							|| product.getId().intValue() != existingProd.getId().intValue())) {
				
				errors.rejectValue("code", "validation.exists", "already exists.");
			}
		}
		
		if (product.getDescription() != null) {
			if (product.getDescription().length() > 512) {
				errors.rejectValue("description", "exceeds.max.characters", new Object[]{512}, "too long.");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "field.required");
		
		this.validate((Product)object, errors);
		
	}

}
