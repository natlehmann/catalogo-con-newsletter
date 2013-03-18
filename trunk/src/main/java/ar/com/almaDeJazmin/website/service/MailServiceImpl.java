package ar.com.almaDeJazmin.website.service;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ar.com.almaDeJazmin.website.domain.EmailException;
import ar.com.almaDeJazmin.website.domain.JobCandidate;

@Service
public class MailServiceImpl implements MailService {
	
	private static Log log = LogFactory.getLog(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired @Qualifier(value="jobCandidateToAddress")
	private InternetAddress jobCandidateToAddress;
	
	@Autowired @Qualifier(value="fromAddress")
	private InternetAddress fromAddress;
	
	@Autowired
	private MimeMessage mimeMessage;
	
	public void sendJobCandidateMail(JobCandidate jobCandidate) throws EmailException {
		
		log.info("Configurando mensaje para envio.");
		StringBuffer message = new StringBuffer("Se ha recibido un CV del siguiente contacto:\n");
		message.append("Nombre: ").append(jobCandidate.getName()).append("\n");
		message.append("Email: ").append(jobCandidate.getEmail()).append("\n");

		
		try {
			mimeMessage.setHeader("Content-Type", "application/octet-stream");
			mimeMessage.setHeader("Content-Transfer-Encoding", "base64");
			
//			mimeMessage.setSubject("Envio de CV", "UTF-8");
//			mimeMessage.setSentDate(new Date());
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setSubject("Envio de CV");
			helper.setSentDate(new Date());
			helper.setTo(jobCandidateToAddress);
			helper.setFrom(fromAddress);
			helper.setText(new String(message.toString().getBytes("UTF-8"), "UTF-8"));
			
			InputStreamSource attach = new ByteArrayResource(jobCandidate.getCv().getContent());
			helper.addAttachment(jobCandidate.getCv().getFileName(), attach, jobCandidate.getCv().getFileType());


			log.info("Previo a enviar mail.");
			javaMailSender.send(mimeMessage);
			log.info("Envio exitoso.");
			
		} catch (Exception e) {
			log.error("error al enviar mail para " + jobCandidate, e);
			throw new EmailException(e);
		}

	}

}