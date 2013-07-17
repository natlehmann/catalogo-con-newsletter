package ar.com.almaDeJazmin.website.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.almaDeJazmin.website.domain.FullSizeImage;
import ar.com.almaDeJazmin.website.domain.ImageFile;
import ar.com.almaDeJazmin.website.domain.Product;


@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {
	
	
	@Autowired
	public ProductDaoImpl(HibernateTemplate hibernateTemplate){
		super();
		this.setHibernateTemplate(hibernateTemplate);
	}
	
	

	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public Product create(Product product) {
		getHibernateTemplate().persist(product);
		return product;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void delete(Product product) {
		
		deleteSmallImage(product);		
		getHibernateTemplate().delete(product);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public Product update(Product product) {
		getHibernateTemplate().update(product);
		return product;
	}
	
	@SuppressWarnings("unchecked")
	public Product getById(final Integer id) {
		
		Product product = null;
			
		List<Product> products = getHibernateTemplate().findByNamedParam(
				"Select p from Product p left join fetch p.thumbnail " +
				"left join fetch p.images i where p.id = :id " +
				"order by i.orderNumber", 
				new String[]{"id"}, 
				new Object[] {id});		
		
		if (!products.isEmpty()) {
			product = products.get(0);
		}
		
		return product;
	}



	@SuppressWarnings("unchecked")
	public List<Product> getAll() {
		return getHibernateTemplate().loadAll(Product.class);
	}


	@SuppressWarnings("unchecked")
	public List<Product> getByCategoryId(Integer categoryId) {
		
		List<Product> products = getHibernateTemplate().findByNamedParam(
				"Select distinct(p) from Product p left join fetch p.thumbnail left join fetch p.images, " +
				"IN(p.categories) c where c.id = :categoryId order by p.id desc", 
				"categoryId", categoryId);
		
		return products;
	}


	public void deleteSmallImage(Product product) {
		
		ImageFile smallImage = product.getThumbnail();
		if (smallImage != null) {
			product.setThumbnail(null);
			getHibernateTemplate().update(product);
			getHibernateTemplate().delete(smallImage);
		}
	}
	
	
	public void deleteImageByOrderNumber(Product product, int orderNumber) {
		
		ImageFile img = product.removeImageByOrderNumber(orderNumber);
	
		if (img != null) {
			getHibernateTemplate().delete(img);
			getHibernateTemplate().update(product);
		}
	}
	
	public void deleteAllProductImages(Product product) {
		
		List<FullSizeImage> images = product.getImages();
		if (images != null) {
			
			for (ImageFile image : images) {
				getHibernateTemplate().delete(image);
			}
			
			product.getImages().clear();
			getHibernateTemplate().update(product);
		}
		
		if (product.getThumbnail() != null) {
			product.setThumbnail(null);
			getHibernateTemplate().update(product);
//			getHibernateTemplate().delete(product.getThumbnail());
		}
		
	}



	@SuppressWarnings("unchecked")
	public List<Product> getUnassignedProducts() {
		List<Product> products = getHibernateTemplate().find(
				"Select distinct(p) from Product p left join fetch p.thumbnail where not exists " +
				"(select c from Category c, IN(c.products) p2 where p2 = p)");
		
		return products;
	}

}
