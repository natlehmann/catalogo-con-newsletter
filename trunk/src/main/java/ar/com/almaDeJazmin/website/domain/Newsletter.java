package ar.com.almaDeJazmin.website.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Newsletter implements Serializable, Cloneable {

	private static final long serialVersionUID = -1194908199561854569L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=255, nullable=false, unique=true)
	private String name;
	
	@Column(length=255, nullable=false)
	private String subject;
	
	@Column(length=2048, nullable=false)
	private String content;
	
	@OneToMany(mappedBy="newsletter", cascade=CascadeType.ALL)
	private List<NewsletterDistribution> distributions = new LinkedList<NewsletterDistribution>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public List<NewsletterDistribution> getDistributions() {
		return distributions;
	}
	
	public void setDistributions(List<NewsletterDistribution> distributions) {
		this.distributions = distributions;
	}
	
	public void addDistribution(NewsletterDistribution newsletterDistribution) {
		newsletterDistribution.setNewsletter(this);
		this.distributions.add(newsletterDistribution);
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
		Newsletter other = (Newsletter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Newsletter " + this.name;
	}
	
	@Override
	public Newsletter clone() {
		Newsletter other = new Newsletter();
		other.setContent(this.content);
		other.setSubject(this.subject);
		return other;
	}

}
