package ar.com.almaDeJazmin.website.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import ar.com.almaDeJazmin.website.domain.FullSizeImage;
import ar.com.almaDeJazmin.website.domain.ImageFile;
import ar.com.almaDeJazmin.website.domain.ImageFileFormat;
import ar.com.almaDeJazmin.website.domain.Thumbnail;

@Service
public class ImageServiceImpl implements ImageService {


	public ImageFile resize(ImageFile source, int width, int height, boolean isThumbnail) 
	throws IOException {
		
		BufferedImage bigImage = ImageIO.read(new ByteArrayInputStream(source.getContent()));
		BufferedImage smallImage = Scalr.resize(bigImage, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC,
				width, height, Scalr.OP_ANTIALIAS);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(smallImage, getFileFormat(source), output);
		output.flush();
		
		String fileName = source.getFileName();
		
		ImageFile reducedImage = new FullSizeImage();
		
		if (isThumbnail) {
			reducedImage = new Thumbnail();
			fileName = "thumb-" + fileName;
		}
		
		reducedImage.setContent(output.toByteArray());
		reducedImage.setFileName(fileName);
		reducedImage.setType(source.getType());
		
		return reducedImage;
	}

	private String getFileFormat(ImageFile source) {
		return ImageFileFormat.getFromString(source.getType()).getFormatName();
	}

}
