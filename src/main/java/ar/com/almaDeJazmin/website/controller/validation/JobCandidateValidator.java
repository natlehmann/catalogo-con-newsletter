package ar.com.almaDeJazmin.website.controller.validation;

import org.springframework.validation.Errors;

import ar.com.almaDeJazmin.website.dao.ContactDao;
import ar.com.almaDeJazmin.website.domain.Contact;
import ar.com.almaDeJazmin.website.domain.JobCandidate;

public class JobCandidateValidator extends ContactValidator {

	public JobCandidateValidator(ContactDao contactDao) {
		super(contactDao);
	}
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return JobCandidate.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		super.validateRequiredFields(target, errors, new String[]{"name", "email", "phoneNumber"});
		Contact contact = (Contact)target;
		
		if (!errors.hasFieldErrors("email")) {
				
			Contact existent = contactDao.getJobCandidateByEmail(contact.getEmail());
			
			if (existent != null
					&& (contact.getId() == null  // es nuevo (no la estoy modificando)
							|| contact.getId().intValue() != existent.getId().intValue())) { // es distinta
				
				errors.rejectValue("email", "email.validation.exists", "already exists.");
			}
		}
	}

}
