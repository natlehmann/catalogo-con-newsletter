package ar.com.almaDeJazmin.website.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="customer")
public class FinalCustomer extends Contact {

	private static final long serialVersionUID = -3092838395727011286L;

}
