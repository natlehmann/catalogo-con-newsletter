package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import ar.com.almaDeJazmin.website.domain.ImageFile;

public interface ImageFileDao {

	ImageFile create(ImageFile imageFile);

	void delete(ImageFile imageFile);

	ImageFile update(ImageFile imageFile);

	ImageFile getById(Integer id);

	List<ImageFile> getAll();

}