package ar.com.almaDeJazmin.website.domain;

public enum TextFileFormat {
	
	TXT		("text/plain"),
	PDF		("application/pdf"),
	DOC		("application/msword"),
	DOCX	("document");
	
	
	private String formatName;
	
	private TextFileFormat(String formatName) {
		this.formatName = formatName;
	}
	
	public String getFormatName() {
		return formatName;
	}
	
	public static TextFileFormat getFromString(String format) {
		
		TextFileFormat result = null;
		
		for (TextFileFormat fileFormat : TextFileFormat.values()) {
			if (format.toLowerCase().contains(fileFormat.formatName.toLowerCase())) {
				result = fileFormat;
			}
		}
		
		return result;
	}

}
