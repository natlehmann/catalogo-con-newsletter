package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import ar.com.almaDeJazmin.website.domain.Product;

public interface ProductDao {

	Product create(Product product);

	void delete(Product product);

	Product update(Product product);

	/**
	 * Returns a product identified by Id, initializing its lazy attributes.
	 * Images belonging to the Product are returned in the order specified by their attribute
	 * <code>order</code>.
	 * @param id
	 * @return
	 */
	Product getById(Integer id);

	List<Product> getAll();

	/**
	 * Returns all products that belong to a certain category identified by id.
	 * It initializes all products' images.
	 * @param categoryId
	 * @return
	 */
	List<Product> getByCategoryId(Integer categoryId);

	/**
	 * Removes a product's small image.
	 * @param product
	 */
	void deleteSmallImage(Product product);

	/**
	 * Returns all products that are not assigned to any category.
	 * It only initializes the products' small image.
	 * @return
	 */
	List<Product> getUnassignedProducts();

	/**
	 * Removes a product's image identified by order number. If there is more than one image with the given order
	 * number, only the first one found will be deleted. If no image with the given order number is found, none will be deleted.
	 * @param product
	 * @param orderNumber
	 */
	void deleteImageByOrderNumber(Product product, int orderNumber);
	
	/**
	 * Deletes ALL product images, including thumbnails if any
	 * @param product
	 */
	void deleteAllProductImages(Product product);


}