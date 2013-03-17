package ar.com.almaDeJazmin.website.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="FULL")
public class FullSizeImage extends ImageFile {

	private static final long serialVersionUID = 6783617196862737000L;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

}
