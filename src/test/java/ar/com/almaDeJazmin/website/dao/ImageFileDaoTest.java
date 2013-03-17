package ar.com.almaDeJazmin.website.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.almaDeJazmin.website.AbstractTest;
import ar.com.almaDeJazmin.website.domain.FullSizeImage;
import ar.com.almaDeJazmin.website.domain.ImageFile;
import ar.com.almaDeJazmin.website.domain.Product;

public class ImageFileDaoTest extends AbstractTest {
	
	@Autowired
	private ImageFileDao imageFileDao;
	
	@Autowired
	private ProductDao productDao;

	@Test
	public void testCreateImageFile() {
		
		Product product = new Product();
		product.setName("c123");
		
		FullSizeImage imageFile = new FullSizeImage();
		imageFile.setType("jpg");
		product.addImage(imageFile);
		
		product = productDao.create(product);
		
		Assert.assertEquals(1, product.getImages().size());
		Assert.assertNull(product.getThumbnail());
		imageFile = product.getImages().get(0);
		
		ImageFile p = imageFileDao.getById(imageFile.getId());
		Assert.assertEquals(imageFile, p);
		
		productDao.delete(product);
		
		p = imageFileDao.getById(imageFile.getId());
		Assert.assertNull(p);
	}
}
