package ar.com.almaDeJazmin.website.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ar.com.almaDeJazmin.website.controller.validation.ContactValidator;
import ar.com.almaDeJazmin.website.dao.ContactDao;
import ar.com.almaDeJazmin.website.domain.InvalidTextFileFormatException;
import ar.com.almaDeJazmin.website.domain.JobCandidate;
import ar.com.almaDeJazmin.website.domain.TextFile;
import ar.com.almaDeJazmin.website.domain.TextFileFormat;

@Controller
public class JobCandidateController extends MultiActionController {
	
	private ContactDao contactDao;
	
	@Autowired
	public JobCandidateController(ContactDao contactDao){
		this.contactDao = contactDao;
		this.setValidators(new Validator[]{new ContactValidator(contactDao)});
	}


	@RequestMapping(value="/receiveJobCandidate.html")
	public ModelAndView receiveJobCandidate(HttpServletRequest request,
			HttpServletResponse response) 
	throws Exception {
		
		JobCandidate jobCandidate = buildJobCandidate(new JobCandidate(), request);
		
		ServletRequestDataBinder binder = validate(request, jobCandidate.getClass(), jobCandidate);		
		
		if (!binder.getBindingResult().hasErrors()) {
		
			try {
				this.addCv(jobCandidate, request);
				
				//TODO: Mandarlo por mail
				
				contactDao.create(jobCandidate);
				return new ModelAndView("/nosotros").addObject("success", true);
				
				
			} catch (InvalidTextFileFormatException e) {
				binder.getBindingResult().rejectValue("cv", "invalid.text.file.format", "invalid file");
				return new ModelAndView("/nosotros").addAllObjects(
						binder.getBindingResult().getModel()).addObject("openForm", true);
			} 
			
			
		} else {
			
			return new ModelAndView("/nosotros").addAllObjects(
					binder.getBindingResult().getModel()).addObject("openForm", true);
		}
	}


	private JobCandidate buildJobCandidate(JobCandidate jobCandidate, HttpServletRequest request) 
	throws ServletRequestBindingException, IOException {
		
		String name = ServletRequestUtils.getRequiredStringParameter(request, "name");
		String email = ServletRequestUtils.getRequiredStringParameter(request, "email");

		jobCandidate.setName(name);
		jobCandidate.setEmail(email);
		
		return jobCandidate;
	}
	
	
	private void addCv(JobCandidate jobCandidate, HttpServletRequest request) 
	throws ServletRequestBindingException, IOException, InvalidTextFileFormatException {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		TextFile textFile = buildCv(multipartRequest, "cvFile");
		if (textFile != null) {
			
			jobCandidate.setCv(textFile);
		}
	}
	
	
	private TextFile buildCv(MultipartHttpServletRequest multipartRequest, 
			String paramName) throws IOException, InvalidTextFileFormatException {
		
		TextFile result = null;
		
		MultipartFile multipartFile = multipartRequest.getFile(paramName);
		
		if (multipartFile != null && multipartFile.getOriginalFilename() != null 
				&& !multipartFile.getOriginalFilename().trim().equals("")) {
			
			if (TextFileFormat.getFromString(multipartFile.getContentType()) == null) {
				throw new InvalidTextFileFormatException();
			}
			
			result = new TextFile();
			result.setFileName(multipartFile.getOriginalFilename());
			result.setFileType(multipartFile.getContentType());
			result.setContent(multipartFile.getBytes());
		}
		
		return result;
	}
}
