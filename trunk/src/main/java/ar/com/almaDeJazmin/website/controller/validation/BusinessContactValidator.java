package ar.com.almaDeJazmin.website.controller.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import ar.com.almaDeJazmin.website.dao.ContactDao;

public class BusinessContactValidator extends ContactValidator {

	public BusinessContactValidator(ContactDao contactDao) {
		super(contactDao);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		super.validate(target, errors);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productCategories", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productAmount", "field.required");
	}

}
