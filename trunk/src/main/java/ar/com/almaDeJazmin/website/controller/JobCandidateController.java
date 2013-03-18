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

import ar.com.almaDeJazmin.website.controller.validation.JobCandidateValidator;
import ar.com.almaDeJazmin.website.dao.ContactDao;
import ar.com.almaDeJazmin.website.domain.ConfigConstants;
import ar.com.almaDeJazmin.website.domain.EmailException;
import ar.com.almaDeJazmin.website.domain.InvalidTextFileFormatException;
import ar.com.almaDeJazmin.website.domain.JobCandidate;
import ar.com.almaDeJazmin.website.domain.TextFile;
import ar.com.almaDeJazmin.website.domain.TextFileFormat;
import ar.com.almaDeJazmin.website.domain.ValidationException;
import ar.com.almaDeJazmin.website.service.MailService;

@Controller
public class JobCandidateController extends MultiActionController {
	
	private ContactDao contactDao;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	public JobCandidateController(ContactDao contactDao){
		this.contactDao = contactDao;
		this.setValidators(new Validator[]{new JobCandidateValidator(contactDao)});
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
				
				mailService.sendJobCandidateMail(jobCandidate);
				
				contactDao.create(jobCandidate);
				
				return new ModelAndView("/nosotros").addObject("success", true);
				
				
			} catch (InvalidTextFileFormatException e) {
				binder.getBindingResult().rejectValue("cv", "invalid.text.file.format", "invalid file");
				return new ModelAndView("/nosotros").addAllObjects(
						binder.getBindingResult().getModel()).addObject("openForm", true);
				
			} catch (ValidationException e) {
				binder.getBindingResult().rejectValue("cv", e.getMessage(), "invalid file size");
				return new ModelAndView("/nosotros").addAllObjects(
						binder.getBindingResult().getModel()).addObject("openForm", true);
				
			} catch (EmailException e) {
				return new ModelAndView("/nosotros").addAllObjects(
						binder.getBindingResult().getModel()).addObject("emailError", "email.error");
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
	throws ServletRequestBindingException, IOException, InvalidTextFileFormatException, ValidationException {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		TextFile textFile = buildCv(multipartRequest, "cvFile");
		if (textFile != null) {
			
			jobCandidate.setCv(textFile);
		}
	}
	
	
	private TextFile buildCv(MultipartHttpServletRequest multipartRequest, 
			String paramName) throws IOException, InvalidTextFileFormatException, ValidationException {
		
		TextFile result = null;
		
		MultipartFile multipartFile = multipartRequest.getFile(paramName);
		
		if (multipartFile != null && multipartFile.getOriginalFilename() != null 
				&& !multipartFile.getOriginalFilename().trim().equals("")) {
			
			if (TextFileFormat.getFromString(multipartFile.getContentType()) == null) {
				throw new InvalidTextFileFormatException();
			}
			
			if (multipartFile.getSize() > ConfigConstants.TEXT_FILE_MAX_SIZE) {
				throw new ValidationException("invalid.text.file.size");
			}
			
			result = new TextFile();
			result.setFileName(multipartFile.getOriginalFilename());
			result.setFileType(multipartFile.getContentType());
			result.setContent(multipartFile.getBytes());
			
		} else {
			throw new ValidationException("required");
		}
		
		return result;
	}
}
