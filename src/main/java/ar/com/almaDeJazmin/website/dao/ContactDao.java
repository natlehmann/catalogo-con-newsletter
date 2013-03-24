package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import ar.com.almaDeJazmin.website.domain.Contact;
import ar.com.almaDeJazmin.website.domain.CorporateSalesContact;
import ar.com.almaDeJazmin.website.domain.FinalCustomer;
import ar.com.almaDeJazmin.website.domain.JobCandidate;
import ar.com.almaDeJazmin.website.domain.Retailer;

public interface ContactDao {

	Contact create(Contact contact);

	void delete(Contact contact);

	Contact update(Contact contact);

	Contact getById(Integer id);

	List<FinalCustomer> getAllFinalCustomersUnnotified();
	
	List<Retailer> getAllRetailersUnnotified();
	
	List<CorporateSalesContact> getAllCorporateSalesContactsUnnotified();

	JobCandidate getJobCandidateByEmail(String email);

}