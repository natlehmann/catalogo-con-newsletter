package ar.com.almaDeJazmin.website.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="private")
public class PrivateContact extends Contact {

	private static final long serialVersionUID = -3092838395727011286L;

}
