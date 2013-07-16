package ar.com.almaDeJazmin.website.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.almaDeJazmin.website.domain.ConfigConstants;
import ar.com.almaDeJazmin.website.domain.Contact;
import ar.com.almaDeJazmin.website.domain.CorporateSalesContact;
import ar.com.almaDeJazmin.website.domain.FinalCustomer;
import ar.com.almaDeJazmin.website.domain.JobCandidate;
import ar.com.almaDeJazmin.website.domain.Retailer;

@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ContactDaoImpl extends HibernateDaoSupport implements ContactDao {
	
	
	@Autowired
	public ContactDaoImpl(HibernateTemplate hibernateTemplate){
		super();
		this.setHibernateTemplate(hibernateTemplate);
	}
	
	

	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public Contact create(Contact contact) {
		getHibernateTemplate().persist(contact);
		return contact;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void delete(Contact contact) {
		getHibernateTemplate().delete(contact);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public Contact update(Contact contact) {
		getHibernateTemplate().update(contact);
		return contact;
	}
	
	public Contact getById(Integer id) {
		return (Contact) getHibernateTemplate().get(Contact.class, id);
	}

	public List<FinalCustomer> getAllFinalCustomersUnnotified() {
		
		@SuppressWarnings("unchecked")
		List<FinalCustomer> contacts = getHibernateTemplate().find(
				"Select c from FinalCustomer c where c.notified = false and c.contactDate >= ?", 
				getMaxContactAge());
		return contacts;
	}

	private Date getMaxContactAge() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, -1 * ConfigConstants.CONTACT_MAX_AGE);
		return date.getTime();
	}



	public List<Retailer> getAllRetailersUnnotified() {
		@SuppressWarnings("unchecked")
		List<Retailer> contacts = getHibernateTemplate().find(
				"Select c from Retailer c where c.notified = false and c.contactDate >= ?", getMaxContactAge());
		return contacts;
	}

	public List<CorporateSalesContact> getAllCorporateSalesContactsUnnotified() {
		@SuppressWarnings("unchecked")
		List<CorporateSalesContact> contacts = getHibernateTemplate().find(
				"Select c from CorporateSalesContact c where c.notified = false and c.contactDate >= ?",
				getMaxContactAge());
		return contacts;
	}



	public JobCandidate getJobCandidateByEmail(String email) {
		@SuppressWarnings("unchecked")
		List<JobCandidate> contacts = getHibernateTemplate().findByNamedParam(
				"Select c from JobCandidate c where c.email = :email", "email", email);
		
		if (!contacts.isEmpty()) {
			return contacts.get(0);
		}
		
		return null;
	}

}