package ar.com.almaDeJazmin.website.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.almaDeJazmin.website.dao.ContactDao;
import ar.com.almaDeJazmin.website.domain.Contact;
import ar.com.almaDeJazmin.website.domain.CorporateSalesContact;
import ar.com.almaDeJazmin.website.domain.FinalCustomer;
import ar.com.almaDeJazmin.website.domain.Retailer;

public class MailSenderJob extends TimerTask {
	
	private static Log log = LogFactory.getLog(MailSenderJob.class);
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ContactDao contactDao; 

	@Override
	public void run() {
		log.info("Comienza proceso de envio de mails de contactos. ----------------------------------");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		log.info("Contactos de tipo Usuario Comun (mis comentarios)");
		try {
			List<FinalCustomer> customers = contactDao.getAllFinalCustomersUnnotified();
			if (!customers.isEmpty()) {
				StringBuffer buffer = buildMailMessage(dateFormat, customers);			
				mailService.sendFinalCustomerMail(buffer);
				updateNotified(customers);
			}
			
		} catch (Exception e) {
			log.error("No se ha podido enviar el mail resumen de contactos Usuario Comun", e);
		}
		
		log.info("Contactos de tipo Mayoristas y Franquicias");
		try {
			List<Retailer> contacts = contactDao.getAllRetailersUnnotified();
			if (!contacts.isEmpty()) {
				StringBuffer buffer = buildMailMessage(dateFormat, contacts);			
				mailService.sendRetailerMail(buffer);
				updateNotified(contacts);
			}
			
		} catch (Exception e) {
			log.error("No se ha podido enviar el mail resumen de contactos Mayoristas y Franquicias", e);
		}
		
		log.info("Contactos de tipo Ventas Corporativas");
		try {
			List<CorporateSalesContact> contacts = contactDao.getAllCorporateSalesContactsUnnotified();
			if (!contacts.isEmpty()) {
				StringBuffer buffer = buildMailMessage(dateFormat, contacts);			
				mailService.sendCorporateSalesContactMail(buffer);
				updateNotified(contacts);
			}
			
		} catch (Exception e) {
			log.error("No se ha podido enviar el mail resumen de contactos Ventas Corporativas", e);
		}
		
		log.info("Fin proceso de envio de mails de contactos. ----------------------------------");
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void updateNotified(List<? extends Contact> contacts) {
		for (Contact contact : contacts) {
			contact.setNotified(true);
			contactDao.update(contact);
		}
	}

	private StringBuffer buildMailMessage(SimpleDateFormat dateFormat,
			List<? extends Contact> contacts) {
		
		StringBuffer buffer = new StringBuffer("Se han recibido los siguientes contactos: \n\n");
		for (Contact contact : contacts) {
			
			buffer.append(dateFormat.format(contact.getContactDate()));
			buffer.append(": ");
			buffer.append(contact.getName());
			buffer.append(" (");
			buffer.append(contact.getEmail());
			buffer.append(")\n");
			buffer.append(contact.getComment());
			buffer.append("\n\n");
		}
		return buffer;
	}

}
