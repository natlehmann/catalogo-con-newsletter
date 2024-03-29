package ar.com.almaDeJazmin.website.dao;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.almaDeJazmin.website.domain.Category;
import ar.com.almaDeJazmin.website.domain.Product;


@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {
	
	
	@Autowired
	public CategoryDaoImpl(HibernateTemplate hibernateTemplate){
		super();
		this.setHibernateTemplate(hibernateTemplate);
	}
	
	

	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public Category create(Category category) {
		getHibernateTemplate().persist(category);
		return category;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void delete(Category category) {
		
		List<Product> products = category.getProducts();
		
		if (products != null) {
			for (Product product : products) {
				product.removeCategory(category);
				getHibernateTemplate().update(product);
			}
		}
		getHibernateTemplate().delete(category);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public Category update(Category category) {
		getHibernateTemplate().update(category);
		return category;
	}
	
	@SuppressWarnings("unchecked")
	public Category getById(Integer id) {
		
		List<Category> categories = getHibernateTemplate().findByNamedParam(
				"Select c from Category c left join fetch c.products p " +
				"where c.id = :id", "id", id);
		
		if (!categories.isEmpty()) {
			return categories.get(0);
		}
		
		return null;
	}



	@SuppressWarnings("unchecked")
	public List<Category> getAll() {
		List<Category> result = getHibernateTemplate().find("Select c from Category c order by name");
		return result;
	}



	@SuppressWarnings("unchecked")
	public Category getByName(String categoryName) {
		
		Category result = null;
		
		List<Category> categories = getHibernateTemplate().findByNamedParam(
				"Select c from Category c where c.name = :name", "name", categoryName);
		
		if (categories.size() == 1) {
			result = categories.get(0);
		} 
		
		if (categories.size() > 1) {
			throw new IllegalStateException("More than one category found under name " + categoryName);
		}
		
		return result;
	}



	@SuppressWarnings("unchecked")
	public List<Category> getAll(Locale locale) {
		
		String sortBy = Category.getSortByField(locale);
		List<Category> result = getHibernateTemplate().find("Select c from Category c order by " + sortBy);
		return result;
	}



	@SuppressWarnings("unchecked")
	public long getCategoryCount() {
		
		List<Long> result = getHibernateTemplate().find("select count(c) from Category c");
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return 0;
	}

	
}
