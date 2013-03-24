package ar.com.almaDeJazmin.website.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import ar.com.almaDeJazmin.website.domain.EmailException;
import ar.com.almaDeJazmin.website.domain.JobCandidate;

public interface MailService {
	
	void sendJobCandidateMail(JobCandidate jobCandidate) throws EmailException;

	void sendFinalCustomerMail(StringBuffer buffer) 
	throws EmailException, MessagingException, UnsupportedEncodingException;

	void sendRetailerMail(StringBuffer buffer)
	throws EmailException, MessagingException, UnsupportedEncodingException;

	void sendCorporateSalesContactMail(StringBuffer buffer)
	throws EmailException, MessagingException, UnsupportedEncodingException;

}
