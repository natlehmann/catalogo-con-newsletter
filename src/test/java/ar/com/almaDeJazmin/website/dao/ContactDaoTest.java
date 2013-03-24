package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.almaDeJazmin.website.AbstractTest;
import ar.com.almaDeJazmin.website.domain.CorporateSalesContact;
import ar.com.almaDeJazmin.website.domain.JobCandidate;
import ar.com.almaDeJazmin.website.domain.Retailer;
import ar.com.almaDeJazmin.website.domain.Contact;
import ar.com.almaDeJazmin.website.domain.FinalCustomer;

public class ContactDaoTest extends AbstractTest {
	
	@Autowired
	private ContactDao contactDao;
	
	@Test
	public void testCreateRetailerContact() {
		
		Retailer contact = buildBusinessContact();
		contact = (Retailer) contactDao.create(contact);
		
		List<Retailer> result = contactDao.getAllRetailersUnnotified();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(contact, result.get(0));
		
		contactDao.delete(contact);
		result = contactDao.getAllRetailersUnnotified();
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testCreateCorporateSalesContact() {
		
		CorporateSalesContact contact = buildCorporateSalesContact();
		contact = (CorporateSalesContact) contactDao.create(contact);
		
		List<CorporateSalesContact> result = contactDao.getAllCorporateSalesContactsUnnotified();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(contact, result.get(0));
		
		contactDao.delete(contact);
		result = contactDao.getAllCorporateSalesContactsUnnotified();
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testCreatePrivateContact() {
		
		FinalCustomer contact = buildPrivateContact();
		contact = (FinalCustomer) contactDao.create(contact);
		
		List<FinalCustomer> result = contactDao.getAllFinalCustomersUnnotified();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(contact, result.get(0));
		
		contactDao.delete(contact);
		result = contactDao.getAllFinalCustomersUnnotified();
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testDontGetPrivateContact() {
		
		FinalCustomer contact = buildPrivateContact();
		contact.setNotified(true);
		contact = (FinalCustomer) contactDao.create(contact);
		
		List<FinalCustomer> result = contactDao.getAllFinalCustomersUnnotified();
		Assert.assertEquals(0, result.size());
		
		contactDao.delete(contact);
	}
	
	@Test
	public void testDontGetRetailer() {
		
		Retailer contact = buildBusinessContact();
		contact.setNotified(true);
		contact = (Retailer) contactDao.create(contact);
		
		List<Retailer> result = contactDao.getAllRetailersUnnotified();
		Assert.assertEquals(0, result.size());
		
		contactDao.delete(contact);
	}
	
	@Test
	public void testDontGetCorporateContact() {
		
		CorporateSalesContact contact = buildCorporateSalesContact();
		contact.setNotified(true);
		contact = (CorporateSalesContact) contactDao.create(contact);
		
		List<CorporateSalesContact> result = contactDao.getAllCorporateSalesContactsUnnotified();
		Assert.assertEquals(0, result.size());
		
		contactDao.delete(contact);
	}
	
	@Test
	public void testCreateContacts() {
		
		Retailer retailer = buildBusinessContact();
		FinalCustomer privateContact = buildPrivateContact();
		CorporateSalesContact corporate = buildCorporateSalesContact();
		contactDao.create(retailer);
		contactDao.create(privateContact);
		contactDao.create(corporate);
		
		List<Retailer> result = contactDao.getAllRetailersUnnotified();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(retailer, result.get(0));
		
		List<FinalCustomer> result2 = contactDao.getAllFinalCustomersUnnotified();
		Assert.assertEquals(1, result2.size());
		Assert.assertEquals(privateContact, result2.get(0));
		
		List<CorporateSalesContact> result3 = contactDao.getAllCorporateSalesContactsUnnotified();
		Assert.assertEquals(1, result3.size());
		Assert.assertEquals(corporate, result3.get(0));
		
		contactDao.delete(privateContact);
		contactDao.delete(retailer);
		contactDao.delete(corporate);
		
		result = contactDao.getAllRetailersUnnotified();
		Assert.assertTrue(result.isEmpty());
		result2 = contactDao.getAllFinalCustomersUnnotified();
		Assert.assertTrue(result2.isEmpty());
		result3 = contactDao.getAllCorporateSalesContactsUnnotified();
		Assert.assertTrue(result3.isEmpty());
	}
	
	@Test
	public void testFindByEmail() {
		
		JobCandidate contact = buildJobCandidate();
		contactDao.create(contact);
		
		Contact result = contactDao.getJobCandidateByEmail(contact.getEmail());
		Assert.assertEquals(contact, result);
		
		contactDao.delete(contact);
		result = contactDao.getJobCandidateByEmail(contact.getEmail());
		Assert.assertNull(result);
	}

	private FinalCustomer buildPrivateContact() {
		
		FinalCustomer contact = new FinalCustomer();
		contact.setComment("comment2");
		contact.setEmail("email2");
		contact.setName("name2");
		return contact;
	}
	
	private JobCandidate buildJobCandidate() {
		
		JobCandidate contact = new JobCandidate();
		contact.setEmail("email2");
		contact.setName("name2");
		return contact;
	}

	private Retailer buildBusinessContact() {
		
		Retailer contact = new Retailer();
		contact.setComment("comment");
		contact.setEmail("email");
		contact.setName("juan");
		return contact;
	}
	
	private CorporateSalesContact buildCorporateSalesContact() {
		
		CorporateSalesContact contact = new CorporateSalesContact();
		contact.setComment("comment");
		contact.setEmail("email");
		contact.setName("juan");
		return contact;
	}

}
