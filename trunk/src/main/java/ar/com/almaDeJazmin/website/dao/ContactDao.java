package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import ar.com.almaDeJazmin.website.domain.Contact;

public interface ContactDao {

	Contact create(Contact contact);

	void delete(Contact contact);

	Contact update(Contact contact);

	Contact getById(Integer id);

	List<Contact> getAllBusinessContacts();
	
	List<Contact> getAllPrivateContacts();

	Contact getByEmail(String email);

}