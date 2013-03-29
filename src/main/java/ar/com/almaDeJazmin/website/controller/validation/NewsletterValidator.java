package ar.com.almaDeJazmin.website.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.com.almaDeJazmin.website.dao.NewsletterDao;
import ar.com.almaDeJazmin.website.domain.Newsletter;


@Component
public class NewsletterValidator implements Validator {
	
	@Autowired
	private NewsletterDao newsletterDao;
	

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Newsletter.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "field.required");
		
		if (!errors.hasFieldErrors("name")) {
			
			Newsletter newsletter = (Newsletter)target;
			Newsletter existent = newsletterDao.getByName(newsletter.getName());
			
			if (existent != null
					&& (newsletter.getId() == null  // es nuevo (no lo estoy modificando)
							|| newsletter.getId().intValue() != existent.getId().intValue())) { // es distinto
				
				errors.rejectValue("name", "validation.exists", "already exists.");
			}
		}
		
	}

	
}
