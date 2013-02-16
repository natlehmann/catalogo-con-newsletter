package ar.com.almaDeJazmin.website.dao;

import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.almaDeJazmin.website.AbstractTest;
import ar.com.almaDeJazmin.website.domain.Category;
import ar.com.almaDeJazmin.website.domain.Product;


public class CategoryDaoTest extends AbstractTest {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	private Category category;
	private Category firstCategory;
	private Category secondCategory;
	private Category categoryWithProducts;
	private Product product;
	
	@Before
	public void init() {
		category = new Category();
		category.setName("hola");
		category = categoryDao.create(category);
		
		categoryWithProducts = new Category();
		categoryWithProducts.setName("categoryWithProducts");
		categoryWithProducts = categoryDao.create(categoryWithProducts);
		
		firstCategory = new Category();
		firstCategory.setName("A");
		firstCategory = categoryDao.create(firstCategory);
		
		secondCategory = new Category();
		secondCategory.setName("B");
		secondCategory = categoryDao.create(secondCategory);
		
		product = new Product();
		product.setCode("tt1");
		product.setName("x12344");
		product = productDao.create(product);
		
		product.addCategory(categoryWithProducts);
		product = productDao.update(product);
		
		categoryWithProducts = categoryDao.getById(categoryWithProducts.getId());
	}
	
	@After
	public void cleanUp() {
		categoryDao.delete(category);
		categoryDao.delete(categoryWithProducts);
		categoryDao.delete(firstCategory);
		categoryDao.delete(secondCategory);
		
		productDao.delete(product);
	}

	@Test
	public void testGetAll() {
		
		List<Category> categories = categoryDao.getAll();
		Assert.assertEquals(4, categories.size());
		Assert.assertTrue(categories.contains(category));
		Assert.assertTrue(categories.contains(categoryWithProducts));
		Assert.assertTrue(categories.contains(firstCategory));
		Assert.assertTrue(categories.contains(secondCategory));
	}
	
	@Test
	public void testGetAll2() {
		
		List<Category> categories = categoryDao.getAll(new Locale("en"));
		Assert.assertEquals(4, categories.size());

	}
	
	@Test
	public void testDeleteCategory() {
		
		categoryDao.delete(categoryWithProducts);
		product = productDao.getById(product.getId());
		
		Assert.assertTrue(product.getCategories().isEmpty());
		
		// se inserta nuevamente para que se pueda borrar despues
		categoryWithProducts = new Category();
		categoryWithProducts.setName("categoryWithProducts");
		categoryWithProducts = categoryDao.create(categoryWithProducts);
	}
	
}
