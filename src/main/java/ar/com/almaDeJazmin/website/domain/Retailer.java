package ar.com.almaDeJazmin.website.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="mayorista")
public class Retailer extends Contact {

	private static final long serialVersionUID = -4074705343827687445L;

}