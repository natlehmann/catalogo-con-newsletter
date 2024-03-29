package ar.com.almaDeJazmin.website.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ar.com.almaDeJazmin.website.controller.validation.ProductValidator;
import ar.com.almaDeJazmin.website.dao.CategoryDao;
import ar.com.almaDeJazmin.website.dao.ImageFileDao;
import ar.com.almaDeJazmin.website.dao.ProductDao;
import ar.com.almaDeJazmin.website.domain.Category;
import ar.com.almaDeJazmin.website.domain.ConfigConstants;
import ar.com.almaDeJazmin.website.domain.FullSizeImage;
import ar.com.almaDeJazmin.website.domain.ImageFile;
import ar.com.almaDeJazmin.website.domain.ImageFileFormat;
import ar.com.almaDeJazmin.website.domain.InvalidImageFileFormatException;
import ar.com.almaDeJazmin.website.domain.Product;
import ar.com.almaDeJazmin.website.domain.Thumbnail;
import ar.com.almaDeJazmin.website.domain.ValidationException;
import ar.com.almaDeJazmin.website.service.ImageService;


@Controller
public class ProductFormController extends MultiActionController {
	
	private static Log log = LogFactory.getLog(ProductFormController.class);

	@Autowired
	private ImageFileDao imageFileDao;
	
	
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ImageService imageService;
	

	@Autowired
	public ProductFormController(ProductDao productDao){
		this.productDao = productDao;
		this.setValidators(new Validator[]{new ProductValidator(productDao)});
	}
	
	
	@RequestMapping("/admin/productFormInit.html")
	public ModelAndView productFormInit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Integer id = ServletRequestUtils.getIntParameter(request, "id");
		
		Product product = new Product();
		
		if (id != null) {
			product = productDao.getById(id);
			product.setCategoryNames();
		}
		
		Map<String, Object> params = getParameterMap(product);
		
		return new ModelAndView("admin/productForm", params);
	}
		
	
	private Map<String, Object> getParameterMap(Product product) {
		
		List<Category> categories = getCategoryList();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("product", product);
		params.put("command", product);
		params.put("categories", categories);
		
		if (product.getId() != null 
				&& (product.getCategories() == null || product.getCategories().isEmpty())) {
			params.put("warning", "this.product.does.not.belong.to.any.category");
		}
		
		return params;
	}
	
	
	private List<Category> getCategoryList() {
		
		List<Category> categories = categoryDao.getAll();		
		return categories;
	}


	@RequestMapping(value="/admin/productCreate.html")
	public ModelAndView productCreate(HttpServletRequest request,
			HttpServletResponse response) 
	throws Exception {

		String action = request.getParameter("action");

		if (action == null || action.trim().equals("")) {
			action = request.getParameter("actionBt");
		}
		
		if (action.equals("create")) {
			return createProduct(request, response);
		}
		
		if (action.equals("update")) {
			return updateProduct(request, response);
		}
		
		if (action.equals("delete")) {
			return deleteProduct(request, response);
		}
		
		if (action.equals("deleteSmallImage")) {
			return deleteSmallImage(request, response);
		}
		
		if (action.equals("deleteImage")) {
			return deleteImage(request, response);
		}
		
		if (action.equals("back")) {
			return new ModelAndView("redirect:productList.html");
		}

		return null;
		
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private ModelAndView deleteImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		Product product = productDao.getById(Integer.parseInt(request.getParameter("id"))); 
		product = buildProduct(product, request);
		
		int imageOrderNumber = ServletRequestUtils.getIntParameter(request, "imageOrderNumberParam");
		
		ServletRequestDataBinder binder = validate(request, product.getClass(), product);		
		
		if (!binder.getBindingResult().hasErrors()) {
			
			try {
				productDao.deleteImageByOrderNumber(product, imageOrderNumber);

				addImages(product, request);
				addCategories(product, request);
				productDao.update(product);
				
				Map<String, Object> params = getParameterMap(product);				
				return new ModelAndView("/admin/productForm", params);
				
				
			} catch (ValidationException e) {
				return new ModelAndView("redirect:productFormInit.html?id=" + product.getId());
			}
			
			
		} else {
			return new ModelAndView("/admin/productForm").addAllObjects(
					binder.getBindingResult().getModel()).addObject("categories", getCategoryList());
		}
		
	}


	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@RequestMapping(value="/admin/deleteProduct.html")
	public ModelAndView deleteProduct(HttpServletRequest request,
			HttpServletResponse response) {
		
		Product product = productDao.getById(Integer.parseInt(request.getParameter("id")));
		productDao.delete(product);
		
		return new ModelAndView("redirect:productList.html");
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private ModelAndView deleteSmallImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Product product = productDao.getById(Integer.parseInt(request.getParameter("id"))); 
		product = buildProduct(product, request);
		
		ServletRequestDataBinder binder = validate(request, product.getClass(), product);		
		
		if (!binder.getBindingResult().hasErrors()) {
			
			try {
				addImages(product, request);
				addCategories(product, request);
				productDao.update(product);
				
				productDao.deleteSmallImage(product);
				
				Map<String, Object> params = getParameterMap(product);				
				return new ModelAndView("/admin/productForm", params);
				
				
			} catch (ValidationException e) {
				return new ModelAndView("redirect:productFormInit.html?id=" + product.getId());
			}
			
			
		} else {
			return new ModelAndView("/admin/productForm").addAllObjects(
					binder.getBindingResult().getModel()).addObject("categories", getCategoryList());
		}
		
	}


	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private ModelAndView updateProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Product product = productDao.getById(Integer.parseInt(request.getParameter("id"))); 
		product = buildProduct(product, request);
		
		ServletRequestDataBinder binder = validate(request, product.getClass(), product);		
		
		if (!binder.getBindingResult().hasErrors()) {
			
			try {
				
				this.addImages(product, request);
				productDao.update(product);
				
				this.addCategories(product, request);
				productDao.update(product);
				
				return new ModelAndView("redirect:productList.html");
				
				
			} catch (ValidationException e) {
				
				binder.getBindingResult().rejectValue("thumbnail", e.getMessage(), 
						"error al modificar producto");
				return new ModelAndView("/admin/productList").addAllObjects(
						binder.getBindingResult().getModel())
						.addObject("categories", getCategoryList())
						.addObject("product", binder.getBindingResult().getTarget())
						.addObject("allProductsByCategory", getAllProductsByCategory())
						.addObject("unassigned", getUnassignedProducts());
				
			} 			
			
		} else {
			return new ModelAndView("/admin/productList").addAllObjects(
					binder.getBindingResult().getModel())
					.addObject("categories", getCategoryList())
					.addObject("product", binder.getBindingResult().getTarget())
					.addObject("allProductsByCategory", getAllProductsByCategory())
					.addObject("unassigned", getUnassignedProducts());
		}

	}


	private Product buildProduct(Product product, HttpServletRequest request) 
	throws ServletRequestBindingException, IOException {
		
		String name = request.getParameter("name");
		product.setName(name);
		
		return product;
	}
	
	private void addImages(Product product, HttpServletRequest request) 
	throws ServletRequestBindingException, IOException, ValidationException {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		// we allow only a single image upload, received as "smallImage"
		// the same image will be saved twice in different sizes:
		// 1- As thumbnail
		// 2- As the original image in bigger size for zooming --> images[0]
		
		FullSizeImage newImage = buildImageFile(multipartRequest, "smallImageFile", null);
		if (newImage != null) {
			
			if (product.getThumbnail() != null) {
				productDao.deleteSmallImage(product);
			}
			
			if (product.getImages() != null && product.getImages().size() > 0) {
				productDao.deleteAllProductImages(product);
			}
			
			FullSizeImage fullSizeImage = (FullSizeImage) imageService.resize(
					newImage, ConfigConstants.PRODUCT_GALLERY_ZOOM_SIZE_IMG_WIDTH, 
					ConfigConstants.PRODUCT_GALLERY_ZOOM_SIZE_IMG_HEIGHT, false);
			fullSizeImage.setOrderNumber(0);
			
			product.addImage(fullSizeImage);
			
			Thumbnail thumbnail = (Thumbnail) imageService.resize(
					newImage, ConfigConstants.PRODUCT_GALLERY_THUMB_IMG_WIDTH, 
					ConfigConstants.PRODUCT_GALLERY_THUMB_IMG_HEIGHT, true);
			product.setThumbnail(thumbnail);
			
		} 
		
		if (product.getImages() == null || product.getImages().isEmpty()) {
			throw new ValidationException("must.select.picture");
		}
	}
	
	private void addCategories(Product product, ServletRequest request) 
	throws ServletRequestBindingException, ValidationException {
		
		String[] categoryNames = product.getCategoryNames();
		
		if (product.getCategories() != null) {
			product.getCategories().clear();
		}
		
		if (categoryNames != null) {
			for (String categoryId : categoryNames) {
				
				Category category = categoryDao.getById(Integer.parseInt(categoryId));
				product.addCategory(category);
			}
		}
		
		if (product.getCategories() == null || product.getCategories().isEmpty()) {
			throw new ValidationException("must.select.category");
		}
	}


	private FullSizeImage buildImageFile(MultipartHttpServletRequest multipartRequest, 
			String paramName, Integer index) throws IOException, InvalidImageFileFormatException {
		
		FullSizeImage imageFile = null;
		
		if (index != null) {
			paramName = paramName + "_" + index;
		}
		
		MultipartFile multipartFile = multipartRequest.getFile(paramName);
		
		if (multipartFile != null && multipartFile.getOriginalFilename() != null 
				&& !multipartFile.getOriginalFilename().trim().equals("")) {
			
			if (ImageFileFormat.getFromString(multipartFile.getContentType()) == null) {
				throw new InvalidImageFileFormatException();
			}
			
			imageFile = new FullSizeImage();
			imageFile.setFileName(multipartFile.getOriginalFilename());
			imageFile.setType(multipartFile.getContentType());
			imageFile.setContent(multipartFile.getBytes());
			
			imageFile.setOrderNumber(index);
		
		}
		
		return imageFile;
	}
	



	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private ModelAndView createProduct(HttpServletRequest request,
			HttpServletResponse response) 
	throws Exception {
		
		Product product = buildProduct(new Product(), request);
		
		ServletRequestDataBinder binder = validate(request, product.getClass(), product);		
		
		if (!binder.getBindingResult().hasErrors()) {
			
			productDao.create(product);
			
			try {
				
				this.addImages(product, request);
				productDao.update(product);
				
				addCategories(product, request);
				productDao.update(product);
				
			} catch (ValidationException e) {
				
				binder.getBindingResult().rejectValue("thumbnail", e.getMessage(), 
						"error al crear producto");
				return new ModelAndView("/admin/productList").addAllObjects(
						binder.getBindingResult().getModel())
						.addObject("categories", getCategoryList())
						.addObject("product", binder.getBindingResult().getTarget())
						.addObject("allProductsByCategory", getAllProductsByCategory())
						.addObject("unassigned", getUnassignedProducts());
			} 
			
			return new ModelAndView("redirect:productList.html");
			
		} else {
			
			return new ModelAndView("/admin/productList").addAllObjects(
					binder.getBindingResult().getModel())
					.addObject("categories", getCategoryList())
					.addObject("product", binder.getBindingResult().getTarget())
					.addObject("allProductsByCategory", getAllProductsByCategory())
					.addObject("unassigned", getUnassignedProducts());
		}
		
	}
	
	
	private Map<Category, List<Product>> getAllProductsByCategory() {
		Map<Category, List<Product>> allProductsByCategory = new HashMap<Category, List<Product>>();
		
		List<Category> categories = categoryDao.getAll();
		for (Category category : categories) {
			
			allProductsByCategory.put(category, productDao.getByCategoryId(category.getId()));
		}
		
		return allProductsByCategory;
	}
	
	private List<Product> getUnassignedProducts() {
	
		List<Product> unassigned = productDao.getUnassignedProducts();
		return unassigned;
	}
	

	@RequestMapping("/imageView.html")
	public ModelAndView imageView(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int id = ServletRequestUtils.getRequiredIntParameter(request, "id");

		ImageFile image = imageFileDao.getById(id);

		response.setContentType(image.getType());
		response.setContentLength(image.getContent().length);
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ image.getFileName() + "\"");

		FileCopyUtils.copy(image.getContent(), response.getOutputStream());

		return null;

	}



}
