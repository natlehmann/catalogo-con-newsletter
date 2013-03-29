package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.almaDeJazmin.website.domain.Newsletter;


@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class NewsletterDaoImpl extends HibernateDaoSupport implements NewsletterDao {
	
	
	@Autowired
	public NewsletterDaoImpl(HibernateTemplate hibernateTemplate){
		super();
		this.setHibernateTemplate(hibernateTemplate);
	}
	
	

	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public Newsletter create(Newsletter newsletter) {
		getHibernateTemplate().persist(newsletter);
		return newsletter;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void delete(Newsletter newsletter) {
		getHibernateTemplate().delete(newsletter);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public Newsletter update(Newsletter newsletter) {
		getHibernateTemplate().update(newsletter);
		return newsletter;
	}
	
	@SuppressWarnings("unchecked")
	public Newsletter getById(Integer id) {
		
		List<Newsletter> newsletters = getHibernateTemplate().findByNamedParam(
				"Select c from Newsletter c left join fetch c.distributions p " +
				"where c.id = :id", "id", id);
		
		if (!newsletters.isEmpty()) {
			return newsletters.get(0);
		}
		
		return null;
	}


	@SuppressWarnings("unchecked")
	public List<Newsletter> getAll() {
		List<Newsletter> result = getHibernateTemplate().find("Select c from Newsletter c order by name");
		return result;
	}



	@SuppressWarnings("unchecked")
	public Newsletter getByName(String name) {
		
		Newsletter result = null;
		
		List<Newsletter> newsletters = getHibernateTemplate().findByNamedParam(
				"Select c from Newsletter c where c.name = :name", "name", name);
		
		if (newsletters.size() == 1) {
			result = newsletters.get(0);
		} 
		
		if (newsletters.size() > 1) {
			throw new IllegalStateException("More than one newsletter found under name " + name);
		}
		
		return result;
	}
	
}
