package ar.com.almaDeJazmin.website.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Product implements Serializable {
	
	private static final long serialVersionUID = -6629557767460796478L;

	private static final int MAX_DESCRIPTION_LENGTH = 100;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=255, nullable=false)
	private String name;
	
	@Column(length=20, nullable=false, unique=true)
	private String code;
	
	@Column(length=512)
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="product")
	private List<ImageFile> images;
	
	@JoinColumn(name="smallImageId")
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private ImageFile smallImage;
	
	@JoinTable(name="Category_Product", joinColumns={@JoinColumn(name="product_id")}, 
			inverseJoinColumns={@JoinColumn(name="category_id")})
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE}, fetch=FetchType.EAGER)
	private List<Category> categories;
	
	
	@Transient
	private Map<Integer, ImageFile> imagesByOrderNumber;
	
	
	public Product() {}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ImageFile> getImages() {
		return images;
	}

	public void setImages(List<ImageFile> images) {
		this.images = images;
		this.imagesByOrderNumber = null;
	}
	
	public void addImage(ImageFile image) {
		if (this.images == null) {
			this.images = new LinkedList<ImageFile>();
		}
		this.images.add(image);
		image.setProduct(this);
		
		this.imagesByOrderNumber = null;
	}

	public ImageFile getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(ImageFile smallImage) {
		this.smallImage = smallImage;
		if (this.smallImage != null) {
			this.smallImage.setProduct(this);
			this.smallImage.setSmallImage(Boolean.TRUE);
		}
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Product " + this.name + " (id: " + this.id + ")";
	}


	public void addCategory(Category category) {
		if (this.categories == null) {
			this.categories = new LinkedList<Category>();
		}
		
		this.categories.add(category);
	}



	public Map<Integer, ImageFile> getImagesByOrderNumber() {
		
		if (this.imagesByOrderNumber == null) {
			
			this.imagesByOrderNumber = new HashMap<Integer, ImageFile>();
			if (this.images != null) {
				for (ImageFile img : this.images) {
					
					if (img.getOrderNumber() != null) {
						this.imagesByOrderNumber.put(img.getOrderNumber(), img);
					}
				}
			}
		}
		
		return this.imagesByOrderNumber;
	}
	


	/**
	 * Removes an image if there is one with the given orderNumber.
	 * Returns the image that was removed, if any.
	 * @param index
	 * @return
	 */
	public ImageFile removeImageByOrderNumber(int index) {
		
		ImageFile result = null;
		
		if (this.images != null) {
			
			Iterator<ImageFile> it = this.images.iterator();
			while (it.hasNext() && result == null) {
				ImageFile img = it.next();
				if (img.getOrderNumber() != null && img.getOrderNumber().intValue() == index) {
					
					result = img;
					it.remove();
				}
			}
		}
		
		return result;
	}


	public void removeCategory(Category category) {
		if (this.categories != null) {
			
			Iterator<Category> it = this.categories.iterator();
			while (it.hasNext()) {
				if (it.next().equals(category)) {
					it.remove();
				}
			}
		}
		
	}


	public static String getSortByField(Locale locale) {
		return "name";
	}
	

}
