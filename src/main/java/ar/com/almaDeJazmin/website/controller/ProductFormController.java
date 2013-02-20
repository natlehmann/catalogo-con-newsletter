package ar.com.almaDeJazmin.website.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.support.RequestContextUtils;

import ar.com.almaDeJazmin.website.controller.validation.ProductValidator;
import ar.com.almaDeJazmin.website.dao.CategoryDao;
import ar.com.almaDeJazmin.website.dao.ImageFileDao;
import ar.com.almaDeJazmin.website.dao.ProductDao;
import ar.com.almaDeJazmin.website.domain.Category;
import ar.com.almaDeJazmin.website.domain.ConfigConstants;
import ar.com.almaDeJazmin.website.domain.ImageFile;
import ar.com.almaDeJazmin.website.domain.Product;
import ar.com.almaDeJazmin.website.domain.ValidationException;


@Controller
public class ProductFormController extends MultiActionController {
	
	private static Log log = LogFactory.getLog(ProductFormController.class);

	@Autowired
	private ImageFileDao imageFileDao;
	
	
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	

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
			
			this.addImages(product, request);
			productDao.update(product);
			
			
			try {
				this.addCategories(product, request);
				productDao.update(product);
				
				return new ModelAndView("redirect:productList.html");
				
				
			} catch (ValidationException e) {
				return new ModelAndView("redirect:productFormInit.html?id=" + product.getId());
			}
			
			
		} else {
			return new ModelAndView("/admin/productForm").addAllObjects(
					binder.getBindingResult().getModel()).addObject("categories", getCategoryList())
					.addObject("product", binder.getBindingResult().getTarget());
		}

	}


	private Product buildProduct(Product product, HttpServletRequest request) 
	throws ServletRequestBindingException, IOException {
		
		String code = ServletRequestUtils.getRequiredStringParameter(request, "code");
		String name = ServletRequestUtils.getRequiredStringParameter(request, "name");
		String description = request.getParameter("description");
		
		product.setCode(code);
		product.setName(name);
		product.setDescription(description);
		
		return product;
	}
	
	private void addImages(Product product, HttpServletRequest request) 
	throws ServletRequestBindingException, IOException {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		for (int index = 0; index < ConfigConstants.MAX_IMAGE_UPLOAD; index++) {
			
			ImageFile imageFile = buildImageFile(multipartRequest, "imageFile", index);
			if (imageFile != null) {

				ImageFile deletedImg = product.removeImageByOrderNumber(index);
				if (deletedImg != null) {
					imageFileDao.delete(deletedImg);
				}
				
				product.addImage(imageFile);
			}
		}
		
		ImageFile smallImage = buildImageFile(multipartRequest, "smallImageFile", null);
		if (smallImage != null) {
			
			if (product.getSmallImage() != null) {
				productDao.deleteSmallImage(product);
			}
			
			product.setSmallImage(smallImage);
		}
	}
	
	private void addCategories(Product product, ServletRequest request) 
	throws ServletRequestBindingException, ValidationException {
		
		// first we process the selection of categories
		
		String categoryIdsStr = ServletRequestUtils.getStringParameter(request, "param_categories");
		
		
		if (product.getCategories() != null) {
			product.getCategories().clear();
		}
		
		if (categoryIdsStr != null) {
			for (String categoryIdStr : categoryIdsStr.split(",")) {
				
				if (categoryIdStr != null && !categoryIdStr.trim().equals("")) {
					Category category = categoryDao.getById(Integer.parseInt(categoryIdStr));
					product.addCategory(category);
				}
			}
		}
		
		// now we process any new categories
		
		String categoryName = ServletRequestUtils.getStringParameter(request, "param_category_name");
		
		if (categoryName != null && !categoryName.trim().equals("")) {
			
			Category newCategory = null;
			Category existentCategory = categoryDao.getByName(categoryName);
			
			if (existentCategory != null) {
				newCategory = existentCategory;
				
			} else {
				newCategory = new Category();
				newCategory.setName(categoryName);
				
				newCategory = categoryDao.create(newCategory);
			}
			
			product.addCategory(newCategory);
		}
		
		if (product.getCategories() == null || product.getCategories().isEmpty()) {
			throw new ValidationException();
		}
	}


	private ImageFile buildImageFile(MultipartHttpServletRequest multipartRequest, 
			String paramName, Integer index) throws IOException {
		
		ImageFile imageFile = null;
		
		if (index != null) {
			paramName = paramName + "_" + index;
		}
		
		MultipartFile multipartFile = multipartRequest.getFile(paramName);
		
		if (multipartFile != null && multipartFile.getOriginalFilename() != null 
				&& !multipartFile.getOriginalFilename().trim().equals("")) {
			
			imageFile = new ImageFile();
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
			
			this.addImages(product, request);
			productDao.update(product);
			
			try {
				addCategories(product, request);
				productDao.update(product);
				
			} catch (ValidationException e) {
				return new ModelAndView("redirect:productFormInit.html?id=" + product.getId());
			}
			
			return new ModelAndView("redirect:productList.html");
			
		} else {
			
			return new ModelAndView("/admin/productForm").addAllObjects(
					binder.getBindingResult().getModel()).addObject("categories", getCategoryList());
		}
		
	}



	@SuppressWarnings("rawtypes")
	private ServletRequestDataBinder validate(HttpServletRequest request, Class clazz, Object object) 
	throws Exception {
		
		ServletRequestDataBinder binder = createBinder(request, object);
		binder.bind(request);
		
		if (this.getValidators() != null) {
		    for (Validator val : this.getValidators()) {
		        if (val != null && val.supports(clazz)) {
		        	ValidationUtils.invokeValidator(val, object, binder.getBindingResult());
		        }
		    }
		}
		
		return binder;
		
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
