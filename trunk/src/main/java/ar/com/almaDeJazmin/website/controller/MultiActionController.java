package ar.com.almaDeJazmin.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;

public class MultiActionController extends
		org.springframework.web.servlet.mvc.multiaction.MultiActionController {
	
	@SuppressWarnings("rawtypes")
	protected ServletRequestDataBinder validate(HttpServletRequest request, Class clazz, Object object) 
	throws Exception {
		
		ServletRequestDataBinder binder = createBinder(request, object);
		binder.bind(request);
		
		if (this.getValidators() != null) {
		    for (Validator val : this.getValidators()) {
		        if (val != null && val.supports(clazz)) {
		        	ValidationUtils.invokeValidator(val, object, binder.getBindingResult());
		        }
		    }
		}
		
		return binder;
		
	}

}
