package ar.com.almaDeJazmin.website.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "size", discriminatorType = DiscriminatorType.STRING)
public abstract class ImageFile implements Serializable {
	
	private static final long serialVersionUID = 6162652429823669897L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Lob
	@Column(length=99999999) // to force longblob
	private byte[] content;
	
	@Column(length=20)
	private String type;
	
	@Column(length=255)
	private String fileName;
	
	private Integer orderNumber;
	
	public ImageFile() {}
	
	public ImageFile(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Integer getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(Integer order) {
		this.orderNumber = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageFile other = (ImageFile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " " + this.fileName + " (id: " + this.id + ")";
	}
}
