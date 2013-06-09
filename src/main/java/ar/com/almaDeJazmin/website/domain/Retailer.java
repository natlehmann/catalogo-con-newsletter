package ar.com.almaDeJazmin.website.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="mayorista")
public class Retailer extends Contact {

	private static final long serialVersionUID = -4074705343827687445L;
	
	@Column(length=255, nullable=true)
	private String companyName;
	
	@Column(length=255, nullable=true)
	private String productCategories;
	
	private Integer productAmount;
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getProductCategories() {
		return productCategories;
	}
	
	public void setProductCategories(String productCategories) {
		this.productCategories = productCategories;
	}

	public Integer getProductAmount() {
		return productAmount;
	}
	
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
}
