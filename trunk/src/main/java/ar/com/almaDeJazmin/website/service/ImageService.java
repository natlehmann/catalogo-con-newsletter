package ar.com.almaDeJazmin.website.service;

import java.io.IOException;

import ar.com.almaDeJazmin.website.domain.ImageFile;

public interface ImageService {
	
	ImageFile resize(ImageFile source, int size) throws IOException;

}
