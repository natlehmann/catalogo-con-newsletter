package ar.com.almaDeJazmin.website.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value="cv")
public class JobCandidate extends Contact {

	private static final long serialVersionUID = -6422903613735809391L;
	
	@Column(length=255, nullable=true)
	private String companyName;
	
	@Transient
	private TextFile cv;
	
	public TextFile getCv() {
		return cv;
	}
	
	public void setCv(TextFile textFile) {
		this.cv = textFile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
	@Override
	public String toString() {
		
		StringBuffer message = new StringBuffer();
		message.append("Nombre y apellido: ").append(this.getName()).append("\n");
		message.append("Razón Social: ").append(this.getCompanyName()).append("\n");
		message.append("Email: ").append(this.getEmail()).append("\n");
		message.append("Teléfono: ").append(this.getPhoneNumber()).append("\n");
		
		return message.toString();
	}
}
