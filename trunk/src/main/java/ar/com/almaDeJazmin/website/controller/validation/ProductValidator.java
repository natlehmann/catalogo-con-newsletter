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
		
		if (product.getName() != null) {
			if (product.getName().length() > 30) {
				errors.rejectValue("name", "exceeds.max.characters", new Object[]{30}, "too long.");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		this.validate((Product)object, errors);
		
	}

}
