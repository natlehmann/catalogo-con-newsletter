package ar.com.almaDeJazmin.website.controller.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.com.almaDeJazmin.website.dao.ContactDao;
import ar.com.almaDeJazmin.website.domain.Contact;

public class ContactValidator implements Validator {
	
	protected ContactDao contactDao;

	public ContactValidator(ContactDao contactDao) {
		super();
		this.contactDao = contactDao;
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Contact.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		
		Contact contact = (Contact)target;
		if (!errors.hasFieldErrors("email") && contact.getEmail() != null) {
			
			if (!contact.getEmail().contains("@")) {
				errors.rejectValue("email", "invalid.email.address");
			}
		}
		
		if (contact.getComment() != null) {
			if (contact.getComment().length() > 512) {
				errors.rejectValue("comment", "exceeds.max.characters", new Object[]{512}, "too long.");
			}
		}
		
		if (contact.getPhoneNumber() != null) {
			if (contact.getPhoneNumber().length() > 20) {
				errors.rejectValue("phoneNumber", "exceeds.max.characters", new Object[]{20}, "too long.");
			}
		}
		
	}

}
