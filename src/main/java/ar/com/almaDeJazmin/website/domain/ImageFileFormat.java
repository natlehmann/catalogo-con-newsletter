package ar.com.almaDeJazmin.website.domain;

public enum ImageFileFormat {
	
	JPG		("jpg"),
	JPEG	("jpg"),
	PNG		("png");
	
	
	private String formatName;
	
	private ImageFileFormat(String formatName) {
		this.formatName = formatName;
	}
	
	public String getFormatName() {
		return formatName;
	}
	
	public static ImageFileFormat getFromString(String format) {
		
		ImageFileFormat result = null;
		
		for (ImageFileFormat fileFormat : ImageFileFormat.values()) {
			if (format.toLowerCase().contains(fileFormat.name().toLowerCase())) {
				result = fileFormat;
			}
		}
		
		return result;
	}

}
