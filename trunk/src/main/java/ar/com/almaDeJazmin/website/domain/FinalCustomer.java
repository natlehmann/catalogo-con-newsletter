package ar.com.almaDeJazmin.website.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="customer")
public class FinalCustomer extends Contact {

	private static final long serialVersionUID = -3092838395727011286L;
	
	@Override
	public String getMailInfo() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Nombre: ");
		buffer.append(this.getName());
		buffer.append("\nMail: ");
		buffer.append(this.getEmail());
		buffer.append("\nTeléfono: ");
		buffer.append(this.getPhoneNumber());
		buffer.append("\nComentario: ");
		buffer.append(this.getComment());
		
		return buffer.toString();
	}

}
