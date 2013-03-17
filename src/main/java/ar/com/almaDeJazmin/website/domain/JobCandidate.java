package ar.com.almaDeJazmin.website.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value="cv")
public class JobCandidate extends Contact {

	private static final long serialVersionUID = -6422903613735809391L;
	
	@Transient
	private TextFile cv;
	
	public TextFile getCv() {
		return cv;
	}
	
	public void setCv(TextFile textFile) {
		this.cv = textFile;
	}

}
