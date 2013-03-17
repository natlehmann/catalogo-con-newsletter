package ar.com.almaDeJazmin.website.domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="THUMB")
public class Thumbnail extends ImageFile {

	private static final long serialVersionUID = 6544151401979951639L;
	
	/**
	 * The full size image this thumbnail is a reduced version of
	 */
	@JoinColumn(name="fullSizeImageId")
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private FullSizeImage fullSizeImage;
	
	public FullSizeImage getFullSizeImage() {
		return fullSizeImage;
	}
	
	public void setFullSizeImage(FullSizeImage fullSizeImage) {
		this.fullSizeImage = fullSizeImage;
	}
}
