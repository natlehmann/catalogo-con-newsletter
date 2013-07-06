package ar.com.almaDeJazmin.website.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=30, nullable=true)
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="product")
	private List<FullSizeImage> images;
	
	@JoinColumn(name="thumbnailId")
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Thumbnail thumbnail;
	
	@JoinTable(name="Category_Product", joinColumns={@JoinColumn(name="product_id")}, 
			inverseJoinColumns={@JoinColumn(name="category_id")})
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE}, fetch=FetchType.EAGER)
	private List<Category> categories;
	
	@Transient
	private Map<Integer, FullSizeImage> imagesByOrderNumber;
	
	@Transient
	private String[] categoryNames;
	
	
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

	public List<FullSizeImage> getImages() {
		return images;
	}

	public void setImages(List<FullSizeImage> images) {
		this.images = images;
		this.imagesByOrderNumber = null;
	}
	
	public void addImage(FullSizeImage image) {
		if (this.images == null) {
			this.setImages(new LinkedList<FullSizeImage>());
		}
		this.images.add(image);
		image.setProduct(this);
	}

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
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
		return "Product id: " + this.id;
	}


	public void addCategory(Category category) {
		if (this.categories == null) {
			this.categories = new LinkedList<Category>();
		}
		
		this.categories.add(category);
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
	
	/**
	 * Returns the first image that is not a small image
	 * @return
	 */
	public ImageFile getFirstBigImage() {
		if (this.images != null && !this.images.isEmpty()) {
			return this.images.get(0);
		}
		
		return null;
	}
	
	/**
	 * Removes an image if there is one with the given orderNumber.
	 * Returns the image that was removed, if any.
	 * @param index
	 * @return
	 */
	public FullSizeImage removeImageByOrderNumber(int index) {
		
		FullSizeImage result = null;
		
		if (this.images != null) {
			
			Iterator<FullSizeImage> it = this.images.iterator();
			while (it.hasNext() && result == null) {
				FullSizeImage img = it.next();
				if (img.getOrderNumber() != null && img.getOrderNumber().intValue() == index) {
					
					result = img;
					it.remove();
				}
			}
		}
		
		return result;
	}
	
	public Map<Integer, FullSizeImage> getImagesByOrderNumber() {
		
		if (this.imagesByOrderNumber == null) {
			
			this.imagesByOrderNumber = new HashMap<Integer, FullSizeImage>();
			if (this.images != null) {
				for (FullSizeImage img : this.images) {
					
					if (img.getOrderNumber() != null) {
						this.imagesByOrderNumber.put(img.getOrderNumber(), img);
					}
				}
			}
		}
		
		return this.imagesByOrderNumber;
	}
	
	public String[] getCategoryNames() {
		return categoryNames;
	}
	
	public void setCategoryNames(String[] categoryNames) {
		this.categoryNames = categoryNames;
	}

}
