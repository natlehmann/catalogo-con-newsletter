package ar.com.almaDeJazmin.website.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="contactType", discriminatorType=DiscriminatorType.STRING)
public abstract class Contact implements Serializable {
	
	private static final long serialVersionUID = 1126421989230114956L;
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column(length=255, nullable=false)
	private String name;
	
	@Column(length=100, nullable=false)
	private String email;
	
	@Column(length=512)
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date contactDate = new Date();
	
	private boolean notified;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getContactDate() {
		return contactDate;
	}
	
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
	
	public boolean isNotified() {
		return notified;
	}

	public void setNotified(boolean notified) {
		this.notified = notified;
	}

	@Override
	public String toString() {
		return "Contact " + this.name;
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
		Contact other = (Contact) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
