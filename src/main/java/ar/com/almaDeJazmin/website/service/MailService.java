package ar.com.almaDeJazmin.website.service;

import ar.com.almaDeJazmin.website.domain.EmailException;
import ar.com.almaDeJazmin.website.domain.JobCandidate;

public interface MailService {
	
	void sendJobCandidateMail(JobCandidate jobCandidate) throws EmailException;

}
