package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.almaDeJazmin.website.domain.Contact;

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



	public List<Contact> getAllBusinessContacts() {
		
		@SuppressWarnings("unchecked")
		List<Contact> contacts = getHibernateTemplate().find("Select c from BusinessContact c");
		return contacts;
	}



	public List<Contact> getAllPrivateContacts() {
		
		@SuppressWarnings("unchecked")
		List<Contact> contacts = getHibernateTemplate().find("Select c from PrivateContact c");
		return contacts;
	}



	public Contact getByEmail(String email) {
		
		@SuppressWarnings("unchecked")
		List<Contact> contacts = getHibernateTemplate().findByNamedParam(
				"Select c from Contact c where c.email = :email", "email", email);
		
		if (!contacts.isEmpty()) {
			return contacts.get(0);
		}
		
		return null;
	}

}
