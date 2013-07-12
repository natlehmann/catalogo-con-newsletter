package ar.com.almaDeJazmin.website.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="corporate")
public class CorporateSalesContact extends Contact {

	private static final long serialVersionUID = 1856936702656698976L;
	
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

	@Override
	public String getMailInfo() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Nombre: ");
		buffer.append(this.getName());
		buffer.append("\nRazón Social: ");
		buffer.append(this.getCompanyName());
		buffer.append("\nMail: ");
		buffer.append(this.getEmail());
		buffer.append("\nTeléfono: ");
		buffer.append(this.getPhoneNumber());
		buffer.append("\nRubros: ");
		buffer.append(this.getProductCategories());
		buffer.append("\nCantidad de productos: ");
		buffer.append(this.getProductAmount());
		buffer.append("\nComentario: ");
		buffer.append(this.getComment());
		
		return buffer.toString();
	}
}
