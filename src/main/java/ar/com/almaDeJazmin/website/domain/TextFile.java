package ar.com.almaDeJazmin.website.domain;

import java.io.Serializable;

public class TextFile implements Serializable {
	
	private static final long serialVersionUID = -4372326263024954141L;

	private byte[] content;
	
	private String fileName;
	
	private String fileType;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
