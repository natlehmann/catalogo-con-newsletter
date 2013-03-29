package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import ar.com.almaDeJazmin.website.domain.Newsletter;

public interface NewsletterDao {

	Newsletter create(Newsletter newsletter);

	void delete(Newsletter newsletter);

	Newsletter update(Newsletter newsletter);

	/**
	 * Returns a newsletter identified by Id, initializing its lazy attributes
	 * @param id
	 * @return
	 */
	Newsletter getById(Integer id);

	List<Newsletter> getAll();

	Newsletter getByName(String name);

}