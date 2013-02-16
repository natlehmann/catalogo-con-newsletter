package ar.com.almaDeJazmin.website.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.almaDeJazmin.website.dao.ContactDao;
import ar.com.almaDeJazmin.website.domain.Category;
import ar.com.almaDeJazmin.website.domain.Contact;

public class ContactValidator implements Validator {
	
	@Autowired
	private ContactDao contactDao;
	
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Contact.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		Contact contact = (Contact)target;
		if (!errors.hasFieldErrors("email") && contact.getEmail() != null) {
			
			if (!contact.getEmail().contains("@")) {
				errors.rejectValue("email", "invalid.email.address");
			}
		}
		
		if (!errors.hasFieldErrors("email")) {
			
			Contact existent = contactDao.getByEmail(contact.getEmail());
			
			if (existent != null
					&& (contact.getId() == null  // es nuevo (no la estoy modificando)
							|| contact.getId().intValue() != existent.getId().intValue())) { // es distinta
				
				errors.rejectValue("email", "validation.exists", "already exists.");
			}
		}
		
		if (contact.getComment() != null) {
			if (contact.getComment().length() > 512) {
				errors.rejectValue("comment", "exceeds.max.characters", new Object[]{512}, "too long.");
			}
		}
		
	}

}
