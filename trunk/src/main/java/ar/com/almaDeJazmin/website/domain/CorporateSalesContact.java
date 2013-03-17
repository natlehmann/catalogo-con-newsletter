package ar.com.almaDeJazmin.website.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="corporate")
public class CorporateSalesContact extends Contact {

	private static final long serialVersionUID = 1856936702656698976L;

}
