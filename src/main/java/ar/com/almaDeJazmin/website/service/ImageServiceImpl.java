package ar.com.almaDeJazmin.website.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import ar.com.almaDeJazmin.website.domain.ImageFile;
import ar.com.almaDeJazmin.website.domain.ImageFileFormat;

@Service
public class ImageServiceImpl implements ImageService {


	public ImageFile resize(ImageFile source, int size) throws IOException {
		
		BufferedImage bigImage = ImageIO.read(new ByteArrayInputStream(source.getContent()));
		BufferedImage smallImage = Scalr.resize(bigImage, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC,
				size, size, Scalr.OP_ANTIALIAS);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(smallImage, getFileFormat(source), output);
		output.flush();
		
		ImageFile reducedImage = new ImageFile();
		reducedImage.setContent(output.toByteArray());
		reducedImage.setFileName("thumb-" + source.getFileName());
		reducedImage.setType(source.getType());
		
		return reducedImage;
	}

	private String getFileFormat(ImageFile source) {
		return ImageFileFormat.getFromString(source.getType()).getFormatName();
	}

}
