package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.almaDeJazmin.website.AbstractTest;
import ar.com.almaDeJazmin.website.domain.BusinessContact;
import ar.com.almaDeJazmin.website.domain.Contact;
import ar.com.almaDeJazmin.website.domain.PrivateContact;

public class ContactDaoTest extends AbstractTest {
	
	@Autowired
	private ContactDao contactDao;
	
	@Test
	public void testCreateBusinessContact() {
		
		BusinessContact contact = buildBusinessContact();
		contact = (BusinessContact) contactDao.create(contact);
		
		List<Contact> result = contactDao.getAllBusinessContacts();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(contact, result.get(0));
		
		contactDao.delete(contact);
		result = contactDao.getAllBusinessContacts();
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testCreatePrivateContact() {
		
		PrivateContact contact = buildPrivateContact();
		contact = (PrivateContact) contactDao.create(contact);
		
		List<Contact> result = contactDao.getAllPrivateContacts();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(contact, result.get(0));
		
		contactDao.delete(contact);
		result = contactDao.getAllPrivateContacts();
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testCreateContacts() {
		
		BusinessContact businessContact = buildBusinessContact();
		PrivateContact privateContact = buildPrivateContact();
		contactDao.create(businessContact);
		contactDao.create(privateContact);
		
		List<Contact> result = contactDao.getAllBusinessContacts();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(businessContact, result.get(0));
		
		result = contactDao.getAllPrivateContacts();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(privateContact, result.get(0));
		
		contactDao.delete(privateContact);
		contactDao.delete(businessContact);
		
		result = contactDao.getAllBusinessContacts();
		Assert.assertTrue(result.isEmpty());
		result = contactDao.getAllPrivateContacts();
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testFindByEmail() {
		
		PrivateContact contact = buildPrivateContact();
		contactDao.create(contact);
		
		Contact result = contactDao.getByEmail(contact.getEmail());
		Assert.assertEquals(contact, result);
		
		contactDao.delete(contact);
		result = contactDao.getByEmail(contact.getEmail());
		Assert.assertNull(result);
	}

	private PrivateContact buildPrivateContact() {
		
		PrivateContact contact = new PrivateContact();
		contact.setComment("comment2");
		contact.setEmail("email2");
		contact.setName("name2");
		return contact;
	}

	private BusinessContact buildBusinessContact() {
		
		BusinessContact contact = new BusinessContact();
		contact.setComment("comment");
		contact.setEmail("email");
		contact.setName("juan");
		return contact;
	}

}
